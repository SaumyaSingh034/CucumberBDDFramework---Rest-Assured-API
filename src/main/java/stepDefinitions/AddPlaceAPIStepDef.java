package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payloadBuilder.DataBuilder;
import resources.Constants;
import utilities.RequestSpecificationBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlaceAPIStepDef extends RequestSpecificationBuilder {

    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    Response response;
    DataBuilder dataBuilder = new DataBuilder();
    Constants constantsBuilder;

    @Given("user add Place payload with {string} {string} {string} {string} {string} and {string}")
    public void userAddPlacePayloadWithAnd(String name, String language, String website, String accuracy, String phoneNumber, String address) {
         responseSpecification =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification=given().spec(createRequestSpecification())
                .body(dataBuilder.createAddPlaceRequestPayload(name,language, website,Integer.parseInt(accuracy),phoneNumber,address));


    }

    @When("user make {string} API call to {string}")
    public void userMakeAPICallTo(String httpMethod, String endpoint) {
        constantsBuilder = Constants.valueOf(endpoint);
       response =requestSpecification.when().post(constantsBuilder.getbasePath()).
                then().spec(responseSpecification).extract().response();
    }

    @Then("user validate the status code is {int}")
    public void userValidateTheStatusCodeIs(int ExpectedStatusCode) {
        assertEquals(response.getStatusCode(), ExpectedStatusCode);
    }

    @Then("user validate response body {string} is {string}")
    public void user_validate_response_body_is(String key, String ExpectedValue) {
        assertEquals(response.jsonPath().get(key), ExpectedValue);

    }



}
