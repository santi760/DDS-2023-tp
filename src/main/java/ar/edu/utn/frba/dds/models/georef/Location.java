package ar.edu.utn.frba.dds.models.georef;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private double latitude;
    private double longitude;

    public Location(){

    }

    public Location(double longitude , double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
