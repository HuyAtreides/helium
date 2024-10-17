package app.helium.projectplanning.core.domain.request;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddIssueToSprintRequest {
    private UUID sprintId;
    private UUID issueId;
    private Instant now;
}
