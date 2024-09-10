package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReuasableMethods {

    public static String getJsonValue(Response response, String key){
        String responseString = response.asString();
        JsonPath js = new JsonPath(responseString);
        return js.get(key).toString();
    }
}
