package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.builders.*;
import ar.edu.utn.frba.dds.builders.users_roles.UserBuilder;
import ar.edu.utn.frba.dds.factories.LocationsFactory;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.models.community.notification_channel.NotificationChannel;
import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp.WhatsAppSender;
import ar.edu.utn.frba.dds.models.community.notification_schedule.NotificationSchedule;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.DefinedMoments;
import ar.edu.utn.frba.dds.models.community.notification_schedule.right_now.RightNow;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.community_member.RolInCommunity;
import ar.edu.utn.frba.dds.models.community_member.temporary_role.TemporaryRole;
import ar.edu.utn.frba.dds.models.community_member.temporary_role.TemporaryRoleToService;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityContainerType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.PublicTransportTpye;
import ar.edu.utn.frba.dds.models.georef.Importer;
import ar.edu.utn.frba.dds.models.locations.*;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.models.users.RolType;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.*;
import ar.edu.utn.frba.dds.repositories.entities.locations.DepartmentRepository;
import ar.edu.utn.frba.dds.repositories.users.ControlOrganismRepository;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Instanciado implements WithSimplePersistenceUnit {

  public static void main(String[] args) throws IOException {
    new Instanciado().run();
  }

  public void run() throws IOException {
    //* --------------------------------------- LOCATIONS  ---------------------------------------*/

    Province province01 = LocationsFactory.createProvince(1, "Buenos Aires");
    Province province02 = LocationsFactory.createProvince(2, "Córdoba");
    Province province03 = LocationsFactory.createProvince(3, "Mendoza");
    Province province04 = LocationsFactory.createProvince(4, "San Juan");
    Province province05 = LocationsFactory.createProvince(5, "Formosa");

    Municipality municipality01 = LocationsFactory.createMunicipality(1, "La Matanza");
    Municipality municipality02 = LocationsFactory.createMunicipality(2, "Capital");
    Municipality municipality03 = LocationsFactory.createMunicipality(3, "Lanus");
    Municipality municipality04 = LocationsFactory.createMunicipality(4, "Chacabuco");
    Municipality municipality05 = LocationsFactory.createMunicipality(5, "Portobelo");


    Department department01 = LocationsFactory.createDepartment(1, "La Matanza");
    Department department02 = LocationsFactory.createDepartment(2, "Capital");
    Department department03 = LocationsFactory.createDepartment(3, "Groenlandia");
    Department department04 = LocationsFactory.createDepartment(4, "Gorgonia");
    Department department05 = LocationsFactory.createDepartment(5, "Capital");


    Locality locality01 = LocationsFactory.createLocality("1", "Ciudad Evita");
    Locality locality02 = LocationsFactory.createLocality("2", "Córdoba City");
    Locality locality03 = LocationsFactory.createLocality("3", "Caballito");
    Locality locality04 = LocationsFactory.createLocality("4", "Hatulpa");
    Locality locality05 = LocationsFactory.createLocality("5", "Tuesto");


    Coordinate coordinate01 = LocationsFactory.createCoordinate(1, 1);
    Coordinate coordinate02 = LocationsFactory.createCoordinate(2, 2);
    Coordinate coordinate03 = LocationsFactory.createCoordinate(3, 3);
    Coordinate coordinate04 = LocationsFactory.createCoordinate(4, 4);
    Coordinate coordinate05 = LocationsFactory.createCoordinate(5, 5);


    locality01.setCoordinate(coordinate01);
    locality02.setCoordinate(coordinate02);
    locality03.setCoordinate(coordinate03);
    locality04.setCoordinate(coordinate04);
    locality05.setCoordinate(coordinate05);

    //* --------------------------------------- SERVICIOS ---------------------------------------*/

    Service serviceBathroom = new Service("Baños");
    Service serviceStair = new Service("Escalera");
    Service serviceMechanicalStair = new Service("Escalera Mecanica");
    Service serviceRamp = new Service("Rampa");
    Service serviceElevator = new Service("Ascensor");
    Service serviceParking = new Service("Estacionamiento");


    //* --------------------------------------- ESTABLISHMENT ---------------------------------------*/

    Establishment GallardoB = new EstablishmentBuilder().
        withName("Angel Gallardo").
        withDescription("Estacion de subte B").
        withEstablishmentType(EstablishmentType.STATION).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality01).
        build();

    Establishment MedranoB = new EstablishmentBuilder().
        withDescription("Estacion de subte B").
        withName("Medrano").
        withEstablishmentType(EstablishmentType.STATION).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality02).
        build();

    Establishment CarlosGardelB = new EstablishmentBuilder().
        withName("Carlos Gardel").
        withDescription("Estacion de subte B").
        withEstablishmentType(EstablishmentType.STATION).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality03).
        build();

    Establishment bcoFrancesAlmagro = new EstablishmentBuilder().
        withName("Sucursal Almagro").
        withDescription("Sucursal de banco Frances").
        withEstablishmentType(EstablishmentType.SUCURSAL).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality01).
        build();

    Establishment bcoFrancesBoedo = new EstablishmentBuilder().
        withName("Sucursal Boedo").
        withDescription("Sucursal de banco Frances").
        withEstablishmentType(EstablishmentType.SUCURSAL).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality02).
        build();

    Establishment bcoFrancesPalermo = new EstablishmentBuilder().
        withName("Sucursal Palermo").
        withDescription("Sucursal de banco Frances").
        withEstablishmentType(EstablishmentType.SUCURSAL).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality03).
        build();

    Establishment cotoCaballito = new EstablishmentBuilder().
        withName("Supermercado Coto").
        withDescription("Supermercado Coto").
        withEstablishmentType(EstablishmentType.SUCURSAL).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality01).
        build();

    Establishment cotoBoedo = new EstablishmentBuilder().
        withName("Supermercado Coto").
        withDescription("Supermercado Coto").
        withEstablishmentType(EstablishmentType.SUCURSAL).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality02).
        build();

    Establishment cotoAlmagro = new EstablishmentBuilder().
        withName("Supermercado Coto").
        withDescription("Supermercado Coto").
        withEstablishmentType(EstablishmentType.SUCURSAL).
        withProvince(province01).
        withMunicipality(municipality01).
        withDepartment(department01).
        withLocality(locality03).
        build();
    //*-------------------------------------- PROVISION OF SERVICE --------------------------------------//

    ProvisionOfService bañosDeGallardo = new ProvisionOfServiceBuilder().
        withName("Baños de Estacion Angel Gallardo subte B").
        withService(serviceBathroom).
        withEstablishment(GallardoB).
        build();

    ProvisionOfService bañosDeMedrano = new ProvisionOfServiceBuilder().
        withName("Ascensor de Medrano").
        withService(serviceElevator).
        withEstablishment(MedranoB).
        build();

    ProvisionOfService escalerasDeCarlosGardel = new ProvisionOfServiceBuilder().
        withName("Escaleras de Carlos Gardel").
        withService(serviceStair).
        withEstablishment(CarlosGardelB).
        build();

    ProvisionOfService estacionamientoCotoCaballito = new ProvisionOfServiceBuilder().
        withName("Estacionamiento del Coto Caballito").
        withService(serviceParking).
        withEstablishment(cotoCaballito).
        build();

    ProvisionOfService estacionamientoCotoBoedo = new ProvisionOfServiceBuilder().
        withName("Estacionamiento del Coto Boedo").
        withService(serviceParking).
        withEstablishment(cotoBoedo).
        build();

    ProvisionOfService estacionamientoCotoAlmagro = new ProvisionOfServiceBuilder().
        withName("Estacionamiento del Coto Almagro").
        withService(serviceParking).
        withEstablishment(cotoAlmagro).
        build();

    ProvisionOfService bañosDeSucursalAlmagro = new ProvisionOfServiceBuilder().
        withName("Baños del Banco frances de  Almagro").
        withService(serviceBathroom).
        withEstablishment(bcoFrancesAlmagro).
        build();

    ProvisionOfService bañosDeSucursalBoedo = new ProvisionOfServiceBuilder().
        withName("Baños del Banco frances de  Boedo").
        withService(serviceBathroom).
        withEstablishment(bcoFrancesBoedo).
        build();

    ProvisionOfService bañosDeSucursalPalermo = new ProvisionOfServiceBuilder().
        withName("Baños del Banco frances de  Palermo").
        withService(serviceBathroom).
        withEstablishment(bcoFrancesPalermo).
        build();

    ProvisionOfService escalerasMecanicasDeSucursalAlmagro = new ProvisionOfServiceBuilder().
        withName("Escaleras Mecanicas del Banco frances de Almagro").
        withService(serviceMechanicalStair).
        withEstablishment(bcoFrancesAlmagro).
        build();

    ProvisionOfService ascensorDeSucursalAlmagro = new ProvisionOfServiceBuilder().
        withName("Ascensor del Banco frances de Almagro").
        withService(serviceElevator).
        withEstablishment(bcoFrancesAlmagro).
        build();

    ProvisionOfService ascensorDeSucursalBoedo = new ProvisionOfServiceBuilder().
        withName("Ascensor del Banco frances de Boedo").
        withService(serviceElevator).
        withEstablishment(bcoFrancesBoedo).
        build();

    ProvisionOfService rampaDeSucursalBoedo = new ProvisionOfServiceBuilder().
        withName("Rampa del Banco frances de Boedo").
        withService(serviceRamp).
        withEstablishment(bcoFrancesBoedo).
        build();

    ProvisionOfService rampaDeSucursalPalermo = new ProvisionOfServiceBuilder().
        withName("Rampa del Banco frances de Palermo").
        withService(serviceRamp).
        withEstablishment(bcoFrancesPalermo).
        build();

    ProvisionOfService rampaDeCotoPalermo = new ProvisionOfServiceBuilder().
        withName("Rampa del Coto de Palermo").
        withService(serviceRamp).
        withEstablishment(cotoAlmagro).
        build();

    ProvisionOfService rampaDeCotoBoedo = new ProvisionOfServiceBuilder().
        withName("Rampa del Coto de Boedo").
        withService(serviceRamp).
        withEstablishment(cotoBoedo).
        build();

    //* --------------------------------------- CONTAINER ENTITY TYPE ---------------------------------------*/

    EntityContainerType publicTransportRailwayType = new EntityContainerType(EntityType.PUBLIC_TRANSPORT, PublicTransportTpye.RAILWAY);
    EntityContainerType publicTransportSubwayType = new EntityContainerType(EntityType.PUBLIC_TRANSPORT, PublicTransportTpye.SUBWAY);
    EntityContainerType organizationType = new EntityContainerType(EntityType.ORGANIZATION);

    //* --------------------------------------- ENTITY ---------------------------------------*/

    Entity trenSarmiento = new EntityBuilder().
        withName("Subte B").
        withEstablishments(GallardoB, MedranoB, CarlosGardelB).
        withTypeEntity(publicTransportSubwayType).
        build();

    Entity trenRoca = new EntityBuilder().
        withName("Tren Roca").
        withEstablishments(GallardoB, MedranoB, CarlosGardelB).
        withTypeEntity(publicTransportRailwayType).
        build();

    Entity bancoFrances = new EntityBuilder().
        withName("Banco Frances").
        withEstablishments(bcoFrancesAlmagro, bcoFrancesBoedo, bcoFrancesPalermo).
        withTypeEntity(organizationType).
        build();

    Entity coto = new EntityBuilder().
        withName("Coto").
        withEstablishments(cotoCaballito, cotoBoedo, cotoAlmagro).
        withTypeEntity(organizationType).
        build();


    //* ---------------------------------------  NOTIFICATIONS MOMENT ---------------------------------------*/

    // NOTIFICATION CHANNEL
    NotificationChannel channelEmail = new EmailSender();
    NotificationChannel channelWhatsapp = new WhatsAppSender();

    // MOMENT OF NOTIFICATION
    NotificationSchedule scheduleRightNow = new RightNow();
    NotificationSchedule scheduleDefinedMoments = new DefinedMoments();


    //* ---------------------------------------  USERS ---------------------------------------*/


    //? ----------------  COMMUNITY MEMBERS  ---------------- */
    User userNico = new UserBuilder().
        withname("Nicolas").
        withpassword("123").
        withNotificationChannel(channelEmail).
        withNotificationSchedule(scheduleRightNow).
        withRol(RolType.COMMUNITY_MEMBER).
        build();


    User userBorda = new UserBuilder().
        withname("Borda").
        withpassword("123").
        withNotificationChannel(channelEmail).
        withNotificationSchedule(scheduleRightNow).
        withRol(RolType.COMMUNITY_MEMBER).
        build();

    User userSanti = new UserBuilder().
        withname("santi").
        withpassword("123").
        withNotificationChannel(channelEmail).
        withNotificationSchedule(scheduleDefinedMoments).
        withRol(RolType.COMMUNITY_MEMBER).
        build();

    User userVilla = new UserBuilder().
        withname("villa").
        withpassword("123").
        withNotificationChannel(channelEmail).
        withNotificationSchedule(scheduleDefinedMoments).
        withRol(RolType.COMMUNITY_MEMBER).
        build();

    //? ----------------  PROVIDER Y CONTROL ORGANISM  ---------------- */

    User userTrenesArgentinos = new UserBuilder().
        withname("trenesArgentinos").
        withpassword("123").
        withRol(RolType.PROVIDER).
        build();

    User userCNRT = new UserBuilder().
        withname("CNRT").
        withpassword("123").
        withRol(RolType.CONTROL_ORGANISM).
        build();


    //* --------------------------------------- PROVIDER ENTITY  ---------------------------------------*/

    ProviderEntity bancoProvincia = new ProviderEntity("Banco Provincia");
    bancoProvincia.getEntities().add(bancoFrances);


    ProviderEntity trenesArgentinos = new ProviderEntity("Trenes Argentinos");
    trenesArgentinos.getEntities().add(trenRoca);
    trenesArgentinos.getEntities().add(trenSarmiento);
    trenesArgentinos.setUser(userTrenesArgentinos);

    ProviderEntity cotoSupermercado = new ProviderEntity("Coto");
    cotoSupermercado.getEntities().add(coto);

    //* ---------------------------------------  ORGANISMOS DE CONTROL ---------------------------------------*/

    ControlOrganism afip = new ControlOrganism("CNRT");
    afip.addProviderEntity(trenesArgentinos);
    afip.setUser(userCNRT);


    ControlOrganism cnrt = new ControlOrganism("AFIP");
    cnrt.addProviderEntity(bancoProvincia);


    //* -------------- ROLES TEMPORALES A SERVICIO --------------------*/
    /*
    TemporaryRoleToService temporaryRole01 = new TemporaryRoleToServiceBuilder().
        withTemporaryRole(TemporaryRole.OBSERVER).
        withService(serviceBathroom).
        build();

    TemporaryRoleToService temporaryRole02 = new TemporaryRoleToServiceBuilder().
        withTemporaryRole(TemporaryRole.AFFECTED).
        withService(serviceMechanicalStair).
        build();

    TemporaryRoleToService temporaryRole03 = new TemporaryRoleToServiceBuilder().
        withTemporaryRole(TemporaryRole.AFFECTED).
        withService(serviceStair).
        build();

    TemporaryRoleToService temporaryRole04 = new TemporaryRoleToServiceBuilder().
        withTemporaryRole(TemporaryRole.AFFECTED).
        withService(serviceElevator).
        build();

    TemporaryRoleToService temporaryRole05 = new TemporaryRoleToServiceBuilder().
        withTemporaryRole(TemporaryRole.AFFECTED).
        withService(serviceRamp).
        build();

    TemporaryRoleToService temporaryRole06 = new TemporaryRoleToServiceBuilder().
        withTemporaryRole(TemporaryRole.AFFECTED).
        withService(serviceParking).
        build();
*/
    //* -------------- Personas --------------------*/
    Person personNico = new PersonBuilder().
        withName("Nicolas").
        withSurname("Galfione").
        withEmail("nicogalfione@gmail.com").
        withPhoneNumber("114324658").
        withProvince(province01).
        withDepartment(department01).
        withLocality(locality01).
        withMunicipality(municipality01).
        withUser(userNico).
        build();

    Person personBorda = new PersonBuilder().
        withName("Juan Ignacio").
        withSurname("Borda").
        withEmail("jborda@frba.utn.edu.ar").
        withPhoneNumber("1137593846").
        withProvince(province02).
        withDepartment(department02).
        withLocality(locality02).
        withMunicipality(municipality02).
        withUser(userBorda).
        build();

    Person personSanti = new PersonBuilder().
        withName("Santi").
        withSurname("Arrascaeta").
        withEmail("santiago7601a@gmail.com").
        withPhoneNumber("1134826296").
        withProvince(province03).
        withDepartment(department03).
        withLocality(locality03).
        withMunicipality(municipality03).
        withUser(userSanti).
        build();

    Person personVilla = new PersonBuilder().
        withName("Nacho").
        withSurname("Villarruel").
        withEmail("nacho.villarruel@gmail.com").
        withPhoneNumber("1134826296").
        withProvince(province03).
        withDepartment(department03).
        withLocality(locality03).
        withMunicipality(municipality03).
        withUser(userVilla).
        build();

    //* ---------------------------------------  MIEMBROS COMUNIDAD ---------------------------------------*/

    //?----------------- COMUNIDAD 1 - Pregnants

    CommunityMember memberBordaADMINPregnants = new CommunityMemberBuilder().
        withPerson(personBorda).
        withInterestingServices(serviceBathroom, serviceElevator).
        withInterestingEntities(trenSarmiento).
        withRolInCommunity(RolInCommunity.ADMIN).
        build();

    CommunityMember memberSantiPregnants = new CommunityMemberBuilder().
        withPerson(personSanti).
        withInterestingServices(serviceBathroom, serviceElevator).
        withInterestingEntities(trenSarmiento).
        withRolInCommunity(RolInCommunity.NORMAL).
        build();

    //?----------------- COMUNIDAD 2 - Viajeros subte
    CommunityMember memberNicoAdminViajerosSubte = new CommunityMemberBuilder().
        withPerson(personNico).
        withInterestingServices(serviceBathroom, serviceElevator).
        withInterestingEntities(trenSarmiento).
        withMomentNotificate(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).
        withRolInCommunity(RolInCommunity.ADMIN).
        build();

    ///?----------------- COMUNIDAD 3

    CommunityMember memberSantiElevatorAdmin = new CommunityMemberBuilder().
        withPerson(personSanti).
        withInterestingServices(serviceBathroom, serviceElevator).
        withInterestingEntities(trenSarmiento).
        withRolInCommunity(RolInCommunity.ADMIN).
        build();


    CommunityMember memberVillaNormalElevator = new CommunityMemberBuilder().
        withPerson(personVilla).
        withInterestingServices(serviceBathroom, serviceElevator).
        withInterestingEntities(trenSarmiento).
        withRolInCommunity(RolInCommunity.NORMAL).
        withMomentNotificate(15, 16, 17).
        build();

    //*-------------------------------------- INCIDENTE --------------------------------------//

    //?----------------- COMUNIDAD 1 - Pregnants

    Incident incident01 = new IncidentBuilder().
        withOpeningDescription("Se rompio el baño").
        withCreator(memberBordaADMINPregnants).
        withOpeningDate(LocalDateTime.now().plusDays(4)).
        withAssociatedProvisionOfService(bañosDeGallardo).
        build();

    Incident incident02 = new IncidentBuilder().
        withOpeningDescription("Se descompuso el elevador").
        withCreator(memberBordaADMINPregnants).
        withOpeningDate(LocalDateTime.now().plusMonths(2).plusMinutes(7)).
        withAssociatedProvisionOfService(ascensorDeSucursalAlmagro).
        build();


    //?----------------- COMUNIDAD 2 - Viajeros subte

    Incident incident04 = new IncidentBuilder().
        withOpeningDescription("Se rompio el baño").
        withCreator(memberNicoAdminViajerosSubte).
        withOpeningDate(LocalDateTime.now().plusWeeks(1)).
        withAssociatedProvisionOfService(bañosDeSucursalAlmagro).
        build();

    Incident incident05 = new IncidentBuilder().
        withOpeningDescription("Se rompio el baño").
        withCreator(memberNicoAdminViajerosSubte).
        withOpeningDate(LocalDateTime.now().plusWeeks(1)).
        withAssociatedProvisionOfService(bañosDeSucursalBoedo).
        build();

    Incident incident06 = new IncidentBuilder().
        withOpeningDescription("Se rompio el baño").
        withCreator(memberNicoAdminViajerosSubte).
        withOpeningDate(LocalDateTime.now().plusWeeks(1)).
        withAssociatedProvisionOfService(bañosDeSucursalPalermo).
        build();

    Incident incident07 = new IncidentBuilder().
        withOpeningDescription("Se rompio la escalera Mecanica").
        withCreator(memberNicoAdminViajerosSubte).
        withOpeningDate(LocalDateTime.now().plusWeeks(1)).
        withAssociatedProvisionOfService(escalerasMecanicasDeSucursalAlmagro).
        build();


    //* --------------------------------------- COMUNIDAD ---------------------------------------*/

    Community communityPregnants = new CommunityBuilder().
        withName("Embarazadas").
        withDescription("Comunidad de embarazadas").
        withMembers(memberSantiPregnants, memberBordaADMINPregnants).
        withInteretingServices(serviceMechanicalStair, serviceStair).
        withInteretingEstablishment(bcoFrancesAlmagro, bcoFrancesBoedo, bcoFrancesPalermo).
        build();

    communityPregnants.addOpenIncidents(incident01, incident02);

    Community communitySubwayTravelers = new CommunityBuilder().
        withName("Viajeros de subte").
        withDescription("Comunidad de viajeros de subte B").
        withMembers(memberNicoAdminViajerosSubte).
        withInteretingServices(serviceElevator, serviceStair, serviceBathroom).
        withInteretingEstablishment(GallardoB, MedranoB, CarlosGardelB).
        build();

    communitySubwayTravelers.addOpenIncidents(incident04, incident05, incident06, incident07);

    //? comunidad elevadores arranca sin incidentes!! (no esta mal que no tenga incidentes)
    Community communityElevator = new CommunityBuilder().
        withName("Ascensores").
        withDescription("Comunidad de ascensores").
        withMembers(memberSantiElevatorAdmin, memberVillaNormalElevator).
        withInteretingServices(serviceElevator).
        withInteretingEstablishment(bcoFrancesAlmagro, bcoFrancesBoedo, bcoFrancesPalermo, cotoAlmagro, cotoBoedo, cotoCaballito, GallardoB, MedranoB, CarlosGardelB).
        build();


    //*-------------------------------------- NOTIFICATION MESSAGE --------------------------------------//

    NotificationMessage notificationMessage01 = new NotificationMessageBuilder().
        withTitle("notificacion1").
        withBody("cuerponNotificacion2").
        withIncident(incident01).
        build();

    NotificationMessage notificationMessage02 = new NotificationMessageBuilder().
        withTitle("notificacion2").
        withBody("cuerpoNotificacion2").
        withIncident(incident02).
        build();


    //* --------------------------------------- API GEOREF ---------------------------------------*/

    /*
    Importer importer = new Importer();
    List<Department> departaments = new ArrayList<Department>();
    List<Locality> localities = new ArrayList<Locality>();
    List<Municipality> municipities = new ArrayList<Municipality>();
    List<Province> provinces = new ArrayList<Province>();

    importer.departamentsImporter().forEach(departamento -> {
      departaments.add(new Department(departamento.getId(), departamento.getNombre(),
          new Coordinate(departamento.getCentroide().getLat(), departamento.getCentroide().getLon())));
    });
    importer.locationsImporter().forEach(localidad -> {
      localities.add(new Locality(localidad.getId(), localidad.getNombre(),
          new Coordinate(localidad.getCentroide().getLat(), localidad.getCentroide().getLon())));
    });
    importer.municipalitysImporter().forEach(municipio -> {
      municipities.add(new Municipality(municipio.getId(), municipio.getNombre(),
          new Coordinate(municipio.getCentroide().getLat(), municipio.getCentroide().getLon())));
    });
    importer.provinciesImporter().forEach(provincia -> {
      provinces.add(new Province(provincia.getId(), provincia.getNombre(),
          new Coordinate(provincia.getCentroide().getLat(), provincia.getCentroide().getLon())));
    });
    */


    //* ------------------------------------  VALIDADOR CONTRASEÑA ------------------------------------*/


    // * ------------------------------ necesitan agregar configuracion aca por orden de instanciado  ------------------------------ */


    //* ---------------------------------------  PERSISTENCIA  ---------------------------------------*/


    withTransaction(() -> {
      ServiceRepository.getInstance().persist(serviceBathroom);
      ServiceRepository.getInstance().persist(serviceStair);
      ServiceRepository.getInstance().persist(serviceMechanicalStair);
      ServiceRepository.getInstance().persist(serviceRamp);
      ServiceRepository.getInstance().persist(serviceElevator);
      ServiceRepository.getInstance().persist(serviceParking);

      // ENTITIES
      EntityRepository.getInstance().persist(trenRoca);
      EntityRepository.getInstance().persist(trenSarmiento);
      EntityRepository.getInstance().persist(bancoFrances);
      EntityRepository.getInstance().persist(coto);


      // ESTABLISHMENTS
      EstablishmentRepository.getInstance().persist(GallardoB);
      EstablishmentRepository.getInstance().persist(MedranoB);
      EstablishmentRepository.getInstance().persist(CarlosGardelB);
      EstablishmentRepository.getInstance().persist(bcoFrancesPalermo);
      EstablishmentRepository.getInstance().persist(bcoFrancesBoedo);
      EstablishmentRepository.getInstance().persist(bcoFrancesAlmagro);
      EstablishmentRepository.getInstance().persist(cotoCaballito);
      EstablishmentRepository.getInstance().persist(cotoAlmagro);
      EstablishmentRepository.getInstance().persist(cotoBoedo);

      //? USER - COMMUNITY - MEMBER
      UserRepository.getInstance().persist(userNico);
      UserRepository.getInstance().persist(userBorda);
      UserRepository.getInstance().persist(userSanti);
      UserRepository.getInstance().persist(userVilla);

      UserRepository.getInstance().persist(userCNRT);
      UserRepository.getInstance().persist(userTrenesArgentinos);

      //? PERSON


      PersonRepository.getInstance().persist(personNico);
      PersonRepository.getInstance().persist(personBorda);
      PersonRepository.getInstance().persist(personSanti);
      PersonRepository.getInstance().persist(personVilla);

      //? TEMPORARY ROLE

    /*
    em.persist(temporaryRole01);
    em.persist(temporaryRole02);
    em.persist(temporaryRole03);
    em.persist(temporaryRole04);
    em.persist(temporaryRole05);
    em.persist(temporaryRole06);
    */

      //?----------------- COMUNIDAD 1 - Pregnants
      CommunityMemberRepository.getInstance().persist(memberBordaADMINPregnants);
      CommunityMemberRepository.getInstance().persist(memberSantiPregnants);

      //?----------------- COMUNIDAD 2 - Viajeros subte


      CommunityMemberRepository.getInstance().persist(memberNicoAdminViajerosSubte);
      ///?----------------- COMUNIDAD 3 - Elevadores
      CommunityMemberRepository.getInstance().persist(memberSantiElevatorAdmin);
      CommunityMemberRepository.getInstance().persist(memberVillaNormalElevator);


      // PROVISION OF SERVICE
      ProvisionOfServiceRepository.getInstance().persist(bañosDeGallardo);
      ProvisionOfServiceRepository.getInstance().persist(bañosDeMedrano);
      ProvisionOfServiceRepository.getInstance().persist(escalerasDeCarlosGardel);
      ProvisionOfServiceRepository.getInstance().persist(estacionamientoCotoCaballito);
      ProvisionOfServiceRepository.getInstance().persist(estacionamientoCotoBoedo);
      ProvisionOfServiceRepository.getInstance().persist(estacionamientoCotoAlmagro);
      ProvisionOfServiceRepository.getInstance().persist(bañosDeSucursalAlmagro);
      ProvisionOfServiceRepository.getInstance().persist(bañosDeSucursalBoedo);
      ProvisionOfServiceRepository.getInstance().persist(bañosDeSucursalPalermo);
      ProvisionOfServiceRepository.getInstance().persist(escalerasMecanicasDeSucursalAlmagro);
      ProvisionOfServiceRepository.getInstance().persist(ascensorDeSucursalAlmagro);
      ProvisionOfServiceRepository.getInstance().persist(ascensorDeSucursalBoedo);
      ProvisionOfServiceRepository.getInstance().persist(rampaDeSucursalBoedo);
      ProvisionOfServiceRepository.getInstance().persist(rampaDeSucursalPalermo);
      ProvisionOfServiceRepository.getInstance().persist(rampaDeCotoPalermo);
      ProvisionOfServiceRepository.getInstance().persist(rampaDeCotoBoedo);

      // INCIDENT
      IncidentRepository.getInstance().persist(incident01);
      IncidentRepository.getInstance().persist(incident02);
      IncidentRepository.getInstance().persist(incident04);
      IncidentRepository.getInstance().persist(incident05);
      IncidentRepository.getInstance().persist(incident06);
      IncidentRepository.getInstance().persist(incident07);


      // COMMUNITY
      CommunityRepository.getInstance().persist(communityPregnants);
      CommunityRepository.getInstance().persist(communityElevator);
      CommunityRepository.getInstance().persist(communitySubwayTravelers);


      // NOTIFICATION MESSAGE
      NotificationMessageRepository.getInstance().persist(notificationMessage01);
      NotificationMessageRepository.getInstance().persist(notificationMessage02);

      // API GEOREF

      /*
      departaments.forEach(department -> DepartmentRepository.getInstance().persist(department));
      localities.forEach(department -> DepartmentRepository.getInstance().persist(department));
      municipities.forEach(department -> DepartmentRepository.getInstance().persist(department));
      provinces.forEach(department -> DepartmentRepository.getInstance().persist(department));
      */

      //SERVICIOS
      ServiceRepository.getInstance().persist(serviceBathroom);
      ServiceRepository.getInstance().persist(serviceStair);
      ServiceRepository.getInstance().persist(serviceMechanicalStair);
      ServiceRepository.getInstance().persist(serviceRamp);
      ServiceRepository.getInstance().persist(serviceElevator);

      ProviderEntityRepository.getInstance().persist(bancoProvincia);
      ProviderEntityRepository.getInstance().persist(trenesArgentinos);
      ProviderEntityRepository.getInstance().persist(cotoSupermercado);

      ControlOrganismRepository.getInstance().persist(afip);
      ControlOrganismRepository.getInstance().persist(cnrt);
    });
  }

}