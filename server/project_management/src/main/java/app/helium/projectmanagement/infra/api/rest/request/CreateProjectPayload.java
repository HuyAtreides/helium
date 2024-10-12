package app.helium.projectmanagement.infra.api.rest.request;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProjectPayload {
    private String iconURL;
    private String name;
    private String description;
    private UUID projectLeadId;
    private String key;
    private UUID defaultAssigneeId;
}
