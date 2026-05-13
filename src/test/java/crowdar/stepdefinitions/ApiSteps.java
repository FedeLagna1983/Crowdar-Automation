package crowdar.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    private static final String BASE_URL = "https://www.mercadolibre.com.ar";
    private static final String DEPARTMENTS_ENDPOINT = "/menu/departments";

    private Response response;

    @Given("the Mercado Libre departments service is available")
    public void theMercadoLibreDepartmentsServiceIsAvailable() {
        Assert.assertNotNull(BASE_URL, "Base URL should not be null");
    }

    @When("the user sends a GET request to the departments endpoint")
    public void theUserSendsAGetRequestToTheDepartmentsEndpoint() {

        response = given()
            .baseUri(BASE_URL)
            .header("User-Agent", "Mozilla/5.0")
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .when()
            .get(DEPARTMENTS_ENDPOINT);
    }

    @Then("the API response status code should be 200")
    public void theApiResponseStatusCodeShouldBe200() {
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Unexpected API status code"
        );
    }

    @Then("the API response should contain departments")
    public void theApiResponseShouldContainDepartments() {
        String responseBody = response.getBody().asString();

        Assert.assertNotNull(responseBody, "Response body should not be null");
        Assert.assertFalse(responseBody.isEmpty(), "Response body should not be empty");

        Assert.assertTrue(
                responseBody.toLowerCase().contains("departments")
                        || responseBody.toLowerCase().contains("departamentos")
                        || responseBody.toLowerCase().contains("categories"),
                "Response body does not contain departments information"
        );
    }
}