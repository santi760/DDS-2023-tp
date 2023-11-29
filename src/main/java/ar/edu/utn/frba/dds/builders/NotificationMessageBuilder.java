package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;

public class NotificationMessageBuilder {

  NotificationMessage notificationMessage = new NotificationMessage();

  public NotificationMessageBuilder withTitle(String title) {
    this.notificationMessage.setTitle(title);
    return this;
  }

  public NotificationMessageBuilder withBody(String body) {
    this.notificationMessage.setBody(body);
    return this;
  }

  public NotificationMessageBuilder withIncident(Incident incident) {
    this.notificationMessage.setIncident(incident);
    return this;
  }

  //* ---------------- BUILD ---------------- *//

  public NotificationMessage build() {
    //* agregamos las validaciones que querramos... si hace falta...
    return this.notificationMessage;
  }
}

//* ejemplo de uso:
/**
 * NotificationMessage notificationMessage = new NotificationMessageBuilder()
 *         .withTitle("Título")
 *         .withBody("Cuerpo del mensaje")
 *         .withIncident(incident)
 *         .build();
 * */
