package ar.edu.utn.frba.dds.models.community;

import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.DefinedMoments;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.CronTaskNotificate;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MemberTest {


  @Test
  public void AddServiceOfInterest(){

    Person person = new Person();
    person.setName("Nacho");
    person.setSurname("Incognito");
    person.setPhoneNumber("5491123185162");
    person.setEmail("nacho@gmail.com");



    CommunityMember nacho = new CommunityMember();
    nacho.setPerson(person);

    Service banioHombre = new Service("Baño Hombre");
    Service banioMujer = new Service("Baño Mujer");

    nacho.addInterestingService(banioHombre,banioMujer);

    assertEquals(2,nacho.getInterestingServices().size());
  }

  @Test
  public void recibirNotificacionSegunMomentoAElegir() throws InterruptedException {

    Service servicioBaños = new Service("Baños");
    Service servicioEscaleras = new Service("Servicio Escaleras");
    User user = new User("user1", "pepito");
    Person person = new Person();
    Establishment estacionMedrano = new Establishment();
    NotificationMessage notification = new NotificationMessage();

    person.setName("Nacho");
    person.setSurname("Borda");
    person.setPhoneNumber("5491123185162");
    person.setEmail("nicogalfione@gmail.com");

    CommunityMember miembroNacho =  new CommunityMember();
    miembroNacho.setPerson(person);
    miembroNacho.getPerson().setUser(user);

    EmailSender emailSender = mock(EmailSender.class);
    emailSender.notificate(notification,miembroNacho);
    miembroNacho.getPerson().getUser().setNotificationSchedule(new DefinedMoments());
    miembroNacho.getPerson().getUser().setNotificationChannel(emailSender);

    List<Integer> horariosNotificar = new ArrayList<>();
    horariosNotificar.add(14);
    horariosNotificar.add(11);
    horariosNotificar.add(12);
    miembroNacho.setMomentsNotificate(horariosNotificar);

    ProvisionOfService provisionOfService = new ProvisionOfService("Provision 1",servicioBaños,estacionMedrano);
    ProvisionOfService provisionOfService2 = new ProvisionOfService("Provision 2",servicioEscaleras,estacionMedrano);
    ProvisionOfService provisionOfService3 = new ProvisionOfService("Provision 3",servicioEscaleras,estacionMedrano);
    ProvisionOfService provisionOfService4 = new ProvisionOfService("Provision 4",servicioBaños,estacionMedrano);
    Incident incidentDentro24hs = new Incident(provisionOfService,miembroNacho,"se rompio un baño");
    Incident incidentDentro24hs2 = new Incident(provisionOfService2,miembroNacho,"se rompio la escalera");
    Incident incidentNoDentro24hs = new Incident(provisionOfService3,miembroNacho,"se rompio otra escalera");
    Community comunidadGrupo2 = new Community("comunidadGrupo2");
    comunidadGrupo2.addCommunityMember(miembroNacho);

    incidentNoDentro24hs.setOpeningDate(LocalDateTime.of(2023,7,10,0,0));
    incidentDentro24hs.setOpeningDate(LocalDateTime.of(2023,7,11,20,0));
    incidentDentro24hs2.setOpeningDate(LocalDateTime.of(2023,7,11,20,0));


    comunidadGrupo2.addIncidentAndNotifyUsers(incidentDentro24hs);
    comunidadGrupo2.addIncidentAndNotifyUsers(incidentDentro24hs2);
    comunidadGrupo2.addIncidentAndNotifyUsers(incidentNoDentro24hs);

    CronTaskNotificate.getInstance().setTimeInMillisecondsUntilNextActivation(30000);
    CronTaskNotificate.getInstance().start();

    Thread.sleep(60000);
    //Incident incidentDentro24hs3 = new Incident(provisionOfService4,miembroNacho,"se rompio otro baño");
    //incidentDentro24hs3.setOpeningDate(LocalDateTime.of(2023,7,12,9,0));
    //comunidadGrupo2.addIncidentAndNotifyUsers(incidentDentro24hs3);
    //Thread.sleep(90000);
    CronTaskNotificate.getInstance().cancelCrontask();

  }



}