package app.helium.projectmanagement.core.application.command;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectCommand {
    private String iconURL;
    private String name;
    private String description;
    private UUID projectLeadId;
    private String key;
    private UUID defaultAssigneeId;
}
