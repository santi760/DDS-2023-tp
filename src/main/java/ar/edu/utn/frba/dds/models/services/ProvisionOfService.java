package ar.edu.utn.frba.dds.models.services;

import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name="provisionOfService")
public class ProvisionOfService {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name="name")
  private String name;

  @ManyToOne
  @JoinColumn(name="service_id", referencedColumnName = "id")
  private Service service;

  @ManyToOne
  @JoinColumn(name="establishment_id", referencedColumnName = "id")
  private Establishment establishment;



  //*---------------------------- CONSTRUCTORS -------------------------------- *//

  public ProvisionOfService(){

  }

  public ProvisionOfService(Service service,Establishment establishment){
    this.service = service;
    this.establishment = establishment;
  }

  public ProvisionOfService(String name, Service service, Establishment establishment) {
    this.name = name;
    this.service = service;
    this.establishment = establishment;
  }

  //*---------------------------- METHODS -------------------------------- *//

  public void getIncidentsOfProvision(){
    //todo repositorioDeIncidentes.obtenerIncidentesDePrestacionDeServicio()
  }

}
