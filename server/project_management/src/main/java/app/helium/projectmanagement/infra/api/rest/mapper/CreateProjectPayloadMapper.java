package app.helium.projectmanagement.infra.api.rest.mapper;

import app.helium.projectmanagement.core.application.command.CreateProjectCommand;
import app.helium.projectmanagement.infra.api.rest.request.CreateProjectPayload;
import org.mapstruct.Mapper;

@Mapper
public interface CreateProjectPayloadMapper {
    CreateProjectCommand mapToCommand(CreateProjectPayload projectPayload);
}
