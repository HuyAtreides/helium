package app.helium.projectplanning.core.application.command;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@ToString(callSuper = true)
public class CreateIssueWithinSprintCommand extends CreateIssueCommand {
    private UUID sprintId;
}
