package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.builders.IncidentBuilder;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.*;
import ar.edu.utn.frba.dds.repositories.entities.CommunityMemberRepository;
import ar.edu.utn.frba.dds.repositories.entities.IncidentRepository;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.time.LocalDateTime;
import java.util.*;

import static ar.edu.utn.frba.dds.models.users.RolType.*;


public class IncidentController extends Controller implements ICrudViewsHandler {



    public IncidentController(IncidentRepository repositorioDeIncidentes) {

    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    public void showOpenIncidents(Context context) {
        User user = super.usuarioLogueado(context);


        super.checkRolType(user, COMMUNITY_MEMBER);

        PersonRepository.getInstance().readByUserId(user.getId());
        Person person = PersonRepository.getInstance().readByUserId(user.getId());
        List<Community> communities = person.getCommunitiesOfMyMembers(person.getId());
        Map<String, Object> model = new HashMap<>();
        model.put("comunidadesPersona", communities);


        context.render("community_member/incidents/openIncidents.hbs", model);

    }

    public void showClosedIncidents(Context context) {
        User user = super.usuarioLogueado(context);
        super.checkRolType(user, COMMUNITY_MEMBER);

        PersonRepository.getInstance().readByUserId(user.getId());
        Person person = PersonRepository.getInstance().readByUserId(user.getId());
        List<Community> communities = person.getCommunitiesOfMyMembers(person.getId());
        Map<String, Object> model = new HashMap<>();
        model.put("comunidadesPersona", communities);

        context.render("community_member/incidents/ClosedIncidents.hbs", model);
    }

    public void showIncidentsOfCommunity(Context context) {
        User user = super.usuarioLogueado(context);
        super.checkRolType(user, COMMUNITY_MEMBER);

        String stateString = context.pathParam("state");
        String idCommunity = context.pathParam("idCommunity");
        Community community = CommunityRepository.getInstance().read(Long.parseLong(idCommunity));
        Map<String, Object> model = new HashMap<>();
        model.put("community", community);
        if (stateString.equals("1")) {
            List<Incident> incidents = IncidentRepository.getInstance().getOpenIncidentsByCommunityId(Long.parseLong(idCommunity));
            model.put("incidents", incidents);
            model.put("state", true);
            if (incidents.isEmpty()) {
                context.render("community_member/incidents/NoIncidents.hbs", model);
                return;
            }
        } else {
            List<Incident> incidents = IncidentRepository.getInstance().getClosedIncidentsByCommunityId(Long.parseLong(idCommunity));
            model.put("incidents", incidents);
            model.put("state", false);
            if (incidents.isEmpty()) {
                context.render("community_member/incidents/NoIncidents.hbs", model);
                return;
            }
        }
        context.render("community_member/incidents/incidents.hbs", model);
    }


    @Override
    public void create(Context context) {
        User user = super.usuarioLogueado(context);


        super.checkRolType(user, COMMUNITY_MEMBER);
        String idCommunity = context.pathParam("idCommunity");
        String provisionOfServiceId = context.pathParam("open");
        ProvisionOfService provisionOfService = ProvisionOfServiceRepository.getInstance().read(Long.parseLong(provisionOfServiceId));

        Map<String, Object> model = new HashMap<>();
        model.put("provisionOfService", provisionOfService);
        model.put("idCommunity", idCommunity);
        //Incident incident = context.bodyAsClass(Incident.class);
        //this.incidentRepository.create(incident);
        context.render("community_member/incidents/CreateIncident.hbs", model);
    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }


    public void reportIncidente(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        Map<String, Object> model = new HashMap<>();


        List<CommunityMember> myCommunityMembers = CommunityMemberRepository.getInstance().readCommunityMembers(user.getId());

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

        model.put("comunidadesPersona", myCommunities);


        context.render("public/incidentReport.hbs", model);
    }


    public void showToClose(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        String idIncident = context.pathParam("id");
        Incident incident = IncidentRepository.getInstance().read(Long.parseLong(idIncident));
        if (!incident.getOpen()) {                  //VALIDO SI EL INCIENTE YA ESTA CERRADO, PARA QUE NO PUEDAN MODIFICARLO DENUEVO
            context.redirect("/incidents.hbs/open");
        } else {
            Map<String, Object> model = new HashMap<>();
            model.put("incidentToClose", incident);
            context.render("/community_member/incidents/CloseIndividualIncident.hbs", model);
        }
    }

    public void close(Context context) {
        User user = super.usuarioLogueado(context);


        super.checkRolType(user, COMMUNITY_MEMBER);

        Map<String, Object> model = new HashMap<>();


        String idIncident = context.pathParam("id");
        Incident incident = IncidentRepository.getInstance().read(Long.parseLong(idIncident));

        Community community = incident.getCommunity();

        Person person = PersonRepository.getInstance().readByUserId(user.getId());

        CommunityMember member = PersonRepository.getInstance().getCommunityMemberOfPersonByCommunity(person, community);


        String descripcionDeCierre = context.formParam("textTareaCierreIncidente");
        incident.setClosedDescription(descripcionDeCierre);
        incident.setClosingDate(LocalDateTime.now());
        incident.setOpen(false);
        incident.setCloser(member);
        IncidentRepository.getInstance().update(incident);

        model.put("message", "Gracias por cerrar un incidente");
        context.render("public/appreciate.hbs", model);
    }

    public void showToReview(Context context) {
        User userInSession = super.usuarioLogueado(context);
        super.checkRolType(userInSession, COMMUNITY_MEMBER);

        String idIncident = context.pathParam("id");
        Incident incident = IncidentRepository.getInstance().read(Long.parseLong(idIncident));

        Map<String, Object> model = new HashMap<>();
        model.put("incidentToReview", incident);
        context.render("/community_member/incidents/ReviewIndividualIncident.hbs", model);
    }

    public void review(Context context) {
        User userInSession = super.usuarioLogueado(context);
        super.checkRolType(userInSession, COMMUNITY_MEMBER);


        Map<String, Object> model = new HashMap<>();
        model.put("message", "Gracias por revisar un incidente");
        context.render("public/appreciate.hbs", model);


        String idCommunity = context.pathParam("idCommunity");
        Community community = CommunityRepository.getInstance().read(Long.parseLong(idCommunity));
        Person person = PersonRepository.getInstance().readByUserId(userInSession.getId());

        CommunityMember communityMemberInSession = CommunityMemberRepository.getInstance().readCommunityMemberOfCommunity(community, person.getId());

        String idIncident = context.pathParam("id");
        Incident incident = IncidentRepository.getInstance().read(Long.parseLong(idIncident));
        incident.setOpen(false);
        String descripcionDeCierre = context.formParam("textTareaCierreIncidente");
        incident.setClosedDescription(descripcionDeCierre);
        incident.setClosingDate(LocalDateTime.now());
        incident.setCloser(communityMemberInSession);
        IncidentRepository.getInstance().update(incident);
    }


    public void createIncident(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        String provisionOfServiceId = context.formParam("provisionOfServiceId");
        String openingDescription = context.formParam("textTareaCierreIncidente");
        String idCommunity = context.formParam("idCommunity");
        String inAllCommunity = context.formParam("reportarTodasComunidades");
        ProvisionOfService provisionOfService = ProvisionOfServiceRepository.getInstance().read(Long.parseLong(provisionOfServiceId));

        Community communityOfUser = CommunityRepository.getInstance().read(Long.parseLong(idCommunity));

        Person person = PersonRepository.getInstance().readByUserId(user.getId());

        CommunityMember member = PersonRepository.getInstance().getCommunityMemberOfPersonByCommunity(person, communityOfUser);

        Incident incident = new IncidentBuilder().
                withOpeningDescription(openingDescription).
                withAssociatedProvisionOfService(provisionOfService)
                .withCreator(member)
                .build();


        IncidentRepository.getInstance().create(incident);




        //todo aca explota.
        communityOfUser.addIncidentAndNotifyUsers(incident);

        CommunityRepository.getInstance().update(communityOfUser);

        if (inAllCommunity != null){
            List<Community> comunidades = CommunityRepository.getInstance().readAll();
            comunidades.removeIf(community1 -> community1.getId().equals(communityOfUser.getId()));

            comunidades.forEach(community -> {
                if(community.getInteretingSerices().contains(provisionOfService.getService()) ||
                    community.getInteretingEstablishment().contains(provisionOfService.getEstablishment())){
                    community.getMembers().forEach(communityMember -> {
                        if (person.getCommunityMembers().contains(communityMember)){
                            IncidentBuilder incident1 = new IncidentBuilder().
                                    withOpeningDescription(openingDescription).
                                    withAssociatedProvisionOfService(provisionOfService);
                            incident1.withCreator(communityMember);
                            IncidentRepository.getInstance().create(incident1.build());
                            community.addIncidentAndNotifyUsers(incident1.build());
                        }

                    });

                    CommunityRepository.getInstance().update(community);
                }
            });


        }


        Map<String, Object> model = new HashMap<>();
        model.put("message", "Gracias por reportar un incidente.");
        context.render("public/appreciate.hbs", model);
        
    }

    //si a la comunidad le interesa el servicio O el establecimiento, traemos la provision de servicio: byVilla
    public void showProvisionOfServiceByService(Context context) {
        User user = super.usuarioLogueado(context);


        super.checkRolType(user, COMMUNITY_MEMBER);

        String idService = context.pathParam("idService");
        String idCommunity = context.pathParam("idCommunity");

        //todo: no se estaba usando la comunidad y hacia pegada a DB al pedo, la comente por si en algun momento se necesita usar.
        //Community community = CommunityRepository.getInstance().read(Long.parseLong(idCommunity));

        Service service = ServiceRepository.getInstance().read(Long.parseLong(idService));

        List<ProvisionOfService> existingProvisionOfServices = ProvisionOfServiceRepository.getInstance().getProvisionsOfServiceByServiceId(service.getId());


        Map<String, Object> model = new HashMap<>();
        model.put("esServicio", true);
        model.put("provisionOfServices", existingProvisionOfServices);
        model.put("idCommunity", idCommunity);
        model.put("servicio", service);
        context.render("/community_member/incidents/openIncidentService.hbs", model);
    }

    ////si a la comunidad le interesa el servicio O el establecimiento, traemos la provision de servicio: byVilla
    public void showProvisionOfServiceByEstablishment(Context context){
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        String idEstablishment = context.pathParam("idEstablishment");
        String idCommunity = context.pathParam("idCommunity");

        //todo: no se estaba usando la comunidad y hacia pegada a DB al pedo, la comente por si en algun momento se necesita usar.
        //Community community = CommunityRepository.getInstance().read(Long.parseLong(idCommunity));
        Establishment establishment = EstablishmentRepository.getInstance().read(Long.parseLong(idEstablishment));

        List<ProvisionOfService> existingProvisionOfServices = ProvisionOfServiceRepository.getInstance().getProvisionsOfServiceByEstablishmentId(establishment.getId());

        Map<String, Object> model = new HashMap<>();
        model.put("esServicio", false);
        model.put("establecimiento", establishment);
        model.put("provisionOfServices", existingProvisionOfServices);
        model.put("idCommunity", idCommunity);
        context.render("/community_member/incidents/openIncidentService.hbs", model);
    }

}
