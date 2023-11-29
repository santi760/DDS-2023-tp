package ar.edu.utn.frba.dds.models.entities_establishment;

import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.PublicTransportTpye;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityContainerType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class EntityTest {

  @Test
  public void AltaEntityOrganization(){
  Entity organization = new Entity();
  EntityContainerType entityType = new EntityContainerType(EntityType.ORGANIZATION);
  organization.setTypeEntity(entityType);

    assertEquals(organization.getTypeEntity().getType(), EntityType.ORGANIZATION);
  }

  @Test
  public void AltaTransportePublico(){
    Entity entity = new Entity();
    EntityContainerType entityType = new EntityContainerType(EntityType.PUBLIC_TRANSPORT);
    entity.setTypeEntity(entityType);

    assertEquals(entity.getTypeEntity().getType(), EntityType.PUBLIC_TRANSPORT);
  }

  @Test
  public void AddEstablishmentsToEntity(){
    EntityContainerType entityType = new EntityContainerType(EntityType.PUBLIC_TRANSPORT, PublicTransportTpye.SUBWAY);

    Entity subteB = new Entity("SUBTE B",entityType);

    Establishment rosas = new Establishment("ROSAS", EstablishmentType.STATION);
    Establishment angelGallardo = new Establishment("ANGEL GALLARDO", EstablishmentType.STATION);
    Establishment alem = new Establishment("ALEM", EstablishmentType.STATION);

    subteB.addEstablishment(rosas,angelGallardo,alem);
    assertEquals(3,subteB.getEstablishments().size());

    subteB.addEstablishment(rosas,angelGallardo,alem);
    assertEquals(6,subteB.getEstablishments().size());
  }


  @Test
  public void getFirstEstablishmentOfEntity(){
    EntityContainerType entityType = new EntityContainerType(EntityType.PUBLIC_TRANSPORT, PublicTransportTpye.SUBWAY);

    Entity subteB = new Entity("SUBTE B",entityType);


    Establishment rosas = new Establishment("ROSAS", EstablishmentType.STATION);
    Establishment angelGallardo = new Establishment("ANGEL GALLARDO", EstablishmentType.STATION);
    Establishment alem = new Establishment("ALEM", EstablishmentType.STATION);

    subteB.addEstablishment(rosas,angelGallardo,alem);
    assertEquals("ROSAS",subteB.getFirstEstablishment().getName());
  }

  @Test
  public void getLastEstablishmentOfEntity(){
    EntityContainerType entityType = new EntityContainerType(EntityType.PUBLIC_TRANSPORT, PublicTransportTpye.SUBWAY);

    Entity subteB = new Entity("SUBTE B",entityType);


    Establishment rosas = new Establishment("ROSAS", EstablishmentType.STATION);
    Establishment angelGallardo = new Establishment("ANGEL GALLARDO", EstablishmentType.STATION);
    Establishment alem = new Establishment("ALEM", EstablishmentType.STATION);

    subteB.addEstablishment(rosas,angelGallardo,alem);
    assertEquals("ALEM",subteB.getLastEstablishment().getName());

  }

}






