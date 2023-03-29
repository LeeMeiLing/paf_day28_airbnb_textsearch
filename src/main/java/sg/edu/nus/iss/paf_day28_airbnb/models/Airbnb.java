package sg.edu.nus.iss.paf_day28_airbnb.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Airbnb {
    
    private String name;
    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public static Airbnb toAirbnb(String jsonStr){

        Airbnb airbnb = new Airbnb();
        JsonReader reader = Json.createReader(new StringReader(jsonStr));
        JsonObject object = reader.readObject();
        airbnb.setName(object.getString("name"));
        airbnb.setDescription(object.getString("description"));

        return airbnb;
    }
    
}
