package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.community_member.temporary_role.TemporaryRole;
import ar.edu.utn.frba.dds.models.community_member.temporary_role.TemporaryRoleToService;
import ar.edu.utn.frba.dds.models.services.Service;

public class TemporaryRoleToServiceBuilder {

    private TemporaryRoleToService temporaryRoleToService = new TemporaryRoleToService();


    public TemporaryRoleToServiceBuilder withService(Service service){
        this.temporaryRoleToService.setService(service);
        return this;
    }

    public TemporaryRoleToServiceBuilder withTemporaryRole(TemporaryRole temporaryRole){
        this.temporaryRoleToService.setTemporaryRole(temporaryRole);
        return this;
    }

    public TemporaryRoleToService build(){
        return this.temporaryRoleToService;
    }
}
