package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityContainerType;

public class EntityBuilder {

  private Entity entity = new Entity();

  public EntityBuilder() {

  }

  public EntityBuilder withName(String name) {
    this.entity.setName(name);
    return this;
  }

  public EntityBuilder withTypeEntity(EntityContainerType typeEntity) {
    this.entity.setTypeEntity(typeEntity);
    return this;
  }

  public EntityBuilder withEstablishments(Establishment... establishments) {
    this.entity.addEstablishment(establishments);
    return this;
  }

  public EntityBuilder withEstablishment(Establishment establishment) {
    this.entity.addEstablishment(establishment);
    return this;
  }


  public Entity build() {
    //* validaciones si necesitamos
    return this.entity;
  }

}

//* ejemplo de uso

//*
// Entity entity = new EntityBuilder()
//    .withName("Entity Name")
//    .withTypeEntity(typeEntity)
//    .withEstablishments(establishmentsList)
//    .withAverageOfIncidentsPerWeek(10)
//    .build();
// */
