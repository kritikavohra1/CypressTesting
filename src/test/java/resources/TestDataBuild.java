package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String address, String language){

        AddPlace addapi = new AddPlace();
        addapi.setAccuracy(50);
        addapi.setAddress(address);
        addapi.setLanguage(language);
        addapi.setPhone_number("(+91) 983 893 3937");
        addapi.setWebsite("https://rahulshettyacademy.com");
        addapi.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        addapi.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        addapi.setLocation(l);

        return addapi;
    }

    public String deletePlacePayload(String place_id){

        return "{\n" +
                "    \"place_id\": \""+place_id+"\"\n" +
                "}";
    }
}
