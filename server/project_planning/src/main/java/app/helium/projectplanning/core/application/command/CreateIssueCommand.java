package app.helium.projectplanning.core.application.command;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreateIssueCommand {
    private String summary;
    private String description;
    private List<String> attachmentURLs;
    private Integer pointEstimate;
    private UUID issueTypeId;
    private UUID issueStatusId;
    private Instant startDate;
    private Instant dueDate;
    private UUID projectId;
    private UUID reporterId;
    private UUID assigneeId;
}
