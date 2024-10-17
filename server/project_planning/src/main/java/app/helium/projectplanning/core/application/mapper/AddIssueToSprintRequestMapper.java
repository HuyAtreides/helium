package app.helium.projectplanning.core.application.mapper;

import app.helium.projectplanning.core.application.command.AddIssueToSprintCommand;
import app.helium.projectplanning.core.domain.request.AddIssueToSprintRequest;
import java.time.Instant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddIssueToSprintRequestMapper {

    @Mapping(target = "now", source = "now")
    AddIssueToSprintRequest mapToRequest(AddIssueToSprintCommand command, Instant now);
}
