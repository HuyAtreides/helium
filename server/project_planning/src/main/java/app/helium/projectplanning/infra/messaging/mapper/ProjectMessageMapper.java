package app.helium.projectplanning.infra.messaging.mapper;

import app.helium.projectmanagement.project.Data;
import app.helium.projectmanagement.project.ProjectMessage;
import app.helium.projectplanning.core.domain.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMessageMapper {
    Project mapToProject(Data projectMessageData);
}
