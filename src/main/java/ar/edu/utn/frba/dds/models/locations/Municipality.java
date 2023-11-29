package ar.edu.utn.frba.dds.models.locations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "municipality")
public class Municipality {

  @Id
  @GeneratedValue
  private Long municipality_id;

  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Embedded
  private Coordinate coordinate;

  public Municipality(int id, String name, Coordinate coordinate) {
    this.id = id;
    this.name = name;
    this.coordinate = coordinate;
  }

  public Municipality(String name) {
    this.name = name;
  }

  public Municipality() {

  }
}
