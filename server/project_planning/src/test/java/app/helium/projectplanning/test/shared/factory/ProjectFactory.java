package app.helium.projectplanning.test.shared.factory;

import app.helium.projectplanning.core.domain.factory.IssueFactory;
import app.helium.projectplanning.core.domain.model.Issue;
import app.helium.projectplanning.core.domain.model.IssueStatus;
import app.helium.projectplanning.core.domain.model.IssueType;
import app.helium.projectplanning.core.domain.model.Project;
import app.helium.projectplanning.core.domain.request.CreateIssueRequest;
import app.helium.projectplanning.core.domain.request.CreateSprintRequest;
import app.helium.projectplanning.test.shared.constant.CommonTestConstant;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProjectFactory {

    private static final IssueFactory issueFactory = new IssueFactory();


    public static Project projectWithIssueAndSprint() {
        UUID issueStatusId = UUID.fromString("97880669-bde4-4737-8e0d-cd8e14b6588f");
        UUID issueTypeId = UUID.fromString("7231b12c-fd1a-4312-9a2f-aca28bf765dc");
        IssueStatus issueStatus = IssueStatus.builder().id(issueStatusId).name("in_progress")
                .build();
        IssueType issueType = IssueType.builder().id(issueTypeId).name("user_story").build();

        Project project = Project.builder()
                .id(CommonTestConstant.DEFAULT_TEST_PROJECT_ID)
                .issueStatuses(
                        Set.of(issueStatus)
                )
                .issueTypes(
                        Set.of(issueType)
                )
                .build();

        project.createNewSprint(
                CreateSprintRequest.builder()
                        .creatorId(CommonTestConstant.DEFAULT_USER_ID)
                        .name("Test Sprint")
                        .goal("Testing")
                        .startDate(CommonTestConstant.DEFAULT_TEST_START_DATE)
                        .dueDate(CommonTestConstant.DEFAULT_TEST_END_DATE)
                        .createdAt(CommonTestConstant.FIXED_NOW_INSTANT)
                        .id(CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                        .build()
        );
        Issue issue = issueFactory.createIssue(
                CreateIssueRequest
                        .builder()
                        .issueID(CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                        .summary("New Issue")
                        .issueName("HE-1104")
                        .attachmentURLs(List.of(
                                "https://media.helium.com/path",
                                "https://media.helium.com/media_name",
                                "https://media.helium.com/media-name"
                        ))
                        .startDate(CommonTestConstant.DEFAULT_TEST_START_DATE)
                        .dueDate(Instant.parse("2025-04-14T00:00:00Z"))
                        .pointEstimate(1)
                        .creatorId(CommonTestConstant.DEFAULT_TEST_USER_ID)
                        .project(project)
                        .createdAt(CommonTestConstant.FIXED_NOW_INSTANT)
                        .issueStatusId(issueStatusId)
                        .issueTypeId(issueTypeId)
                        .build()
        );
        project.addIssue(issue);

        return project;
    }
    /*
    public static Issue createDefaultTestIssue() {

    }*/
}
