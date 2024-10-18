package app.helium.projectplanning.test.integration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.helium.projectplanning.core.application.command.AddIssueToSprintCommand;
import app.helium.projectplanning.core.application.command.CreateIssueWithinSprintCommand;
import app.helium.projectplanning.core.application.service.ProjectPlanningService;
import app.helium.projectplanning.core.domain.model.Issue;
import app.helium.projectplanning.core.domain.model.Project;
import app.helium.projectplanning.infra.repository.ProjectRepository;
import app.helium.projectplanning.test.shared.constant.CommonTestConstant;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

@IntegrationTest
public class AddIssueToSprintIntegrationTest {

    @Autowired
    private ProjectPlanningService projectPlanningService;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @Sql(
            scripts = {
                    "classpath:/sql_scripts/clear_all_tables.sql",
                    "classpath:/sql_scripts/insert_project.sql",
                    "classpath:/sql_scripts/insert_sprint_along_with_issues.sql"
            },
            config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)
    )
    void move_an_issue_from_one_sprint_to_another_sprint_validly_should_success() {
        projectPlanningService.addIssueToSprint(
                AddIssueToSprintCommand.builder()
                        .sprintId(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .issueId(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .projectId(CommonTestConstant.DEFAULT_TEST_PROJECT_ID)
                        .build()
        );

        Project project = projectRepository.findById(CommonTestConstant.DEFAULT_TEST_PROJECT_ID)
                .orElseThrow();

        assertFalse(
                project.isIssueInSprint(
                        CommonTestConstant.DEFAULT_TEST_ISSUE_ID,
                        UUID.fromString("24f402bb-d5de-4886-b545-2a4cb0361632")
                )
        );
        assertTrue(
                project.isIssueInSprint(
                        CommonTestConstant.DEFAULT_TEST_ISSUE_ID,
                        CommonTestConstant.DEFAULT_TEST_SPRINT_ID
                )
        );
    }

    @Test
    @Sql(
            scripts = {
                    "classpath:/sql_scripts/clear_all_tables.sql",
                    "classpath:/sql_scripts/insert_project.sql",
                    "classpath:/sql_scripts/insert_sprint_along_with_issues.sql"
            },
            config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)
    )
    void create_issue_within_sprint_validly_should_success() {
        Issue issue = projectPlanningService.createIssueWithinSprint(
                CreateIssueWithinSprintCommand.builder()
                        .assigneeId(UUID.randomUUID())
                        .summary("New Issue")
                        .projectId(CommonTestConstant.DEFAULT_TEST_PROJECT_ID)
                        .startDate(CommonTestConstant.FIXED_NOW_INSTANT)
                        .dueDate(Instant.parse("2025-05-07T17:15:00Z"))
                        .reporterId(CommonTestConstant.DEFAULT_USER_ID)
                        .assigneeId(CommonTestConstant.DEFAULT_USER_ID)
                        .description("Implement create issue")
                        .attachmentURLs(List.of("https://media.helium.com/requirements.pdf",
                                "https://media.helium.com/figma"))
                        .pointEstimate(2)
                        .issueStatusId(CommonTestConstant.DEFAULT_TEST_ISSUE_STATUS_ID)
                        .issueTypeId(CommonTestConstant.DEFAULT_TEST_ISSUE_TYPE_ID)
                        .sprintId(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .build()
        );

        Project project = projectRepository.findById(CommonTestConstant.DEFAULT_TEST_PROJECT_ID)
                .orElseThrow();

        assertTrue(
                project.isIssueInSprint(issue.getId(), CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
        );
    }
}
