package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.builders.CommunityBuilder;
import ar.edu.utn.frba.dds.builders.CommunityMemberBuilder;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.RolInCommunity;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.RolType;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.CommunityMemberRepository;
import ar.edu.utn.frba.dds.repositories.entities.CommunityRepository;
import ar.edu.utn.frba.dds.repositories.entities.ServiceRepository;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.repositories.entities.*;

import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.*;

import static ar.edu.utn.frba.dds.models.users.RolType.COMMUNITY_MEMBER;

public class CommunityController extends Controller implements ICrudViewsHandler {


  public CommunityController(CommunityRepository repository) {

  }

  @Override
  public void index(Context context) {
      User user = super.usuarioLogueado(context);

      super.checkRolType(user, COMMUNITY_MEMBER);

    Map<String, Object> model = new HashMap<>();
    List<Community> community = CommunityRepository.getInstance().readAll();

    List<Community> comunidadesNuevas= new ArrayList<>();
    for (Community c : community) {
      if (!c.getMembers().stream().anyMatch(cm -> cm.getPerson().getUser().getId() == user.getId())) {
        comunidadesNuevas.add(c);
      }
    }

    model.put("state", !comunidadesNuevas.isEmpty());

    model.put("community", comunidadesNuevas);
    context.render("community_member/communities/JoinCommunity.hbs", model);
  }

  @Override
  public void show(Context context) { //MOSTRAR MIS COMUNIDADES
      User user = super.usuarioLogueado(context);

      super.checkRolType(user, COMMUNITY_MEMBER);

      PersonRepository personRepository = PersonRepository.getInstance();

      Person person = personRepository.readByUserId(user.getId());

      List<Community> communities = person.getCommunitiesOfMyMembers(person.getId());

     Map<String, Object> model = new HashMap<>();

     model.put("state", !communities.isEmpty());

     model.put("comunidadesPersona", communities);



      context.render("community_member/communities/Communities.hbs",model);
  }


  @Override
  public void create(Context context) {

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

 public void showSpecificCommunity(Context context){
     User user = super.usuarioLogueado(context);
     Community community = (Community) CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

     super.checkRolType(user, COMMUNITY_MEMBER);



    Person personOfActiveUser = PersonRepository.getInstance().readByUserId(user.getId());

    CommunityMember communityMemberOfView = PersonRepository.getInstance().getCommunityMemberOfPersonByCommunity(personOfActiveUser, community);

    Boolean isAdministrator = communityMemberOfView.getRolInCommunity().equals(RolInCommunity.ADMIN);



    Map<String, Object> model = new HashMap<>();
    model.put("community", community);
    model.put("isAdministrator", isAdministrator);


    context.render("community_member/communities/IndividualCommunity.hbs", model);
  }


    public void showCommunityName(Context context) {
        User user = super.usuarioLogueado(context);

        String id = context.pathParam("id");
        Community community = CommunityRepository.getInstance().read(id);
        super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

        Map<String, Object> model = new HashMap<>();


        context.render("admin_community/changeCommunityName.hbs", model);
    }

    public void changeCommunityName(Context context) {
        User user = super.usuarioLogueado(context);

        Map<String, Object> datos = context.bodyAsClass(Map.class);

        String nuevoNombre = (String) datos.get("nuevoNombre");

        Long id = Long.parseLong(context.pathParam("id"));

        Community community = CommunityRepository.getInstance().read(id);

        super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

        community.setName(nuevoNombre);
        CommunityRepository.getInstance().update(community);

        context.render("admin_community/changeCommunityName.hbs");
    }

    public void showCommunityDescription(Context context) {
        User user = super.usuarioLogueado(context);


        String id = context.pathParam("id");
        Community community = CommunityRepository.getInstance().read(id);
        super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

        Map<String, Object> model = new HashMap<>();


        context.render("admin_community/changeCommunityDescription.hbs", model);
    }

    public void changeCommunityDescription(Context context) {
        User user = super.usuarioLogueado(context);

        Map<String, Object> datos = context.bodyAsClass(Map.class);

        String nuevoNombre = (String) datos.get("nuevaDescripcion");

        Long id = Long.parseLong(context.pathParam("id"));

        Community community = CommunityRepository.getInstance().read(id);

        super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

        community.setName(nuevoNombre);
        CommunityRepository.getInstance().update(community);

        context.render("admin_community/changeCommunityDescription.hbs");
    }

  public void showAddInterest(Context context){
      User user = super.usuarioLogueado(context);


      Community community = (Community) CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

      super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

    List<Service> allServices = ServiceRepository.getInstance().readAll();
    Set<Service> servicesInteresed = community.getInteretingSerices();
    List<Service> serviceNotInterested = new ArrayList<>();

    for (Service service : allServices) {
      if(!servicesInteresed.contains(service)){
        serviceNotInterested.add(service);
      }
    }

    List<Establishment> allEstablishments = EstablishmentRepository.getInstance().readAll();
    Set<Establishment> establishmentsInteresed = community.getInteretingEstablishment();
    List<Establishment> establishmentNotInterested = new ArrayList<>();

    for (Establishment establishment : allEstablishments) {
      if(!establishmentsInteresed.contains(establishment)){
        establishmentNotInterested.add(establishment);
      }
    }

    Map<String, Object> model = new HashMap<>();

    model.put("community", community);
    model.put("services", serviceNotInterested);
    model.put("establishments", establishmentNotInterested);
    context.render("community_member/communities/addCommunityInterest.hbs", model);
  }

  public void addInterest(Context context){
      User user = super.usuarioLogueado(context);

      Community community = CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("idCommunity")));

      super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

    String tipo = context.pathParam("tipo");
    Long idNuevo = Long.parseLong(context.pathParam("addID"));
    if(tipo.equals("service")){
      Service service = ServiceRepository.getInstance().read(idNuevo);
      if(!community.getInteretingSerices().stream().anyMatch(serviceP -> serviceP.getId().equals(service.getId()))){
          community.getInteretingSerices().add(service);
      }

    }else{
        Establishment establishment = EstablishmentRepository.getInstance().read(idNuevo);

        if(!community.getInteretingEstablishment().stream().anyMatch(establishmentP -> establishmentP.getId().equals(establishment.getId()))){
            community.getInteretingEstablishment().add(establishment);
        }

    }
    CommunityRepository.getInstance().update(community);
    context.redirect("/communities/"+community.getId());
  }

  public void showRemoveInterest(Context context){
      User user = super.usuarioLogueado(context);

      Community community = (Community) CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

      super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

    Set<Service> servicesInteresed = community.getInteretingSerices();
    Set<Establishment> establishmentsInteresed = community.getInteretingEstablishment();

    Map<String, Object> model = new HashMap<>();
    model.put("community", community);
    model.put("services", servicesInteresed);
    model.put("establishments", establishmentsInteresed);
    context.render("community_member/communities/removeCommunityInterest.hbs", model);
  }

  public void removeInterest(Context context){
      User user = super.usuarioLogueado(context);
      Community community = CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("idCommunity")));

      super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, user);

    String tipo = context.pathParam("tipo");
    Long idNuevo = Long.parseLong(context.pathParam("removeID"));
    if(tipo.equals("service")){
      Service service = ServiceRepository.getInstance().read(idNuevo);
      community.getInteretingSerices().removeIf(serviceP -> serviceP.getId().equals(service.getId()));
    }else{
      Establishment establishment = EstablishmentRepository.getInstance().read(idNuevo);

      community.getInteretingEstablishment().removeIf(establishmentP -> establishmentP.getId().equals(establishment.getId()));
    }
    CommunityRepository.getInstance().update(community);
    context.redirect("/communities/"+community.getId());
  }


    public void joinCommunity(Context context){
        User user = super.usuarioLogueado(context);
        super.checkRolType(user, COMMUNITY_MEMBER);

        Community community = CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

        Person person = PersonRepository.getInstance().readByUserId(user.getId());

        CommunityMember communityMember = new CommunityMember();
        communityMember.setPerson(person);
        communityMember.setRolInCommunity(RolInCommunity.NORMAL);

        person.add(communityMember);
        PersonRepository.getInstance().update(person);

        community.getMembers().add(communityMember);


        CommunityMemberRepository.getInstance().create(communityMember);
        CommunityRepository.getInstance().update(community);


        context.redirect("/communities");
    }

    public void showCreateCommunity(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        Map<String, Object> model = new HashMap<>();
        model.put("services", ServiceRepository.getInstance().readAll());
        model.put("establishments", EstablishmentRepository.getInstance().readAll());
        context.render("community_member/communities/createCommunity.hbs", model);
    }


    public void createCommunity(Context context) {
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        String name = context.formParam("name");
        String description = context.formParam("description");
        Long serviceId = Long.parseLong(context.formParam("services"));
        Long selectedEstablishmentId = Long.parseLong(context.formParam("establishments"));

        Service selectedService = ServiceRepository.getInstance().read(serviceId);
        Establishment selectedEstablishment = EstablishmentRepository.getInstance().read(selectedEstablishmentId);

        Person person = PersonRepository.getInstance().readByUserId(user.getId());

        // tengo que traer el usuario activo y agregarle un nuevo rol para avisar que es administrador en la comunidad creada.

        CommunityMember newCommunityMember = new CommunityMemberBuilder()
            .withPerson(PersonRepository.getInstance().readByUserId(user.getId()))
                .withRolInCommunity(RolInCommunity.ADMIN)
            .build();

          Community newCommunity = new CommunityBuilder()
              .withName(name)
              .withDescription(description)
              .build();

          newCommunity.getInteretingSerices().add(selectedService);
          newCommunity.getInteretingEstablishment().add(selectedEstablishment);
          newCommunity.getMembers().add(newCommunityMember);

          CommunityMemberRepository.getInstance().create(newCommunityMember);

        CommunityRepository.getInstance().create(newCommunity);
          UserRepository.getInstance().update(user);

          person.add(newCommunityMember);
          PersonRepository.getInstance().update(person);

          context.redirect("/communities");

    }

    public void showCommunityMembers(Context context) {
        User user = super.usuarioLogueado(context);
        Community community = (Community) CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
        Set<CommunityMember> communityMembers = community.getMembers();
        Map<String, Object> model = new HashMap<>();
        model.put("community", community);
        model.put("communityMembers", communityMembers);
        model.put("communityRoles", RolInCommunity.values());
        CommunityMember miembroUsuario = communityMembers.stream().filter(communityMember -> communityMember.getPerson().getUser().getId().equals(user.getId())).findFirst().orElse(null);
        model.put("esAdmin", miembroUsuario.getRolInCommunity().equals(RolInCommunity.ADMIN));
        context.render("/community_member/communities/member-reports.hbs", model);
    }


    //todo: terminar (villa o borda.)
    public void deleteCommunityMember(Context context) {
        User userInSession = super.usuarioLogueado(context);
        Community community = (Community) CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
        super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community, userInSession);

        CommunityMember communityMemberToDelete = CommunityMemberRepository.getInstance().read(Long.parseLong(context.pathParam("idMember")));
        if(communityMemberToDelete.getRolInCommunity().equals(RolInCommunity.NORMAL)){
            community.removeMember(communityMemberToDelete);
            CommunityRepository.getInstance().update(community);
            CommunityMemberRepository.getInstance().delete(communityMemberToDelete);

        }

        context.redirect("/communities/"+ community.getId());
    }

    public void removeMember(Context context){
        User user = super.usuarioLogueado(context);

        super.checkRolType(user, COMMUNITY_MEMBER);

        Community community = (Community) CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
        Person person = PersonRepository.getInstance().readByUserId(user.getId());

        person.getCommunityMembers().forEach(member ->{
           community.getMembers().forEach(memberC ->{
               if (member.getId().equals(memberC.getId())){
                   CommunityMember communityMember = CommunityMemberRepository.getInstance().read(member.getId());

                   if(communityMember.getRolInCommunity().equals(RolInCommunity.NORMAL)){
                       CommunityMemberRepository.getInstance().delete(communityMember);
                   }

               }
           });
        });




        context.redirect("/confirmation");
    }
}

