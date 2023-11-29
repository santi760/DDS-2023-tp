package ar.edu.utn.frba.dds.models.community;

import ar.edu.utn.frba.dds.builders.NotificationMessageBuilder;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.proximity_finder.CustomProximityFinder;
import ar.edu.utn.frba.dds.models.services.Service;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "community")
public class Community {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "community_x_service",
          joinColumns = @JoinColumn(name = "id_community", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "id_service", referencedColumnName = "id")
  )
  private Set<Service> interetingSerices = new HashSet<Service>();    //TODO REFACTOR DE NOMBRE

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "community_x_establishment",
          joinColumns = @JoinColumn(name = "id_community", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "id_establishment", referencedColumnName = "id")
  )
  private Set<Establishment> interetingEstablishment = new HashSet<Establishment>();


  @OneToMany
  @JoinColumn(name = "community_id", referencedColumnName = "id")
  private Set<CommunityMember> members = new HashSet<CommunityMember>();

  @OneToMany
  @JoinColumn(name = "community_id", referencedColumnName = "id")
  private Set<Incident> openIncidents = new HashSet<Incident>();

  @OneToMany
  @JoinColumn(name = "community_id", referencedColumnName = "id")
  private Set<Incident> closedIncidents = new HashSet<Incident>();


  @Transient
  private CustomProximityFinder customProximityFinder; // cada comunidad especifica su nivel de busqueda de cercania para notificar revisiones de incidentes


  //! agregados por la integracion a API servicio 01
  @Column(name = "degreeOfConfidence")
  private Double degreeOfConfidence;

  //! agregado por la integracion a la API servicio 01
  @Column(name = "lastTimeMerged")
  private LocalDate lastTimeMerged;

  //** ----------- CONSTRUCTORS ----------------

  public Community(String name) {
    this.name = name;
  }

  public Community() {

  }


  //** --------------------------------- METHODS ----------------------------------------------------------------

  public void removeInteretingServices(Service... services) {
    this.interetingSerices.removeAll(Arrays.asList(services));
  }

  public void removeInteretingEstablishments(Establishment... establishments) {
    this.interetingEstablishment.removeAll(Arrays.asList(establishments));
  }

  public void addOpenIncidents(Incident... incidents) {
    this.openIncidents.addAll(Arrays.asList(incidents));
  }
  public void addClosedIncidents(Incident... incidents) {
    this.closedIncidents.addAll(Arrays.asList(incidents));
  }

  public void addCommunityMember(CommunityMember... members) {
    this.members.addAll(Arrays.asList(members));
  }

  public void addInteretingServices(Service... services) {
    this.interetingSerices.addAll(Arrays.asList(services));
  }

  public void addInteretingEstablishments(Establishment... establishments) { this.interetingEstablishment.addAll(Arrays.asList(establishments)); }


  public void addIncidentAndNotifyUsers(Incident newIncident) {
    openIncidents.add(newIncident);

    NotificationMessage notification = new NotificationMessageBuilder()
        .withTitle("Apertura de incidente")
        .withBody("Se abrio un incidente en " +  newIncident.getAssociatedProvisionOfService().getName())
        .withIncident(newIncident)
        .build();

    //todo: esto me explota

    try {
      this.members.parallelStream().forEach(member -> member.notificate(notification));
    } catch (Exception e) {

    }
  }


  public void closeIncidentAndNotifyMembers(Incident incident, CommunityMember closerMember, String closeObservation){
    incident.close(closerMember,closeObservation);
    this.openIncidents.remove(incident);
    this.closedIncidents.add(incident);

    NotificationMessage notification = new NotificationMessageBuilder()
        .withTitle("Cierre de incidente")
        .withBody("\"Se cerro el incidente en \"" +  incident.getAssociatedProvisionOfService().getName())
        .build();

    try {
      this.members.parallelStream().forEach(member -> member.notificate(notification));
    } catch (Exception e) {

    }

    //todo se podria hacer algo como? se notifica a todos los usuarios que se cerro el incidente -> NotificadorDeIncidentes.notificar()
  }


  public void suggestIncidentReviewToCloseMember() {
    for (CommunityMember member : members) {
      boolean isNearby = false; // Flag to indicate if the member is nearby an incident
      //*? estaria bien pasar el miembro? o deberiamos pasar una lista de ubicaicones a comparar?
      for (Incident incident : openIncidents) {
        if (customProximityFinder.memberIsCloser(member, incident)) {

          NotificationMessage notification = new NotificationMessageBuilder()
              .withTitle("A muchos usuarios les gustaria saber tu opinion: cual es el estado del incidente")
              .withBody("Comentanos el estado del incidente en" + incident.getAssociatedProvisionOfService().getName())
              .build();

          member.getPerson().getUser().getNotificationChannel().notificate(notification,member);
          isNearby = true;
          break; // Exit the inner loop once a nearby incident is found for the member
        }
      }
    }
  }

  public void removeMember(CommunityMember member) {
    this.members.remove(member);
  }

}// end community
