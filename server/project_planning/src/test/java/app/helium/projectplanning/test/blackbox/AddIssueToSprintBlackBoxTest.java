package app.helium.projectplanning.test.blackbox;

import static io.restassured.RestAssured.given;

import app.helium.projectplanning.infra.api.rest.controller.ApiEndPoint;
import app.helium.projectplanning.test.shared.constant.CommonTestConstant;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

@BlackBoxTest
public class AddIssueToSprintBlackBoxTest {
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    @Sql(
            scripts = {
                    "classpath:/sql_scripts/clear_all_tables.sql",
                    "classpath:/sql_scripts/insert_project.sql",
                    "classpath:/sql_scripts/insert_sprint_along_with_issues.sql"
            },
            config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)
    )
    void issue_should_be_added_to_sprint_when_call_api_with_valid_payload() {
        given()
                .basePath(contextPath)
                .pathParam("issue_id", CommonTestConstant.DEFAULT_TEST_ISSUE_ID)
                .pathParam("project_id", CommonTestConstant.DEFAULT_TEST_PROJECT_ID)
                .pathParam("sprint_id", CommonTestConstant.DEFAULT_TEST_SPRINT_ID)
                .contentType(ContentType.JSON)
                .when()
                .patch(ApiEndPoint.ADD_ISSUE_TO_SPRINT)
                .then()
                .statusCode(204);
    }
}
