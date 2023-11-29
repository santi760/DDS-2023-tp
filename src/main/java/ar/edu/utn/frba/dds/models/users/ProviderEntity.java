package ar.edu.utn.frba.dds.models.users;


import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import lombok.ToString;

@Getter
@Setter
@ToString
@javax.persistence.Entity
@Table(name = "providerEntity")
public class ProviderEntity {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id" , referencedColumnName = "id")
  private User user;

  @Column(name = "name")
  private String name;

  @OneToMany
  @JoinColumn(name = "providerEntity_id" , referencedColumnName = "id")
  private Set<Entity> entities;


  //* ------------------------- CONSTRUCTORS ------------------------- *//
  public ProviderEntity(String name) {
    this.name = name;
    this.entities = new HashSet<Entity>();

  }

  public ProviderEntity() {}

  //* ------------------------- METHODS ------------------------- *//

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public void addEntities(Entity... entities) {
    this.entities.addAll(Arrays.asList(entities));
  }
  public void deleteEntity(Entity entity) { entities.remove(entity);}


}
