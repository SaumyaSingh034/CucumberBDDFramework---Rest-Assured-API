package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {
    RequestSpecification requestSpecification;

    public RequestSpecification createRequestSpecification(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        requestSpecification =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .addFilter(Re)
                .setContentType(ContentType.JSON).build();
        return requestSpecification;
    }
}

