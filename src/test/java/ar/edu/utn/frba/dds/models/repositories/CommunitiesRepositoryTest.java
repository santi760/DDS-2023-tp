package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.repositories.entities.CommunityMemberRepository;
import ar.edu.utn.frba.dds.repositories.entities.CommunityRepository;
import org.junit.jupiter.api.Test;

public class CommunitiesRepositoryTest {

  @Test
  public void CommunityMemeberTest(){
    Long n = 1L;
    CommunityMember member01 = CommunityMemberRepository.getInstance().read(n);
    n = 2L;
    CommunityMember member02 = CommunityMemberRepository.getInstance().read(n);
    n = 3L;
    CommunityMember member03 = CommunityMemberRepository.getInstance().read(n);

    assertEquals(member01.getPerson().getName(),"Carlos");
    assertEquals(member01.getPerson().getEmail(),"calitosRodriguez@gmail.com");
    assertEquals(member01.getPerson().getDepartment().getId(),1);

    assertEquals(member02.getPerson().getName(),"Juan");
    assertEquals(member02.getPerson().getEmail(),"juanArrascaeta@gmail.com");
    assertEquals(member02.getPerson().getDepartment().getId(),2);

    assertEquals(member03.getPerson().getName(),"Pablo");
    assertEquals(member03.getPerson().getEmail(),"pabloGiralt@gmail.com");
    assertEquals(member03.getPerson().getDepartment().getId(),3);
  }

  @Test
  public void CommunitiesTest(){
    Long n = 1L;
    Community embarazadas = CommunityRepository.getInstance().read(n);
    n = 2L;
    Community sillaRuedas = CommunityRepository.getInstance().read(n);
    n = 3L;
    Community viajerosSubte = CommunityRepository.getInstance().read(n);

    assertEquals(embarazadas.getName(),"Embarazadas");
    assertEquals(sillaRuedas.getName(),"Silla de ruedas");
    assertEquals(viajerosSubte.getName(),"Viajeros de subte");

  }



}
