package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.proximity_finder.CustomProximityFinder;
import ar.edu.utn.frba.dds.models.services.Service;

public class CommunityBuilder {

    private Community community = new Community();


    public CommunityBuilder withName(String name) {
        this.community.setName(name);
        return this;
    }

    public CommunityBuilder withInteretingServices(Service... services) {
        this.community.addInteretingServices(services);
        return this;
    }

    public CommunityBuilder withMembers(CommunityMember ... members) {
        this.community.addCommunityMember(members);
        return this;
    }

    public CommunityBuilder withCustomProximityFinder(CustomProximityFinder customProximityFinder) {
        this.community.setCustomProximityFinder(customProximityFinder);
        return this;
    }

    public CommunityBuilder withInteretingEstablishment(Establishment... establishments) {
        this.community.addInteretingEstablishments(establishments);
        return this;
    }

    public CommunityBuilder withDescription(String description) {
        this.community.setDescription(description);
        return this;
    }


    public Community build(){
        // validaciones si necesitamos

        return this.community;
    }


}
