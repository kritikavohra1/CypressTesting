package stepDefinations;

import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws Throwable {

        // write a code that will give you place id
        //execute this code only when place_id is null
        StepDefinitions m = new StepDefinitions();
        if (StepDefinitions.place_id == null){
            m.add_Place_Payload_with_and("Kritika", "English", "India");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Kritika", "GetPlaceAPI");
        }

    }
}
