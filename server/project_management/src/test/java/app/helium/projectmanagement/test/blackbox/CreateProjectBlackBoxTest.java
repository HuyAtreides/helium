package app.helium.projectmanagement.test.blackbox;

import static app.helium.projectmanagement.test.shared.file.FileReader.readJsonFromFile;
import static io.restassured.RestAssured.given;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import app.helium.projectmanagement.infra.api.rest.controller.ApiEndPoint;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Value;

@BlackBoxTest
public class CreateProjectBlackBoxTest {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    void create_project_with_valid_data_should_success() throws JSONException {
        String response = given()
                .basePath(contextPath)
                .body(readJsonFromFile("api/create_project/valid_request.json"))
                .contentType(ContentType.JSON)
                .when()
                .post(ApiEndPoint.CREATE_PROJECT)
                .then()
                .statusCode(201)
                .and().extract().body().asString();
        String expectedResponse = readJsonFromFile("api/create_project/response.json");

        assertEquals(expectedResponse, response, JSONCompareMode.LENIENT);

    }
}
