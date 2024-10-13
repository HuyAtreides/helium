package app.helium.projectmanagement.infra.api.rest.mapper;

import app.helium.projectmanagement.core.domain.model.Project;
import app.helium.projectmanagement.infra.api.rest.response.ProjectPayload;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectPayloadMapper {
    ProjectPayload mapToPayload(Project project);
}
