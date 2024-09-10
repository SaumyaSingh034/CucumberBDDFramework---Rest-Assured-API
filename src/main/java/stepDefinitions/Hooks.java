package stepDefinitions;

import io.cucumber.java.Before;
import stepDefinitions.AddPlaceAPIStepDef;

public class Hooks {

    @Before("@DeletePlace")
    public void preRequestie(){
        AddPlaceAPIStepDef addPlace = new AddPlaceAPIStepDef();
        if(AddPlaceAPIStepDef.place_id==null) {
            addPlace.userAddPlacePayloadWithAnd("Saumya", "French", "globant.com", "90", "8877661122", "North America");
            addPlace.userMakeAPICallTo("POST", "AddPlaceAPI");
            addPlace.userVerifiesThePlace_IdCreatedMapsToUsing("Saumya", "GetPlaceAPI");
        }
    }
}
