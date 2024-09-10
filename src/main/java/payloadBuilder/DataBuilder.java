package payloadBuilder;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    public AddPlace createAddPlaceRequestPayload(String name, String language, String website, int accuracy, String phoneNumber, String address){
        AddPlace addPlace  =new AddPlace();
        addPlace.setAccuracy(accuracy);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number(phoneNumber);
        addPlace.setWebsite(website);
        addPlace.setName(name);
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        addPlace.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        addPlace.setLocation(l);
        return addPlace;
    }

    public String createDeletePlacePlayload(String place_id){
        return "{\"place_id\":\""+place_id+"\"}";
    }
}
