package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;

public class ProvisionOfServiceBuilder {


    private ProvisionOfService provisionOfService = new ProvisionOfService();

    public ProvisionOfServiceBuilder() {

    }

    public ProvisionOfServiceBuilder withName(String name) {
      this.provisionOfService.setName(name);
      return this;
    }

    public ProvisionOfServiceBuilder withService(Service service) {
      this.provisionOfService.setService(service);
      return this;
    }

    public ProvisionOfServiceBuilder withEstablishment(Establishment establishment) {
      this.provisionOfService.setEstablishment(establishment);
      return this;
    }

    public ProvisionOfService build() {
      // validaciones si necesitamos
      return this.provisionOfService;
    }

}

//* ejemplo de uso
/**
 * ProvisionOfService provisionOfService = new ProvisionOfServiceBuilder()
 *     .withName("Service Name")
 *     .withService(service)
 *     .withEstablishment(establishment)
 *     .build();
 */
