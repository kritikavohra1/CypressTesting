package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class StepDefinitions extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    public static String place_id;
    TestDataBuild data = new TestDataBuild();


    @Given("^Add Place Payload with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void add_Place_Payload_with_and(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_with_http_request(String resource, String method) throws Throwable {

        // Constructor will be called with value of resource which you pass
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        resspec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST")){
            response = res.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        }

        //.then().spec(resspec).extract().response();
    }

    @Then("^the API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code_200() {

        assertEquals(response.getStatusCode(), 200);
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {

        assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {

        //requestSpec
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification())
                .queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("^Delete Place Payload$")
    public void delete_Place_Payload() throws Throwable {

        res = given().spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));
    }

}