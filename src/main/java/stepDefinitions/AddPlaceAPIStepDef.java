package stepDefinitions;

import io.cucumber.java.en.And;
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
import resources.ReuasableMethods;
import utilities.RequestSpecificationBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlaceAPIStepDef extends RequestSpecificationBuilder {

    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    Response response;
    DataBuilder dataBuilder = new DataBuilder();
    Constants constantsBuilder;
    static String place_id;

    @Given("user add Place payload with {string} {string} {string} {string} {string} and {string}")
    public void userAddPlacePayloadWithAnd(String name, String language, String website, String accuracy, String phoneNumber, String address) {
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification = given().spec(createRequestSpecification())
                .body(dataBuilder.createAddPlaceRequestPayload(name, language, website, Integer.parseInt(accuracy), phoneNumber, address));


    }

    @When("user make {string} API call to {string}")
    public void userMakeAPICallTo(String httpMethod, String endpoint) {
        constantsBuilder = Constants.valueOf(endpoint);

        if (httpMethod.equals("POST"))
            response = requestSpecification.when().post(constantsBuilder.getbasePath());
        else if (httpMethod.equals("GET")) {
            response = requestSpecification.when().get(constantsBuilder.getbasePath());

        } else if (httpMethod.equals("PUT")) {

        } else if (httpMethod.equals("DELETE")) {
            response = requestSpecification.when().post(constantsBuilder.getbasePath());

        } else
            System.out.println("Please check your http Method : " + httpMethod);
        response.then().spec(responseSpecification).extract().response();
    }

    @Then("user validate the status code is {int}")
    public void userValidateTheStatusCodeIs(int ExpectedStatusCode) {
        assertEquals(response.getStatusCode(), ExpectedStatusCode);
    }

    @Then("user validate response body {string} is {string}")
    public void user_validate_response_body_is(String key, String ExpectedValue) {
        assertEquals(ReuasableMethods.getJsonValue(response,key), ExpectedValue);

    }


    @And("user verifies the place_Id created maps to {string} using {string}")
    public void userVerifiesThePlace_IdCreatedMapsToUsing(String expectedName, String endpoint) {
        place_id = ReuasableMethods.getJsonValue(response,"place_id");
      requestSpecification = given().spec(createRequestSpecification()).queryParam("place_id",place_id);
      userMakeAPICallTo("GET",endpoint);
      String actualName = ReuasableMethods.getJsonValue(response,"name");
      assertEquals(expectedName,actualName);
    }

    @Given("user build the delete place payload")
    public void userBuildTheDeletePlacePayload() {
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification = given().spec(createRequestSpecification())
                .body(dataBuilder.createDeletePlacePlayload(place_id));
    }
}
