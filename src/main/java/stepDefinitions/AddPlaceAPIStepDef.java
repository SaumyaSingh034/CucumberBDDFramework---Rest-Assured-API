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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class AddPlaceAPIStepDef {

    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    Response response;

    @Given("user add Place payload")
    public void user_add_place_payload() {
        RestAssured.baseURI="https://rahulshettyacademy.com";

        AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName("Frontline house");
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();


         responseSpecification =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        requestSpecification=given().spec(req)
                .body(p);


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
