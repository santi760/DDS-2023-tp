package ar.edu.utn.frba.dds.models.locations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "locality")
public class Locality {
    @Id
    @GeneratedValue
    private Long locality_id;

    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Coordinate coordinate;


    public Locality(String id, String name, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
    }

    public Locality(String name) {
        this.name = name;
    }
    public Locality() {

    }
}
