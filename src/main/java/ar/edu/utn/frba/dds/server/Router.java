package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.*;
import ar.edu.utn.frba.dds.exceptions.NotFoundException;
import ar.edu.utn.frba.dds.exceptions.ServerErrorException;
import ar.edu.utn.frba.dds.exceptions.SessionNotLogInException;
import ar.edu.utn.frba.dds.handlers.AccessDeniedHandler;
import io.javalin.http.HttpResponseException;
import io.javalin.http.NotFoundResponse;

import java.util.Arrays;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {

  private static final List<String> STATIC_RESOURCES = Arrays.asList("/assets/", "/images/", "/styles/");
  private static final List<String> PUBLIC_ENDPOINTS = Arrays.asList("/", "/login", "/sign-in","/prueba");

  public static void init() {

    Server.app().before(ctx -> {
      String path = ctx.path();

      if (isStaticResource(path) || isPublicEndpoint(path) ) {
        return;
      }


      if (ctx.sessionAttribute("user_id") == null && !(ctx.path().equals("/") || ctx.path().equals("/login") || ctx.path().equals("/sign-in") ||  ctx.path().equals("/login-failed"))) {

        throw new SessionNotLogInException();
      }
    });





    Server.app().get("/", ctx -> {

      ctx.result("pagina de bienvenida, deberia tener links para login y explicacion de la app");
      ctx.render("public/home.hbs");
    });

    Server.app().routes(() -> {

      get("/log-out", ((AuthenticateController) FactoryController.controller("Authenticate"))::logOut);

      /*---------------------------AUTHENTIFICACIÓN-------------------------------*/
      get("/login",((AuthenticateController) FactoryController.controller("Authenticate"))::showLogin);  // TODO Nacho agregar /home adelante del /login para que venga por default con javascript
      post("/login",((AuthenticateController) FactoryController.controller("Authenticate"))::session);

      get("/login-failed",((AuthenticateController) FactoryController.controller("Authenticate"))::showError);

      get("sign-in",((AuthenticateController) FactoryController.controller("Authenticate"))::showSignIn);
      post("sign-in",((AuthenticateController) FactoryController.controller("Authenticate"))::create);

      /*---------------------------HOME-------------------------------*/

      //TODO mover dashboard a un controlador especifico y crear uno para cada rol
      get("/dashboard",((PublicController) FactoryController.controller("Home"))::show)  ;  // ACA SE REDIRIGE UNA VEZ LOGUEADO

      /*--------------------------- ESTABLISHMENT -------------------------------*/
      get("establishments", ((EstablishmentController) FactoryController.controller("Establecimiento"))::show);  //MUESTRA TODOS LOS ESTABLECIMIENTOS

      /*--------------------------- ENTITIES -------------------------------*/
      get("entities", ((EntityController) FactoryController.controller("Entidad"))::show);  //MUESTRA TODAS LAS ENTIDADES


      get("entities/upload/entities", ((EntityController) FactoryController.controller("Entidad"))::showToUploadEntities); //MUESTRA PARA SUBIR UN ARCHIVO
      get("entities/upload/organismControl", ((EntityController) FactoryController.controller("Entidad"))::showToUploadOrganismControl); //MUESTRA PARA SUBIR UN ARCHIVO
      post("entities/upload/{tipo}", ((EntityController) FactoryController.controller("Entidad"))::upload);  //SUBE UN ARCHIVO

      /*--------------------------- PROVIDER ENTITIES -------------------------------*/
      get("providerEntity/upload/establishments", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showToUploadEstablishments); //MUESTRA PARA SUBIR UN ARCHIVO
      get("provisionOfServices", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showProvisionOfServices);
      post("/providerEntity/entity/{id}/upload/establishments", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::uploadEstablishments);  //SUBE UN ARCHIVO
      get("providerEntity/upload/entities",((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showToUploadEntities);
      post("providerEntity/upload/entities",((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::uploadEntities);
      get("/providerEntity/add/provisionOfService", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showToAddProviderEntities);
      post("/providerEntity/provisionOfService/add/service/{id}/establishment/{otherId}", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::addProvisionOfService);
      post("deleteEntity/{id}", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::deleteEntity);
      post("delete/establishment/{id}/entity/{otherId}",((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::deleteEstablishment);
      post("deleteProvisionOfService/{id}", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::deleteProvisionOfService );

      post("controlOrganism/{id}/add/providerEntity/{providerEntityId}" , ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::addProviderEntity);
      get("controlOrganism/providerEntities", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::showControlOrganismProviderEntities);
      get("controlOrganism/add/providerEntities", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::updateControlOrganismProviderEntities);
      get("controlOrganism/providerEntity/{id}/entities", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::showEntitiesFromProvider);
      post("deleteProviderEntity/{id}", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::deleteProviderEntity);
      get("report/{id}", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::report);
      get("upload/services", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::showToUploadServices);
      post("upload/services", ((ControlOrganismController) FactoryController.controller("OrganismoControl"))::uploadServices);
      get("controlOrganism/services",((ControlOrganismController) FactoryController.controller("OrganismoControl"))::showServices);

      get("providerEntity/entities", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showEntitiesFromProviderEntity);
      get("report", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::report);
      get("providerEntity/{id}/entities", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::show );
      get("providerEntity/add/entities", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::updateProviderEntityEntities);
      get("entity/{id}/establishments", ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showEstablishmentsFromEntity);
      post("providerEntity/{id}/add/entities/{entityId}" , ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::addEntity);


      get("providerEntity/create" , ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::showCreateProviderEntity);
      post("providerEntity/create" , ((ProviderEntityController) FactoryController.controller("EntidadPrestadora"))::createProviderEntity);



      /*---------------------------INCIDENTES -------------------------------*/

      get("incidents/open", ((IncidentController) FactoryController.controller("Incidente"))::showOpenIncidents); //MUESTRA TODOS LOS INCIDENTES ABIERTOS
      get("incidents/close", ((IncidentController) FactoryController.controller("Incidente"))::showClosedIncidents);  // MUESTRA TODOS LOS INCIDENTES CERRADOS
      get("incidents/close/{id}", ((IncidentController) FactoryController.controller("Incidente"))::showToClose);   // MUESTRA PARA CERRAR UN INCIDENTE
      get("incidents/review/{id}", ((IncidentController) FactoryController.controller("Incidente"))::showToReview);   // MUESTRA PARA REVISAR UN INCIDENTE

      get("communities/{idCommunity}/incidents/{state}", ((IncidentController) FactoryController.controller("Incidente"))::showIncidentsOfCommunity); //MUESTRA TODOS LOS INCIDENTES ABIERTOS DE UNA COMUNIDAD

      get("incidents", ((IncidentController) FactoryController.controller("Incidente"))::reportIncidente); // te muestra las prestaciones de servicios donde podes reportar un incidente;
      get("incidents/{open}/community/{idCommunity}", ((IncidentController) FactoryController.controller("Incidente"))::create); //duplicado

      get("incident/community/{idCommunity}/service/{idService}", ((IncidentController) FactoryController.controller("Incidente"))::showProvisionOfServiceByService);
      get("incident/community/{idCommunity}/establishment/{idEstablishment}", ((IncidentController) FactoryController.controller("Incidente"))::showProvisionOfServiceByEstablishment);

      post("incidents/close/{id}", ((IncidentController) FactoryController.controller("Incidente"))::close);   // CIERRA UN INCIDENTE
      post("incidents/review/{id}/community/{idCommunity}", ((IncidentController) FactoryController.controller("Incidente"))::review);   // REVISAR UN INCIDENTE
      post("/incidents/create", ((IncidentController) FactoryController.controller("Incidente"))::createIncident); // CREA UN INCIDENTE EN UNA PROVISION DE SERVICIO

      /*---------------------------COMMUNITY -------------------------------*/

      post("/communities/create", ((CommunityController) FactoryController.controller("Comunidad"))::createCommunity);  //CREA UNA COMUNIDAD

      get("communities", ((CommunityController) FactoryController.controller("Comunidad"))::show);  //MUESTRA TODAS LAS COMUNIDADES del miembro
      get("/communities/join", ((CommunityController) FactoryController.controller("Comunidad"))::index);  //MUESTRA TODAS LAS COMUNIDADES disponibles
      get("communities/create" , ((CommunityController) FactoryController.controller("Comunidad"))::showCreateCommunity);  //MUESTRA UNA COMUNIDAD
      get("communities/{id}", ((CommunityController) FactoryController.controller("Comunidad"))::showSpecificCommunity);  //MUESTRA UNA COMUNIDAD

      get("communities/{id}/members", ((CommunityController) FactoryController.controller("Comunidad"))::showCommunityMembers);  //MUESTRA TODOS LOS MIEMBROS DE UNA COMUNIDAD

      //get("communities/{id}/deleteCommunityMember", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::showCommunityMember); //listo
      post("communities/{id}/deleteCommunityMember/{idMember}", ((CommunityController) FactoryController.controller("Comunidad"))::deleteCommunityMember);

      get("communities/{id}/changeCommunityName", ((CommunityController) FactoryController.controller("Comunidad"))::showCommunityName); //listo
      post("communities/{id}/changeCommunityName", ((CommunityController) FactoryController.controller("Comunidad"))::changeCommunityName); //listo

      get("communities/{id}/changeCommunityDescription", ((CommunityController) FactoryController.controller("Comunidad"))::showCommunityDescription); //listo
      post("communities/{id}/changeCommunityDescription", ((CommunityController) FactoryController.controller("Comunidad"))::changeCommunityDescription); //listo


      //post("configure/user", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::changeUbication);


      get("communities/{id}/add", ((CommunityController) FactoryController.controller("Comunidad"))::showAddInterest);  //PANTALLA PARA AGREGAR SERVICIO O ESTABLECIMIENTO DE INTERES
      get("communities/{id}/remove", ((CommunityController) FactoryController.controller("Comunidad"))::showRemoveInterest);  //PANTALLA PARA AGREGAR SERVICIO O ESTABLECIMIENTO DE INTERES

      post("communities/join/{id}", ((CommunityController) FactoryController.controller("Comunidad"))::joinCommunity);  //MUESTRA TODAS LAS COMUNIDADES disponibles
      post("communities/{idCommunity}/add/{tipo}/{addID}", ((CommunityController) FactoryController.controller("Comunidad"))::addInterest);  //AGREGA SERVICIO O ESTABLECIMIENTO DE INTERES
      post("communities/{idCommunity}/remove/{tipo}/{removeID}", ((CommunityController) FactoryController.controller("Comunidad"))::removeInterest);  //AGREGA SERVICIO O ESTABLECIMIENTO DE INTERES
      post("communities/{id}/exit", ((CommunityController) FactoryController.controller("Comunidad"))::removeMember);   //SE BORRA AL MIEMBRO DE LA COMUNIDAD
      /*---------------------------COMMUNITY MEMBER-------------------------------*/

      get("services", ((ServiceController) FactoryController.controller("Servicio"))::showAll); //TODO NO VA

      delete("services/{id}", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::delete); //TODO NO VA

      /*--------------------------- CONFIG -------------------------------*/
      //TODO hay que cambiar a person controller
      get("configure/user", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::showSelectUbication);
      get("configure/schedules", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::configHorarios);
      get("configure/profile", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::configPerfil);
      get("confirmation", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::confirmConfig);
      get("view/schedules", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::configHorariosVer);

      post("configure/profile", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::guardarConfigPerfil);
      post("configure/user", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::changeUbication);

      post("configure/schedules", ((CommunityMemberController) FactoryController.controller("CommunityMember"))::guardarConfigHorarios);

    });
  }

  // Helper methods
  private static boolean isStaticResource(String path) {
    return STATIC_RESOURCES.stream().anyMatch(path::startsWith);
  }

  private static boolean isPublicEndpoint(String path) {
    return PUBLIC_ENDPOINTS.contains(path);
  }

}