package ar.edu.utn.frba.dds.models.entities_establishment;

import ar.edu.utn.frba.dds.models.locations.*;
import ar.edu.utn.frba.dds.models.community.Incident;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "establishment")
public class Establishment {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_establishment")
  private EstablishmentType typeEstablishment;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "province_id", referencedColumnName = "province_id")
  private Province province;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "department_id", referencedColumnName = "department_id")
  private Department department;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "municipality_id", referencedColumnName = "municipality_id")
  private Municipality municipality;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "locality_id", referencedColumnName = "locality_id")
  private Locality locality;



  //* ------------------------- CONSTRUCTORS -------------------------*//


  public Establishment() {

  }


  public Establishment(String name, EstablishmentType typeEstablishment) {
    this.name = name;
    this.typeEstablishment = typeEstablishment;
  }


//* ------------------------- FUNCTIONS -------------------------*//



 public List<Incident> getIncidentsOfEstablishment(){
   // incidentRepository.getIncidentsOf(this)
    return new ArrayList<>();
 }

} // end clase
