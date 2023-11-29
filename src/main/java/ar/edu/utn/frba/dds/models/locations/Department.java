package ar.edu.utn.frba.dds.models.locations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "department")
public class Department {

  @Id
  @GeneratedValue
  private Long department_id;

  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Embedded
  private Coordinate coordinate;



  //* ---------------- CONSTRUCTORS ---------------- *//
  public Department() {

  }

  public Department(int id, String name, Coordinate coordinate) {
    this.id = id;
    this.name = name;
    this.coordinate = coordinate;
  }

  public Department(String name) {
    this.name = name;
  }
}


