package ar.edu.utn.frba.dds.models.community;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "incident")
public class Incident {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  @JoinColumn(name = "associatedProvisionOfService_id", referencedColumnName = "id")
  private ProvisionOfService associatedProvisionOfService;

  @Column(name = "open")
  private Boolean open;

  @OneToOne
  @JoinColumn(name = "communityMemberCreator_id", referencedColumnName = "id")
  private CommunityMember creator;

  @OneToOne
  @JoinColumn(name = "communityMemberCloser_id", referencedColumnName = "id")
  private CommunityMember closer = null;

  @Column(name = "openingDate")
  private LocalDateTime openingDate; // 2023-06-18T23:15:33.698055600 ejemplo de como se guarda

  @Column(name = "closingDate")
  private LocalDateTime closingDate = null;

  @Column(name = "openingDescription", columnDefinition = "longtext")
  private String openingDescription = null;

  @Column(name = "closedDescription", columnDefinition = "longtext")
  private String closedDescription = null;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "community_id", referencedColumnName = "id")
  private Community community;

  //*  -------------------------------------- CONSTRUCTORS --------------------------------------  *//

    // TODO posible factory para instanciar los incidentes

  public Incident() {
    this.open = true;
    this.openingDate = LocalDateTime.now();
  }


  public Incident(ProvisionOfService associatedProvisionOfService, CommunityMember creator, String openingDescription) {
    this.open = true;
    this.openingDate = LocalDateTime.now();
    this.associatedProvisionOfService = associatedProvisionOfService;
    this.creator = creator;
    this.openingDescription = openingDescription;

  }

  public static void main(String[] args) {
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println("Fecha y hora actual: " + localDateTime);
  }

  //*  -------------------------------------- FUNCTIONS --------------------------------------  *//

  /**
   * Para marcar como resuelto un incidente
   *
   * @param closer Mimebro que cierra el incidente
   * @param closeDescription Descripcion para el cierre del incidente
   *
   * @return void
   */
  public void close(CommunityMember closer, String closeDescription){

    this.closer = closer;
    this.closedDescription = closedDescription;

    this.open = false;
    this.closingDate = LocalDateTime.now();
  }


  //* ------------- OBTENER UBICACION DEL INCIDENTE ------------- *//

  public Province getProvince(){
    return this.associatedProvisionOfService.getEstablishment().getProvince();
  }


  public Department getDepartment(){
    return this.associatedProvisionOfService.getEstablishment().getDepartment();
  }


  public Municipality getMunicipality(){
    return this.associatedProvisionOfService.getEstablishment().getMunicipality();
  }


  public Locality getLocality(){
    return this.associatedProvisionOfService.getEstablishment().getLocality();
  }


  //* ------------- CIERRE DE INCIDENTE ------------- *//


  public static Duration calculateDuration(LocalDateTime openingDate, LocalDateTime closingDate) {
    return Duration.between(openingDate, closingDate);
  }

  //* ------------- DIFERENCIA ENTRE APERTURA Y CIERRE DE INCIDENTE ------------- *//

  public long getHoursDifferenceOpenClose(){
    return ChronoUnit.HOURS.between(this.openingDate, this.closingDate);
  }

  public long getDaysDifferenceOpenClose(){
    return ChronoUnit.DAYS.between(this.openingDate, this.closingDate);
  }

  public long getWeeksDifferenceOpenClose(){
    return ChronoUnit.WEEKS.between(this.openingDate, this.closingDate);
  }


  //* para saber si un incidente se abrio hace menos de 24 hs

  public Boolean isWithin24Hours() {

    LocalDateTime currentDate = LocalDateTime.now();
    Duration duracion = Duration.between(this.getOpeningDate(), currentDate);
    return duracion.toHours() < 24;
  }

  public Boolean isFromThisWeek(){

    return ChronoUnit.WEEKS.between(this.openingDate, LocalDateTime.now()) == 0;
  }

  public Boolean equalsAnotherIncidentFromSameProvsionOfService(List<Incident> incidents){

    return incidents.stream().anyMatch(incidentFromList ->
            incidentFromList.getAssociatedProvisionOfService().equals(this.getAssociatedProvisionOfService())
                    && ChronoUnit.HOURS.between(this.getOpeningDate(), incidentFromList.getOpeningDate()) < 24
                    && (this.getOpen() && incidentFromList.getOpen()));
  }

}
