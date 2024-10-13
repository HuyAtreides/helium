package app.helium.projectmanagement.infra.api.rest.response;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class ProjectPayload {
    private UUID id;
    private String iconURL;
    private String name;
    private String description;
    private UUID projectLeadId;
    private String key;
    private UUID defaultAssigneeId;
    private Instant createdAt;
    private Instant lastUpdatedAt;
    private UUID lastUpdatedById;
}
