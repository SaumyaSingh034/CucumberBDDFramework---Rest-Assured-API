package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.DataBuilder;
import utilities.RequestSpecificationBuilder;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlaceAPIStepDef extends RequestSpecificationBuilder {

    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    Response response;
    DataBuilder dataBuilder = new DataBuilder();

    @Given("user add Place payload")
    public void user_add_place_payload() {
         responseSpecification =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification=given().spec(createRequestSpecification())
                .body(dataBuilder.createAddPlaceRequestPayload());


    }

    @When("user make POST API call to {string}")
    public void userMakePOSTAPICallTo(String arg0) {
       response =requestSpecification.when().post("/maps/api/place/add/json").
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
