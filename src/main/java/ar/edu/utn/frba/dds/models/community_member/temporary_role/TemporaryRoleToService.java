package ar.edu.utn.frba.dds.models.community_member.temporary_role;

import ar.edu.utn.frba.dds.models.services.Service;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "temporaryRoleToService")
public class TemporaryRoleToService {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "service_id", referencedColumnName = "id")
  Service service;

  @Enumerated(EnumType.STRING)
  @Column(name = "temporaryRole")
  TemporaryRole temporaryRole;


}
