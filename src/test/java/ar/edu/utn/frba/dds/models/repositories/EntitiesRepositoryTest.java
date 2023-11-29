package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.Instanciado;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.PublicTransportTpye;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.DepartmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.LocalityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.MunicipalityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.ProvinceRepository;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import java.security.Provider;
import org.junit.jupiter.api.Test;

public class EntitiesRepositoryTest {

  @Test
  public void entitiesTest(){
    Long n = 1L;
    Entity subteB = EntityRepository.getInstance().read(n);

    assertEquals("Subte B", subteB.getName());
    assertEquals(EntityType.PUBLIC_TRANSPORT, subteB.getTypeEntity().getType());
    assertEquals(PublicTransportTpye.SUBWAY, subteB.getTypeEntity().getTypePublicTransport());

    n = 2L;
    Entity trenRoca = EntityRepository.getInstance().read(n);

    assertEquals("Tren Roca", trenRoca.getName());
    assertEquals(EntityType.PUBLIC_TRANSPORT, trenRoca.getTypeEntity().getType());
    assertEquals(PublicTransportTpye.RAILWAY, trenRoca.getTypeEntity().getTypePublicTransport());

    n = 3L;
    Entity jumbo = EntityRepository.getInstance().read(n);

    assertEquals("Banco CABA", jumbo.getName());
    assertEquals(EntityType.ORGANIZATION, jumbo.getTypeEntity().getType());

  }

  @Test
  public void getEntititesByNameTest(){

    assertEquals("Subte B", EntityRepository.getInstance().getEntityNamed("Subte B").getName());

  }

  @Test
  public void getEntititesFromProvider(){


    Long id = 2L;

    ProviderEntity providerEntity = ProviderEntityRepository.getInstance().read(id);

    assertEquals(2, EntityRepository.getInstance().getEntitiesFromProvider(providerEntity).size());

  }

}
