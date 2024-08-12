package TestCases;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PolygonAPITests {
    @Description("Validate that the response schema matches the expected JSON schema.")
    @Test(priority = 0)
    public void verifyStatusCode() {
        given()
                .baseUri("https://api.polygon.io")
                .when()
                .get("/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?apiKey=2ELdFAGPUtlXcFv6re9039ThV6xQGtzs")
                .then()
                .statusCode(200);
    }


    @Description("Validate that the response schema from the Polygon API matches the expected JSON schema.")
    @Test(priority = 1)
    public void validateResponseSchema() {
        given()
                .baseUri("https://api.polygon.io")
                .when()
                .get("/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-01-09?apiKey=2ELdFAGPUtlXcFv6re9039ThV6xQGtzs")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("polygon-schema.json"));
    }
}
