package ar.edu.utn.frba.dds.models.api_fusion;

import ar.edu.utn.frba.dds.builders.CommunityBuilder;
import ar.edu.utn.frba.dds.builders.CommunityMemberBuilder;
import ar.edu.utn.frba.dds.builders.users_roles.UserBuilder;
import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunitiesToMerge;
import ar.edu.utn.frba.dds.models.api_fusion.entities.MergedCommunity;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.User;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType.STATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FusionTest {
    ApiFusionService apiFusionService = ApiFusionService.getInstance();

    @Test
    public void communityFusion() throws IOException {

        Establishment establishment1 = new Establishment("Angel Gallardo",STATION);
        Establishment establishment2 = new Establishment("Angel Gallardo2",STATION);
        Establishment establishment3 = new Establishment("Angel Gallardo3",STATION);

        establishment1.setId(1l);
        establishment2.setId(2l);
        establishment3.setId(3l);

        Service service_bathroom = new Service("banio");
        Service service_elevator = new Service("ascensor");
        Service service_mechanical_stair = new Service("escalera mecanica");
        Service service_stair = new Service("escalera");
        Service service_ramp = new Service("rampa");

        service_bathroom.setId(1l);
        service_elevator.setId(2l);
        service_mechanical_stair.setId(3l);
        service_stair.setId(4l);
        service_ramp.setId(5l);

        Entity subteB = new Entity();

        User user_carlos = new UserBuilder().
                withname("carlos").
                withpassword("123").
                build();


        User user_juan = new  UserBuilder().
                withname("juan").
                withpassword("123").
                build();

        User user_pablo = new  UserBuilder().
                withname("pablo").
                withpassword("123").
                build();

        CommunityMember member_01 = new CommunityMemberBuilder().
                withInterestingServices(service_bathroom,service_elevator).
                withInterestingEntities(subteB).

                build();

        CommunityMember member_02 = new CommunityMemberBuilder().
                withInterestingServices(service_bathroom,service_elevator).
                withInterestingEntities(subteB).

                build();

        CommunityMember member_03 = new CommunityMemberBuilder().
                withInterestingServices(service_bathroom,service_elevator).
                withInterestingEntities(subteB).
                build();

        member_01.setId(1l);
        member_02.setId(2l);
        member_03.setId(3l);

        Person person = new Person();
        person.setName("miembro");

        member_01.setPerson(person);
        member_02.setPerson(person);
        member_03.setPerson(person);

        Community communityPregnants = new CommunityBuilder().
                withName("Embarazadas").
                withMembers(member_01,member_02,member_03).
                withInteretingServices(service_mechanical_stair, service_ramp, service_bathroom).
                build();


        Community communityWheelchair = new CommunityBuilder().
                withName("Silla de ruedas").
                withMembers(member_01,member_02).
                withInteretingServices(service_ramp, service_bathroom,service_mechanical_stair,service_stair).
                build();

        communityWheelchair.setId(1L);
        communityPregnants.setId(2L);

        communityWheelchair.setDegreeOfConfidence(3.0);
        communityPregnants.setDegreeOfConfidence(3.0);

        LocalDate fechaPersonalizada = LocalDate.of(2022, 10, 13);
        communityWheelchair.setLastTimeMerged(fechaPersonalizada);
        communityPregnants.setLastTimeMerged(fechaPersonalizada);

        communityPregnants.addInteretingEstablishments(establishment1);
        communityPregnants.addInteretingEstablishments(establishment2);
        communityPregnants.addInteretingEstablishments(establishment3);

        communityWheelchair.addInteretingEstablishments(establishment1);
        communityWheelchair.addInteretingEstablishments(establishment2);
        communityWheelchair.addInteretingEstablishments(establishment3);

        AdapterApiFusion adapterApiFusion = new AdapterApiFusion();


        //* ---------------------- TEST -------------------------- *//
        MergedCommunity communityMerge = adapterApiFusion.communityMerged(communityWheelchair,communityPregnants);
        CommunitiesToMerge communities = adapterApiFusion.communityMerged2(communityWheelchair,communityPregnants);

        System.out.println(communityMerge);
        System.out.println(communities);


    }
}
