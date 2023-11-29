package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.repositories.entities.EstablishmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.ProvisionOfServiceRepository;
import org.junit.jupiter.api.Test;

public class ProvisionOfServiceRepositoryTest {

  @Test
  public void provisionOfServiceTest() {
    Long n = 1L;
    ProvisionOfService provisionOfService_01 = ProvisionOfServiceRepository.getInstance().read(n);

    assertEquals(1, provisionOfService_01.getEstablishment().getId());
    assertEquals(1, provisionOfService_01.getService().getId());

    n = 2L;
    ProvisionOfService provisionOfService_02 = ProvisionOfServiceRepository.getInstance().read(n);

    assertEquals(2, provisionOfService_02.getEstablishment().getId());
    assertEquals(5, provisionOfService_02.getService().getId());

    n = 3L;
    ProvisionOfService provisionOfService_03 = ProvisionOfServiceRepository.getInstance().read(n);

    assertEquals(6, provisionOfService_03.getEstablishment().getId());
    assertEquals(2, provisionOfService_03.getService().getId());
  }

}
