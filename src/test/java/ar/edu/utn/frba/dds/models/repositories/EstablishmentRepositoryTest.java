package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.PublicTransportTpye;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.repositories.entities.EstablishmentRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EstablishmentRepositoryTest {

  @Test
  public void establishmentTest(){
    /*
    Long n = 4L;
    Establishment angelGallardo = EstablishmentRepository.getInstance().read(n);

    assertEquals("Angel Gallardo", angelGallardo.getName());
    assertEquals(EstablishmentType.STATION, angelGallardo.getTypeEstablishment());
    assertEquals(1, angelGallardo.getDepartment().getId());

    n = 5L;
    Establishment medrano = EstablishmentRepository.getInstance().read(n);

    assertEquals("Medrano", medrano.getName());
    assertEquals(EstablishmentType.STATION, medrano.getTypeEstablishment());
    assertEquals(1, medrano.getDepartment().getId());

    n = 1L;
    Establishment sucursalAlmagro = EstablishmentRepository.getInstance().read(n);

    assertEquals("SUCURSAL ALMAGRO", sucursalAlmagro.getName());
    assertEquals(EstablishmentType.SUCURSAL, sucursalAlmagro.getTypeEstablishment());
    assertEquals(1, sucursalAlmagro.getDepartment().getId());
      */

    Long n = 2L;
    Entity entity = EntityRepository.getInstance().read(n);

    List<Establishment> establishments = EstablishmentRepository.getInstance().getEstablishmentsFromEntity(entity);

    EstablishmentRepository.getInstance().removeRelationWithEntity(establishments.get(0));
    assertEquals(establishments.size(),3 );


  }

}
