package ar.edu.utn.frba.dds.models.locations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Embeddable
public class Coordinate {

  @Column(name = "latitude")
  private double latitude;

  @Column(name = "longitude")
  private double longitude;

  public Coordinate(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Coordinate() {

  }
}
