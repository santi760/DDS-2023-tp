package ar.edu.utn.frba.dds.models.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name="controlOrganism")
public class ControlOrganism {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name="name")
  private String name;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToMany
  @JoinColumn(name = "controlOrganism_id", referencedColumnName = "id")
  private Set<ProviderEntity> providerEntities = new HashSet<ProviderEntity>();




  //* ------------------------- CONSTRUCTORS ------------------------- *//

  public ControlOrganism() {

  }

  public ControlOrganism(String name) {
    this.name = name;
  }

  public ControlOrganism(String name, User user) {
    this.name = name;
    this.user = user;
  }


  //* ------------------------- METHODS ------------------------- *//

  public void addProviderEntity(ProviderEntity providerEntity) {
    this.providerEntities.add(providerEntity);
  }

  public void deleteProviderEntity(ProviderEntity providerEntity) { this.providerEntities.remove(providerEntity);}


}
