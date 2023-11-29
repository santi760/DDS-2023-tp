package ar.edu.utn.frba.dds.models.locations;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "province")
public class Province {

  @Id
  @GeneratedValue
  private Long province_id;

  @Column(name = "idprovince")
  private int idProvince;

  @Column(name = "name")
  private String name;

  @Embedded
  private Coordinate coordinate;

  public Province(int id, String name, Coordinate coordinate) {
    this.idProvince = id;
    this.name = name;
    this.coordinate = coordinate;
  }

  public Province(String name) {
    this.name = name;
  }

  public Province() {

  }
}
