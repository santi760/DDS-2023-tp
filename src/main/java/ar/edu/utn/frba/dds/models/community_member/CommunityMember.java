package ar.edu.utn.frba.dds.models.community_member;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.community_member.temporary_role.TemporaryRoleToService;
import ar.edu.utn.frba.dds.repositories.entities.CommunityRepository;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@javax.persistence.Entity
@Table(name = "communitymember")
public class CommunityMember {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
  private Person person;

  @ElementCollection
  @CollectionTable(name = "MomentsNotificate", joinColumns = @JoinColumn(name = "communityMember_id", referencedColumnName = "id"))
  @Column(name = "momentsNotificate", nullable = true)
  private List<Integer> MomentsNotificate = new ArrayList<Integer>();

  @Enumerated(EnumType.STRING)
  @Column(name = "rolInCommunity", nullable = false)
  private RolInCommunity rolInCommunity;

  @ManyToMany
  @JoinTable(
      name = "Interesting_Servicie",
      joinColumns = @JoinColumn(name = "id_communityMember", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "id_service", referencedColumnName = "id")
  )
  private Set<Service> interestingServices = new HashSet<Service>();

  @ManyToMany
  @JoinTable(
      name = "CommunityMember_X_Entity",
      joinColumns = @JoinColumn(name = "id_communityMember", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "id_entity", referencedColumnName = "id")
  )
  private Set<Entity> interestingEntities = new HashSet<Entity>();

  @ManyToMany
  @JoinTable(
      name = "TemporalRol_CommunityMember",
      joinColumns = @JoinColumn(name = "CommunityMember_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "TemporalRol_id", referencedColumnName = "id")
  )
  private Set<TemporaryRoleToService> temporaryRoles = new HashSet<TemporaryRoleToService>();

  @Column(name = "degreeOfConfidence", nullable = true)
  private Double degreeOfConfidence;


  //** ----------- CONSTRUCTORS ----------------

  public CommunityMember() {

  }


  /*public CommunityMember(String name, String surname, String phoneNumber,String email) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }*/


  //** ----------- METHODS ----------------


  // todo revisar si rompen con el set
  public void addInterestingService(Service... services) {
    this.interestingServices.addAll(Arrays.asList(services));
  }


  public void addInterestingEntity(Entity... entities) {
    this.interestingEntities.addAll(Arrays.asList(entities));
  }

  public void addMomentNotificate(Integer... moments) {
    this.MomentsNotificate.addAll(Arrays.asList(moments));
  }

  public void notificate(NotificationMessage notification) {
    this.getPerson().getUser().getNotificationSchedule().sendNotificationIfScheduleMatches(notification, this);
  }

  public void addTemporaryRoletoService(TemporaryRoleToService... temporaryRoleToServices) {
    this.temporaryRoles.addAll(Arrays.asList(temporaryRoleToServices));
  }

  // TODO: CUANDO HACEMOS SET DE UN NOTIFICATOIN SCHEDULE HAY QUE SACARLO DE LA COLA DE QUEUETONOTIFICATE


  //NOTA: DESDE LA ESTAPA DE CONTROLADORES SE VA A LLAMAR A TODAS LAS COMUNIDADES DEL MIEMBRO Y SE LAS VA A NOTIFICAR DESDE AHI.

  public List<Community> getCommunities() {
    CommunityRepository communityRepository = CommunityRepository.getInstance();
    List<Community> myCommunities = communityRepository.readAllForMember(this.id);
    return myCommunities;
  }
} // fin clase
