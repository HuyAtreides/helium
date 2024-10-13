package app.helium.projectmanagement.infra.api.rest.controller;

import app.helium.projectmanagement.core.application.service.ProjectService;
import app.helium.projectmanagement.infra.api.rest.mapper.CreateProjectPayloadMapper;
import app.helium.projectmanagement.infra.api.rest.mapper.ProjectPayloadMapper;
import app.helium.projectmanagement.infra.api.rest.request.CreateProjectPayload;
import app.helium.projectmanagement.infra.api.rest.response.ProjectPayload;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectPayloadMapper projectPayloadMapper = Mappers.getMapper(ProjectPayloadMapper.class);
    private final CreateProjectPayloadMapper mapper = Mappers.getMapper(CreateProjectPayloadMapper.class);

    @PostMapping(APIEndPoint.CREATE_PROJECT)
    public ProjectPayload createProject(@RequestBody CreateProjectPayload projectPayload) {
        var project = projectService.createProject(
          mapper.mapToCommand(projectPayload)
        );

        return projectPayloadMapper.mapToPayload(project);
    }

}
