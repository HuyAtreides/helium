package app.helium.projectplanning.core.application.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddIssueToSprintCommand {
    UUID projectId;
    UUID sprintId;
    UUID issueId;
}
