package app.helium.projectplanning.core.application.service;

import app.helium.projectplanning.core.application.command.AddIssueToSprintCommand;
import app.helium.projectplanning.core.application.command.CreateIssueCommand;
import app.helium.projectplanning.core.application.command.CreateIssueWithinSprintCommand;
import app.helium.projectplanning.core.application.command.CreateSprintCommand;
import app.helium.projectplanning.core.application.mapper.AddIssueToSprintRequestMapper;
import app.helium.projectplanning.core.application.mapper.CreateIssueRequestCommandMapper;
import app.helium.projectplanning.core.application.mapper.CreateSprintRequestMapper;
import app.helium.projectplanning.core.domain.factory.IssueFactory;
import app.helium.projectplanning.core.domain.model.Issue;
import app.helium.projectplanning.core.domain.model.Project;
import app.helium.projectplanning.core.domain.model.Sprint;
import app.helium.projectplanning.core.domain.request.AddIssueToSprintRequest;
import app.helium.projectplanning.core.domain.request.CreateIssueRequest;
import app.helium.projectplanning.core.domain.request.CreateSprintRequest;
import app.helium.projectplanning.infra.repository.ProjectRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectPlanningService {

    private final IssueFactory issueFactory;
    private final CreateIssueRequestCommandMapper mapper = Mappers.getMapper(
            CreateIssueRequestCommandMapper.class
    );
    private final CreateSprintRequestMapper createSprintRequestMapper = Mappers.getMapper(
            CreateSprintRequestMapper.class
    );
    private final AddIssueToSprintRequestMapper addIssueToSprintRequestMapper = Mappers.getMapper(
            AddIssueToSprintRequestMapper.class
    );
    private final AbstractDateTimeService dateTimeService;
    private final ProjectRepository projectRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Issue createIssue(CreateIssueCommand command) {
        log.info("message = create new issue, request = {}", command);
        UUID projectId = command.getProjectId();
        Project project = findProject(projectId);
        long nextSequence = projectRepository.getNextSequence();
        String issueName = project.generateIssueName(nextSequence);

        //TODO: retrieve user id from security context
        UUID creatorId = UUID.fromString("e0dfb21f-1ad9-42eb-94a3-98383ffa6618");
        Instant now = dateTimeService.getCurrentInstant();

        CreateIssueRequest request = mapper.mapToCreateIssueRequest(command, creatorId, issueName,
                project, now);
        Issue issue = issueFactory.createIssue(request);
        project.addIssue(issue);

        return projectRepository.save(project).getIssueById(issue.getId());
    }

    @Transactional
    public void addIssueToSprint(AddIssueToSprintCommand command) {
        log.info("message = add an issue to sprint, request = {}", command);
        Project project = findProject(command.getProjectId());
        Instant now = dateTimeService.getCurrentInstant();
        AddIssueToSprintRequest request = addIssueToSprintRequestMapper.mapToRequest(command, now);
        project.addIssueToSprint(request);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Issue createIssueWithinSprint(CreateIssueWithinSprintCommand command) {
        Issue issue = createIssue(command);
        addIssueToSprint(
                AddIssueToSprintCommand.builder()
                        .issueId(issue.getId())
                        .projectId(command.getProjectId())
                        .sprintId(command.getSprintId())
                        .build()
        );

        return issue;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Sprint createSprint(CreateSprintCommand command) {
        log.info("message = create new sprint, request = {}", command);
        //TODO: retrieve user id from security context
        UUID creatorId = UUID.fromString("e0dfb21f-1ad9-42eb-94a3-98383ffa6618");
        Project project = findProject(command.getProjectId());
        Instant now = dateTimeService.getCurrentInstant();
        UUID id = UUID.randomUUID();
        CreateSprintRequest request = createSprintRequestMapper.mapToCreateSprintRequest(id,
                command, creatorId, now);
        Sprint newSprint = project.createNewSprint(request);
        projectRepository.save(project);

        return newSprint;
    }

    @Transactional
    public void addNewProject(Project project) {
        projectRepository.save(project);
    }

    private Project findProject(UUID projectId) {
        return projectRepository.findById(projectId).orElseThrow();
    }
}
