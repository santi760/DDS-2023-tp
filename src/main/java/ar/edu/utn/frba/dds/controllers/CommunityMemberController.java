package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community.notification_channel.email.EmailSender;
import ar.edu.utn.frba.dds.models.community.notification_channel.whatsapp.WhatsAppSender;
import ar.edu.utn.frba.dds.models.community.notification_schedule.definded_moments.DefinedMoments;
import ar.edu.utn.frba.dds.models.community.notification_schedule.right_now.RightNow;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.RolInCommunity;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.models.users.RolType;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.entities.CommunityMemberRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.DepartmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.LocalityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.MunicipalityRepository;
import ar.edu.utn.frba.dds.repositories.entities.locations.ProvinceRepository;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.repositories.entities.CommunityRepository;
import ar.edu.utn.frba.dds.repositories.entities.PersonRepository;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import ar.edu.utn.frba.dds.server.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import lombok.Getter;
import java.util.*;


import static ar.edu.utn.frba.dds.models.users.RolType.COMMUNITY_MEMBER;


public class CommunityMemberController extends Controller implements ICrudViewsHandler {




  public CommunityMemberController(CommunityMemberRepository repositorioDeMiembrosDeComunidad) {

  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {
    CommunityMember servicio = (CommunityMember) CommunityMemberRepository.getInstance().read(Long.parseLong(context.pathParam("id")));
    Map<String, Object> model = new HashMap<>();
    model.put("servicio", servicio);
    context.pathParam("id");
  }

  public void showCommunityMember(Context context) {
    User user = super.usuarioLogueado(context);
    Community community = CommunityRepository.getInstance().read(Long.parseLong(context.pathParam("id")));

    super.checkRolTypeForSpecificCommunity(RolInCommunity.ADMIN,community,user);


    Set<CommunityMember> communityMembers = community.getMembers();
    Map<String, Object> model = new HashMap<>();
    Set<String> nombresPersonas = new HashSet<>();
    communityMembers.forEach(member -> {
      nombresPersonas.add(member.getPerson().getName() + " " + member.getPerson().getSurname());
    });


    model.put("communityMembers", nombresPersonas);
    context.render("/admin_community/deleteCommunityMember.hbs", model);
  }

    /*public void deleteCommunityMember(Context context) {
        Map<String, Object> datos = context.bodyAsClass(Map.class);

        String servicio = (String) datos.get("select_servicio");
        Service service = ServiceRepository.getInstance().readByName(servicio);
        Long id = Long.parseLong(context.pathParam("id"));

        Community community = CommunityRepository.getInstance().read(id);

        community.getInteretingSerices().add(service);
        CommunityRepository.getInstance().update(community);
    }*/

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
    //TODO PORQUE ESTA VACIO?????

  }


  public void showSelectUbication(Context context) {
    User user = super.usuarioLogueado(context);
    Set<RolType> roles = new HashSet<>();
    roles.add(COMMUNITY_MEMBER);

    super.checkRolType(user, COMMUNITY_MEMBER);

    //hara falta obtener la persona a la cual esta asociada la ubicacion? o una vez seleccione las
    //ubicaciones que quiere ahi obtengo la pesona para setterla?
    Map<String, Object> model = new HashMap<>();

    List<Department> departamentos = new ArrayList<>();
    List<Locality> localidades = new ArrayList<>();
    List<Municipality> municipalidades = new ArrayList<>();
    List<Province> pronvicias = new ArrayList<>();

    departamentos = DepartmentRepository.getInstance().readAll();
    localidades = LocalityRepository.getInstance().readAll();
    municipalidades = MunicipalityRepository.getInstance().readAll();
    pronvicias = ProvinceRepository.getInstance().readAll();
    Person persona = PersonRepository.getInstance().readByUserId(user.getId());
    if(persona.getDepartment() != null){
      model.put("departamentoEstablecido", persona.getDepartment().getName() );
    }else{
      model.put("departamentoEstablecido", "Aun no has establecido Departamento" );
    }

    if(persona.getLocality() != null){
      model.put("localidadEstablecido", persona.getLocality().getName() );
    }else{
      model.put("localidadEstablecido", "Aun no has establecido Localidad" );
    }

    if(persona.getProvince() != null){
      model.put("provinciaEstablecido", persona.getProvince().getName() );
    }else{
      model.put("provinciaEstablecido", "Aun no has establecido Provincia" );
    }

    if(persona.getMunicipality() != null){
      model.put("municipalidadEstablecido", persona.getMunicipality().getName() );
    }else{
      model.put("municipalidadEstablecido", "Aun no has establecido Municipalidad" );
    }

    model.put("departamentos", departamentos);
    model.put("localidades", localidades);
    model.put("municipalidades", municipalidades);
    model.put("pronvicias", pronvicias);


    context.render("public/configuracion_ubicacion.hbs", model);
  }


  public void configHorarios(Context context) {
    User user = super.usuarioLogueado(context);

    super.checkRolType(user, COMMUNITY_MEMBER);
    Map<String, Object> model = new HashMap<>();

    Person persona = PersonRepository.getInstance().readByUserId(user.getId());

    List<Community> comunidades = CommunityRepository.getInstance().readAllCommunityOfPerson(persona.getId());

    model.put("comunidadesPersona", comunidades);

    context.render("public/config_notificaciones.hbs", model);
  }

  public void guardarConfigHorarios(Context context) {

    User user = super.usuarioLogueado(context);

    super.checkRolType(user, COMMUNITY_MEMBER);

    Map<String, Object> model = new HashMap<>();
    Map<String, Object> datos = context.bodyAsClass(Map.class);

    List<String> horariosObtenidos = (List<String>) datos.get("horarios");

    List<Integer> horarios = new ArrayList<>();
    horariosObtenidos.forEach(h -> horarios.add(Integer.parseInt(h)));

    String comunidad = (String) datos.get("comunidad");

    Community comunidade = CommunityRepository.getInstance().readByName(comunidad);

    Person persona = PersonRepository.getInstance().readByUserId(user.getId());

    CommunityMember member = CommunityMemberRepository.getInstance().readCommunityMemberOfCommunity(comunidade, persona.getId());
    member.setMomentsNotificate(horarios);
    CommunityMemberRepository.getInstance().update(member);

    context.render("public/confirmacion_de_accion.hbs");
  }
  
  public void changeUbication(Context context){
    User user = super.usuarioLogueado(context);


    super.checkRolType(user, COMMUNITY_MEMBER);



    Map<String, Object> datos = context.bodyAsClass(Map.class);
  
    String provincia = (String) datos.get("provincia");
    String municipio = (String) datos.get("municipio");
    String departamento = (String) datos.get("departamento");
    String localidad = (String) datos.get("localidad");
  
    Province provinciaSettear = ProvinceRepository.getInstance().readByName(provincia);
    Municipality municipioSettear = MunicipalityRepository.getInstance().readByName(municipio);
    Department departamentoSettear = DepartmentRepository.getInstance().readByName(departamento);
    Locality localidadSettear = LocalityRepository.getInstance().readByName(localidad);

    User usuarioLoged = UserRepository.getInstance().read(user.getId());
    Person persona = PersonRepository.getInstance().readByUserId(usuarioLoged.getId());

    persona.setLocality(localidadSettear);
    persona.setDepartment(departamentoSettear);
    persona.setProvince(provinciaSettear);
    persona.setMunicipality(municipioSettear);

    PersonRepository.getInstance().update(persona);

    context.render("public/confirmacion_de_accion.hbs");
  }

  public void configPerfil(Context context){
    User user = super.usuarioLogueado(context);


    super.checkRolType(user, COMMUNITY_MEMBER);


    Map<String, Object> model = new HashMap<>();

    Person persona = PersonRepository.getInstance().readByUserId(user.getId());

    model.put("nombre", persona.getName());
    model.put("apellido", persona.getSurname());
    model.put("email", persona.getEmail());
    model.put("telefono", persona.getPhoneNumber());

    if(user.getNotificationChannel().getClass().getName().equals(WhatsAppSender.class.getName())){
      model.put("medio", "WhatsApp");
    }else {
      model.put("medio", "Email");
    }

    if(user.getNotificationSchedule().getClass().getName().equals(RightNow.class.getName())){
      model.put("momento", "En el momento");
    }else {
      model.put("momento", "Segun horarios");
    }

    context.render("public/config_perfil.hbs", model);
  }

  public void guardarConfigPerfil(Context context){
    User user = super.usuarioLogueado(context);


    super.checkRolType(user, COMMUNITY_MEMBER);



    Map<String, Object> datos = context.bodyAsClass(Map.class);


    String nombre = (String) datos.get("nombre");
    String apellido = (String) datos.get("apellido");
    String email = (String) datos.get("email");
    String telefono = (String) datos.get("telefono");
    String comunicacion = (String) datos.get("comunicacion");
    String notificacion = (String) datos.get("notificacion");


    Person persona = PersonRepository.getInstance().readByUserId(user.getId());

    if (!nombre.equals("")){
      persona.setName(nombre);
    }
    if (!apellido.equals("")){
      persona.setSurname(apellido);
    }
    if (!email.equals("")){
      persona.setEmail(email);
    }
    if (!telefono.equals("")){
      persona.setPhoneNumber(telefono);
    }

    if (!comunicacion.equals("")){
      if(comunicacion.equals("WhatsApp")){
        user.setNotificationChannel(new WhatsAppSender());
      }else{
        user.setNotificationChannel(new EmailSender());
      }
    }

    if (!notificacion.equals("")){
      if (notificacion.equals("En el momento")){
        user.setNotificationSchedule(new RightNow());
      }else{
        user.setNotificationSchedule(new DefinedMoments());
      }
    }

    UserRepository.getInstance().update(user);
    PersonRepository.getInstance().update(persona);

    context.render("public/confirmacion_de_accion.hbs");
  }

  public void confirmConfig(Context context){
    Map<String, Object> model = new HashMap<>();
    context.render("public/confirmacion_de_accion.hbs",model);
  }

  public void configHorariosVer(Context context){
    User user = super.usuarioLogueado(context);

    super.checkRolType(user, COMMUNITY_MEMBER);



    Map<String, Object> model = new HashMap<>();
    @Getter
    class ComunidadConHorario{
      String name;
      List<Integer> horarios;
      public ComunidadConHorario(String name,List<Integer> horarios ){
        this.name = name;
        this.horarios = horarios;
      }
    }

    Person persona = PersonRepository.getInstance().readByUserId(user.getId());
    List<Community> comunidades = CommunityRepository.getInstance().readAll();

    List<ComunidadConHorario> comunidadConHorarios = new ArrayList<>();

      for(Community community: comunidades) {
        CommunityMember cm = CommunityMemberRepository.getInstance().readCommunityMemberOfCommunity(community, persona.getId());
        if(cm != null) {
          ComunidadConHorario comunidadConHorari = new ComunidadConHorario(community.getName(), cm.getMomentsNotificate());
          comunidadConHorarios.add(comunidadConHorari);
        }
      }

    model.put("comunidades", comunidadConHorarios);

    context.render("public/config_notificaciones_ver.hbs", model);
  }
}
