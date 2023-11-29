package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.repositories.entities.EstablishmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.IncidentRepository;
import org.junit.jupiter.api.Test;

public class IncidentRepositoryTest {

  @Test
  public void incidentTest(){
    Long n = 1L;
    Incident incident_01 = IncidentRepository.getInstance().read(n);

    assertEquals(false, incident_01.getOpen());
    assertEquals(1, incident_01.getAssociatedProvisionOfService().getId());

    n = 2L;
    Incident incident_02 = IncidentRepository.getInstance().read(n);

    assertEquals(true, incident_02.getOpen());
    assertEquals(2, incident_02.getAssociatedProvisionOfService().getId());

    n = 3L;
    Incident incident_03 = IncidentRepository.getInstance().read(n);

    assertEquals(true, incident_03.getOpen());
    assertEquals(3, incident_03.getAssociatedProvisionOfService().getId());

  }

  @Test
  public void getOpenIncidentsTest(){

    assertEquals(2, IncidentRepository.getInstance().getOpenIncidents().size());

  }

  @Test
  public void getClosedIncidentsTest(){

    assertEquals(1, IncidentRepository.getInstance().getClosedIncidents().size());

  }

  @Test
  public void getIncidentsByEstablishment(){

    Establishment angelGallardo = EstablishmentRepository.getInstance().read(6L);

    assertEquals(3, IncidentRepository.getInstance().getIncidentsFromEstablishment(angelGallardo).get(0).getId());

  }

  @Test
  public void getIncidentsByEntity(){

    Entity entity = EntityRepository.getInstance().read(3L);

    assertEquals(1, IncidentRepository.getInstance().getIncidentsFromEntity(entity).get(0).getId());

  }




}
