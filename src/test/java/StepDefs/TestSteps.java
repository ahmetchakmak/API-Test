package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class TestSteps {

    String baseURI;
    ValidatableResponse response;

    @Given("existing Server application {string}")
    public void existingServerApplicationURI(String serverURI) {
        baseURI=serverURI;
    }

    @And("on GET request to {string}")
    public void onGETRequestTo(String endPoint) {
        response=given().baseUri(baseURI)
                .accept(ContentType.JSON)
                .when().get(endPoint)
                .then().assertThat().contentType(ContentType.JSON);
    }

    @Then("it returns expected users list")
    public void onGETRequestToUsersItReturnsExpectedUsersList() {
        String actual=response.extract().body().asString();
        String expected="{\"id\":1,\"email\":\"george.bluth@reqres.in\",\"first_name\":\"George\",\"last_name\":\"Bluth\",\"avatar\":\"https://reqres.in/img/faces/1-image.jpg\"},{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},{\"id\":3,\"email\":\"emma.wong@reqres.in\",\"first_name\":\"Emma\",\"last_name\":\"Wong\",\"avatar\":\"https://reqres.in/img/faces/3-image.jpg\"},{\"id\":4,\"email\":\"eve.holt@reqres.in\",\"first_name\":\"Eve\",\"last_name\":\"Holt\",\"avatar\":\"https://reqres.in/img/faces/4-image.jpg\"}";
        Assert.assertTrue("Verify response contains users list",actual.contains(expected));
    }

    @Then("it returns expected user data")
    public void itReturnsExpectedWelcomeMessage() {
        response.assertThat().statusCode(200);
        String actual=response.extract().body().asString();
        String expected="{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"}";
        Assert.assertTrue("Verify response contains users list",actual.contains(expected));
    }

    @Then("it returns {int} status code")
    public void itReturnsStatusCode(int expectedStatusCode) {
        response.assertThat().statusCode(expectedStatusCode);
    }
}
