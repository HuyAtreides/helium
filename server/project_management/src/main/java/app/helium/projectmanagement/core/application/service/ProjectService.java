package app.helium.projectmanagement.core.application.service;

import app.helium.projectmanagement.core.application.command.CreateProjectCommand;
import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.infra.repository.ProjectRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final MessageProducer<UUID, Project> kafkaProjectProducer;

    @Transactional
    public Project createProject(CreateProjectCommand command) {
        Project project = Project.builder()
                .id(UUID.randomUUID())
                .key(command.getKey())
                .iconURL(command.getIconURL())
                .description(command.getDescription())
                .projectLeadId(command.getProjectLeadId())
                .defaultAssigneeId(command.getDefaultAssigneeId())
                .name(command.getName())
                .createdAt(Instant.now())
                .lastUpdatedById(UUID.randomUUID())
                .lastUpdatedAt(Instant.now())
                .build();
        Project savedProject = projectRepository.save(project);
        kafkaProjectProducer.sendMessage(savedProject.getId(), savedProject);

        return projectRepository.save(project);
    }
}
