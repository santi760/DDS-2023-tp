package ar.edu.utn.frba.dds.models.community_member;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.CommunityMemberRepository;
import ar.edu.utn.frba.dds.repositories.entities.CommunityRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue
  Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surname")
  private String surname;

  @Column(name = "email")
  private String email;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "province_id", referencedColumnName = "province_id")
  private Province province;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "department_id", referencedColumnName = "department_id")
  private Department department;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "municipality_id", referencedColumnName = "municipality_id")
  private Municipality municipality;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "locality_id", referencedColumnName = "locality_id")
  private Locality locality;

   @OneToMany(mappedBy = "person")
   List<CommunityMember> communityMembers = new ArrayList<CommunityMember>();

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;

  public Person() {

  }

  public Person(User user){
    this.user = user;
  }

  public void add(CommunityMember communityMember) {
    this.communityMembers.add(communityMember);
  }


  public  List<Community> getCommunitiesOfMyMembers(Long personId){
    List<CommunityMember> myCommunityMembers = CommunityMemberRepository.getInstance().readCommunityMembers(personId);

    List<Community> communities = CommunityRepository.getInstance().readAll();
    List<Community> myCommunities = new ArrayList<>();
    for (Community community : communities) { // asumimos que existe   un método para obtener todas las comunidades
      for (CommunityMember member : community.getMembers()) {
        if (myCommunityMembers.stream().anyMatch(m -> m.getId() == member.getId())) {
          myCommunities.add(community);
          break;
        }
      }
    }
    return myCommunities;
  }
}
