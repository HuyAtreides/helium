package app.helium.projectplanning.test.unit;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.helium.projectplanning.core.domain.model.IssueStatus;
import app.helium.projectplanning.core.domain.model.IssueType;
import app.helium.projectplanning.core.domain.model.Project;
import app.helium.projectplanning.core.domain.request.AddIssueToSprintRequest;
import app.helium.projectplanning.core.domain.request.CreateSprintRequest;
import app.helium.projectplanning.test.shared.constant.CommonTestConstant;
import app.helium.projectplanning.test.shared.factory.ProjectFactory;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class AddIssueToSprintTest {

    /*@Test
    void add_issue_to_a_closed_sprint_should_fail() {
        Project project = ProjectFactory.projectWithIssueAndSprint();

        assertThrows(IllegalStateException.class,
                () -> project.addIssueToSprint(AddIssueToSprintRequest.builder()
                        .issueId(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .now(Instant.parse("2025-04-27T11:00:00Z"))
                        .sprintId(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .build()
                ));
    }
*/
    @Test
    void add_issue_to_a_sprint_after_its_due_date_should_fail() {
        Project project = ProjectFactory.projectWithIssueAndSprint();

        assertThrows(IllegalStateException.class,
                () -> project.addIssueToSprint(AddIssueToSprintRequest.builder()
                        .issueId(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .now(Instant.parse("2025-04-27T11:00:00Z"))
                        .sprintId(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .build()
                ));
    }

    @Test
    void add_issue_to_a_valid_sprint_should_success() {
        Project project = ProjectFactory.projectWithIssueAndSprint();

        assertDoesNotThrow(
                () -> project.addIssueToSprint(AddIssueToSprintRequest.builder()
                        .issueId(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .now(Instant.parse("2025-04-05T11:00:00Z"))
                        .sprintId(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .build()
                )
        );

        assertTrue(project.isIssueInSprint(
                CommonTestConstant.DEFAULT_TEST_ISSUE_ID,
                CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
        );
    }

    @Test
    void add_issue_which_is_already_in_a_sprint_to_another_sprint_should_remove_the_issue_from_the_previous_sprint() {
        Project project = ProjectFactory.projectWithIssueAndSprint();
        UUID newSprintID = UUID.fromString("ccf41de3-df53-4d0d-be33-e3e6380b5f73");

        project.createNewSprint(
                CreateSprintRequest.builder()
                        .id(newSprintID)
                        .build()
        );

        project.addIssueToSprint(
                AddIssueToSprintRequest.builder()
                        .issueId(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .now(Instant.parse("2025-04-05T11:00:00Z"))
                        .sprintId(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .build()
        );

        project.addIssueToSprint(
                AddIssueToSprintRequest.builder()
                        .issueId(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .now(Instant.parse("2025-04-05T11:00:00Z"))
                        .sprintId(newSprintID)
                        .build()
        );

        assertFalse(project.isIssueInSprint(
                        CommonTestConstant.DEFAULT_TEST_ISSUE_ID,
                        CommonTestConstant.DEFAULT_TEST_SPRINT_ID
                )
        );

        assertTrue(project.isIssueInSprint(
                        CommonTestConstant.DEFAULT_TEST_ISSUE_ID,
                        newSprintID
                )
        );
    }
}
