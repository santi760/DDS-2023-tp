package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.repositories.entities.CommunityMemberRepository;
import ar.edu.utn.frba.dds.repositories.entities.EntityRepository;
import ar.edu.utn.frba.dds.repositories.entities.EstablishmentRepository;
import ar.edu.utn.frba.dds.repositories.entities.IncidentRepository;
import ar.edu.utn.frba.dds.repositories.users.ControlOrganismRepository;
import ar.edu.utn.frba.dds.repositories.entities.*;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;

public class FactoryController {
  public static Object controller(String nombre) {
    Object controller = null;
    switch (nombre) {
      case "CommunityMember": controller = new CommunityMemberController(CommunityMemberRepository.getInstance()); break;
      case "Incidente": controller = new IncidentController(IncidentRepository.getInstance()); break;
      case "Authenticate": controller = new AuthenticateController(UserRepository.getInstance()); break;
      case "Home": controller = new PublicController(); break;    //Que repositorios deberia usar?
      case "Entidad": controller = new EntityController(EntityRepository.getInstance()); break;
      case "Establecimiento": controller = new EstablishmentController(EstablishmentRepository.getInstance()); break;
      case "Comunidad": controller = new CommunityController(CommunityRepository.getInstance()); break;
      case "Servicio" : controller = new ServiceController(ServiceRepository.getInstance()); break;
      case "EntidadPrestadora" : controller = new ProviderEntityController(ProviderEntityRepository.getInstance(), ControlOrganismRepository.getInstance()); break;
      case "OrganismoControl" : controller = new ControlOrganismController(ControlOrganismRepository.getInstance()); break;
      //case "Tareas": controller = new TareasController(new RepositorioDeServicios()); break;
    }
    return controller;
  }

}
