package ar.edu.utn.frba.dds.builders;


import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.community_member.RolInCommunity;
import ar.edu.utn.frba.dds.models.community_member.temporary_role.TemporaryRoleToService;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.services.Service;

public class CommunityMemberBuilder {

  private CommunityMember communityMember = new CommunityMember();

  public CommunityMemberBuilder() {

  }

  //todo testear si funciona con 1 y mas de 1 servicio por parametro
  public CommunityMemberBuilder withInterestingServices(Service... services) {
    this.communityMember.addInterestingService(services);
    return this;
  }

  //todo testear si funciona con 1 y mas de 1 servicio por parametro
  public CommunityMemberBuilder withInterestingEntities(Entity... entities) {
    this.communityMember.addInterestingEntity(entities);
    return this;
  }


  public CommunityMemberBuilder withTemporaryRoleToService(TemporaryRoleToService... temporaryRoleToService) {
    this.communityMember.addTemporaryRoletoService(temporaryRoleToService);
    return this;
  }



  public CommunityMemberBuilder withPerson(Person person) {
    this.communityMember.setPerson(person);
    return this;
  }

  public CommunityMemberBuilder withMomentNotificate(Integer ... fechaNueva) {
    this.communityMember.addMomentNotificate(fechaNueva);
    return this;
  }

  public CommunityMemberBuilder withRolInCommunity(RolInCommunity rolInCommunity) {
    this.communityMember.setRolInCommunity(rolInCommunity);
    return this;
  }




  //* -------------------------- BUILD -------------------------- *//

  public CommunityMember build() {

    //* aca validaciones si las requerimos ej: nombre no vacio

    return this.communityMember;
  }

}



//* ejemplo de uso. no hace falta que tenga todas las funciones. podemos utilizar cualquiera y siempre hay que terminar con build

/**
* CommunityMember member = new CommunityMemberBuilder()
    .withMeanOfNotification(meanOfNotification)
    .withName(name)
    .withSurname(surname)
    .withEmail(email)
    .withPhoneNumber(phoneNumber)
    .withLocation(location)
    .withInterestingServices(service1, service2)
    .withInterestingEntities(entity1, entity2)
    .withProvince(province)
    .withDepartment(department)
    .withMunicipality(municipality)
    .withLocality(locality)
    .withCoordinate(coordinate)
    .build();
* */

