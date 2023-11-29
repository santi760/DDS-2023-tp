package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.DefinedMoments;
import ar.edu.utn.frba.dds.models.community.notification_schedule.right_now.RightNow;
import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.users.ControlOrganismRepository;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest {


    @Test
    public void ControlOrganismTest(){
        Long n = 1L;
        ControlOrganism controlOrganism = ControlOrganismRepository.getInstance().read(n);
        assertEquals(3, ProviderEntityRepository.getInstance().readNotIncludedOrganism(controlOrganism).size());
        assertEquals("AFIP", controlOrganism.getName());
    }

    @Test
    public void ProviderEntityTest(){
        Long n = 2L;
        ProviderEntity providerEntity = ProviderEntityRepository.getInstance().read(n);

        ProviderEntityRepository.getInstance().removeOrganismFromProvider(providerEntity);
        assertEquals(3,ProviderEntityRepository.getInstance().readAll().size());
        assertEquals("Trenes Argentinos", providerEntity.getName());
    }

    @Test
    public void UserTest(){
        Long n = 1L;
        User user = UserRepository.getInstance().read(n);

        assertEquals("carlos", user.getName());
        assertEquals(EmailSender.class, user.getNotificationChannel().getClass());
        assertEquals(RightNow.class, user.getNotificationSchedule().getClass());
    }


}
