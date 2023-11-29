package ar.edu.utn.frba.dds.models.community;

import static org.junit.jupiter.api.Assertions.*;


import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.locations.*;
import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp.WhatsAppSender;
import ar.edu.utn.frba.dds.models.community.notification_schedule.right_now.RightNow;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.proximity_finder.CustomProximityFinder;
import ar.edu.utn.frba.dds.models.proximity_finder.searchers.DepartmentSearcher;
import ar.edu.utn.frba.dds.models.proximity_finder.searchers.LocalitySearcher;
import ar.edu.utn.frba.dds.models.proximity_finder.searchers.MunicipalitySearcher;
import ar.edu.utn.frba.dds.models.proximity_finder.searchers.ProvinceSearcher;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class CommunityTest {




  @BeforeEach
  public void init(){

  }


  @Test
  public void aperturaDeIncidenteEnComunidad(){

      User user = new User("user1", "pepito");
      Service servicioBaños = new Service("Baños");
      Service servicioEscaleras = new Service("Servicio Escaleras");
      NotificationMessage notification = new NotificationMessage();

      Establishment estacionMedrano = new Establishment();

      Person person = new Person();
      person.setName("Nacho");
      person.setSurname("Borda");
      person.setPhoneNumber("5491123185162");
      person.setEmail("nachoborda50@gmail.com");

      CommunityMember miembroNacho =  new CommunityMember();
      miembroNacho.setPerson(person);
      miembroNacho.getPerson().setUser(user);
      WhatsAppSender whatsAppSender = mock(WhatsAppSender.class);
      whatsAppSender.notificate(notification,miembroNacho);
      miembroNacho.getPerson().getUser().setNotificationSchedule(new RightNow());
      miembroNacho.getPerson().getUser().setNotificationChannel(whatsAppSender);




      ProvisionOfService provisionOfService = new ProvisionOfService("Provision 1",servicioBaños,estacionMedrano);
      Incident incident = new Incident(provisionOfService,miembroNacho,"se rompio un baño");


      Community comunidadGrupo2 = new Community("comunidadGrupo2");
      comunidadGrupo2.addCommunityMember(miembroNacho);

      //* ---------------------- TEST -------------------------- *//

    comunidadGrupo2.addIncidentAndNotifyUsers(incident);


    assertEquals(1, comunidadGrupo2.getOpenIncidents().size());
    assertEquals(0, comunidadGrupo2.getClosedIncidents().size());
    assertEquals(true, incident.getOpen());

  }

  @Test
  public void cierreDeIncidenteEnComunidad(){
    Service servicioBaños = new Service("Baños");
    Service servicioEscaleras = new Service("Servicio Escaleras");
      User user = new User("user1", "pepito");

    Establishment estacionMedrano = new Establishment();
    NotificationMessage notification = new NotificationMessage();

    Person person = new Person();
    person.setName("Nacho");
    person.setSurname("Borda");
    person.setPhoneNumber("5491123185162");
    person.setEmail("nicogalfione@gmail.com");
    person.setUser(user);

    CommunityMember miembroNacho =  new CommunityMember();
    miembroNacho.setPerson(person);
    EmailSender emailSender = mock(EmailSender.class);
    emailSender.notificate(notification,miembroNacho);
    miembroNacho.getPerson().getUser().setNotificationSchedule(new RightNow());
    miembroNacho.getPerson().getUser().setNotificationChannel(emailSender);


    ProvisionOfService provisionOfService = new ProvisionOfService("Provision 1",servicioBaños,estacionMedrano);
    Incident incident = new Incident(provisionOfService,miembroNacho,"se rompio un baño");


    Community comunidadGrupo2 = new Community("comunidadGrupo2");
    comunidadGrupo2.addCommunityMember(miembroNacho);

    //* ---------------------- TEST -------------------------- *//

    comunidadGrupo2.closeIncidentAndNotifyMembers(incident,miembroNacho,"hola");


    assertEquals(0, comunidadGrupo2.getOpenIncidents().size());
    assertEquals(1, comunidadGrupo2.getClosedIncidents().size());
    assertEquals(false, incident.getOpen());


  }


  @Test
  public void sugerenciaRevisionDeIncidenteTest(){
      Service servicioBaños = new Service("Baños");
      Service servicioEscaleras = new Service("Servicio Escaleras");
      Establishment estacionMedrano = new Establishment();
      Province provincia = new Province("Chaco");
      Province provincia2 = new Province("Buenos Aires");
      Locality locality = new Locality("Localidad");
      Locality locality2 = new Locality("Localidad2");
      Municipality municipality = new Municipality("Municipalidad");
      Municipality municipality2 = new Municipality("Municipalidad2");
      Department department = new Department("Departamento");
      Department department2 = new Department("Departamento2");
      NotificationMessage notification = new NotificationMessage();

      estacionMedrano.setProvince(provincia);
      estacionMedrano.setDepartment(department);
      estacionMedrano.setLocality(locality);
      estacionMedrano.setMunicipality(municipality);

      User user = new User("user1", "pepito");
      User user2 = new User("user2", "pepito");

    Person person = new Person();
    person.setName("Nacho");
    person.setSurname("Borda");
    person.setPhoneNumber("5491123185162");
    person.setEmail("nicogalfione@gmail.com");
    person.setUser(user);
    person.setProvince(provincia);
    person.setLocality(locality);
    person.setDepartment(department);
    person.setMunicipality(municipality);



      CommunityMember miembroNacho =  new CommunityMember();
      miembroNacho.setPerson(person);
      EmailSender emailSender = mock(EmailSender.class);
      emailSender.notificate(notification,miembroNacho);
      miembroNacho.getPerson().getUser().setNotificationSchedule(new RightNow());
      miembroNacho.getPerson().getUser().setNotificationChannel(emailSender);


    Person person1 = new Person();
    person1.setName("Santi");
    person1.setSurname("Arrascaeta");
    person1.setPhoneNumber("5491123185162");
    person1.setEmail("nicogalfione@gmail.com");
    person1.setUser(user2);
    person1.setProvince(provincia2);
    person1.setLocality(locality2);
    person1.setDepartment(department2);
    person1.setMunicipality(municipality2);


      CommunityMember miembroSanti =  new CommunityMember();
      miembroSanti.setPerson(person1);
      WhatsAppSender whatsAppSender = mock(WhatsAppSender.class);
      whatsAppSender.notificate(notification,miembroNacho);
      miembroSanti.getPerson().getUser().setNotificationSchedule(new RightNow());
      miembroSanti.getPerson().getUser().setNotificationChannel(whatsAppSender);


      ProvisionOfService provisionOfService = new ProvisionOfService("Provision 1",servicioBaños,estacionMedrano);
      Incident incident = new Incident(provisionOfService,miembroNacho,"se rompio un baño");


      Community comunidadGrupo2 = new Community("comunidadGrupo2");
      comunidadGrupo2.addCommunityMember(miembroNacho);
      comunidadGrupo2.addCommunityMember(miembroSanti);
      comunidadGrupo2.getOpenIncidents().add(incident);

      System.out.println(miembroNacho.getPerson().getLocality().getName());


      ProvinceSearcher provinceSearcher = new ProvinceSearcher();
      LocalitySearcher localitySearcher = new LocalitySearcher();
      DepartmentSearcher departmentSearcher = new DepartmentSearcher();
      MunicipalitySearcher municipalitySearcher = new MunicipalitySearcher();
      comunidadGrupo2.setCustomProximityFinder(new CustomProximityFinder());
      comunidadGrupo2.getCustomProximityFinder().getProximityFinders().add(provinceSearcher);
      comunidadGrupo2.getCustomProximityFinder().getProximityFinders().add(localitySearcher);
      comunidadGrupo2.getCustomProximityFinder().getProximityFinders().add(departmentSearcher);
      comunidadGrupo2.getCustomProximityFinder().getProximityFinders().add(municipalitySearcher);

      comunidadGrupo2.suggestIncidentReviewToCloseMember();



  }


}