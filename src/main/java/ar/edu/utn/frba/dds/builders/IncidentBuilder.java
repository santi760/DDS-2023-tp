package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;

import java.time.LocalDateTime;

public class IncidentBuilder {

  Incident incident = new Incident();

  public IncidentBuilder() {

  }

  public IncidentBuilder withAssociatedProvisionOfService(ProvisionOfService provisionOfService) {
    this.incident.setAssociatedProvisionOfService(provisionOfService);
    return this;
  }

  public IncidentBuilder withCreator(CommunityMember creator) {
    this.incident.setCreator(creator);
    return this;
  }

  public IncidentBuilder withOpeningDescription(String openingDescription) {
    this.incident.setOpeningDescription(openingDescription);
    return this;
  }

  public IncidentBuilder withOpeningDate(LocalDateTime openingDate) {
    this.incident.setOpeningDate(openingDate);
    return this;
  }

  // al finalizar entrega 3 es abierto o cerrado
  public IncidentBuilder withState(Boolean state) {
    this.incident.setOpen(state);
    return this;
  }

  public Incident build() {
    return this.incident;
  }
}

/*TODO EJEMPLO DE USO
* Incident incident = new IncidentBuilder()
    .withAssociatedProvisionOfService(provisionOfService)
    .withCreator(creator)
    .withOpeningDescription(openingDescription)
    .build();
* */
