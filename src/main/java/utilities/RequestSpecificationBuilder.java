package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RequestSpecificationBuilder {
    RequestSpecification requestSpecification;

    public RequestSpecification createRequestSpecification(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        try {
            PrintStream log = new PrintStream(new FileOutputStream("looging.txt"));
            requestSpecification =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .setContentType(ContentType.JSON).build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return requestSpecification;
    }
}

