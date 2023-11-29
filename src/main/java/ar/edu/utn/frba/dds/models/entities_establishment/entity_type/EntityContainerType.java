package ar.edu.utn.frba.dds.models.entities_establishment.entity_type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Embeddable
public class EntityContainerType {


  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  EntityType type;

  @Enumerated(EnumType.STRING)
  @Column(name = "typePublicTransport")
  PublicTransportTpye typePublicTransport;


  public EntityContainerType(EntityType type, PublicTransportTpye typePublicTransport) {
    this.type = type;
    this.typePublicTransport = typePublicTransport;
  }

  public EntityContainerType(EntityType type) {
    this.type = type;
    this.typePublicTransport = null;
  }

  public EntityContainerType() {

  }



}
