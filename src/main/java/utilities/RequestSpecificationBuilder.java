package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class RequestSpecificationBuilder {
    RequestSpecification requestSpecification;



    public RequestSpecification createRequestSpecification(){
        try {
            PrintStream log = new PrintStream(new FileOutputStream("looging.txt"));
            requestSpecification =new RequestSpecBuilder().setBaseUri(getValueFromConfigFile("baseURI").toString()).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .setContentType(ContentType.JSON).build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return requestSpecification;
    }

    public static Object getValueFromConfigFile(String key){
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
            properties.load(fis);
             return properties.get(key);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

