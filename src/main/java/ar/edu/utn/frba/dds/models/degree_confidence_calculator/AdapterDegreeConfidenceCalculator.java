package ar.edu.utn.frba.dds.models.degree_confidence_calculator;

import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunityApi;
import ar.edu.utn.frba.dds.models.api_fusion.entities.EstablishmentApi;
import ar.edu.utn.frba.dds.models.api_fusion.entities.MemberApi;
import ar.edu.utn.frba.dds.models.api_fusion.entities.ServiceApi;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdapterDegreeConfidenceCalculator {
    private static AdapterDegreeConfidenceCalculator instance = null;
    private static ApiDegreeConfidenceCalculator apiDegreeConfidenceCalculator;

    private AdapterDegreeConfidenceCalculator(){
        apiDegreeConfidenceCalculator = ApiDegreeConfidenceCalculator.getInstancia();
    }

    public static AdapterDegreeConfidenceCalculator instancia(){
        if(instance == null){
            instance = new AdapterDegreeConfidenceCalculator();
        }
        return instance;
    }

    public CommunityApi communityDegreeCalculator(Community communityToEvaluate) throws IOException {
        CommunityApi communityApi1 = communityToCommunityApi(communityToEvaluate,communityToEvaluate.getMembers(),communityToEvaluate.getInteretingSerices(),communityToEvaluate.getInteretingEstablishment());


        //CommunityApi communityApi = apiDegreeConfidenceCalculator.calculateDegreeConfident(communityApi1);
        CommunityApi communityApi = null;
        return communityApi;
    }

    private CommunityApi communityToCommunityApi(Community community, Set<CommunityMember> members, Set<Service> interestingServices, Set<Establishment> interestingEstablishments){
        CommunityApi communityApi = new CommunityApi();
        // seteo los atributos de communityApi
        communityApi.setId(community.getId().toString());
        communityApi.setName(community.getName());
        communityApi.setLastTimeMerged(community.getLastTimeMerged().toString());

        List<MemberApi> membersApi = new ArrayList<>();
        List<ServiceApi> interestingServicesApi = new ArrayList<>();
        List<EstablishmentApi> interestingEstablishmentsApi = new ArrayList<>();

        for (CommunityMember communityMember : members){
            MemberApi memberApi = this.memberToMemberApi(communityMember);
            membersApi.add(memberApi);
        }
        for (Service service : interestingServices){
            ServiceApi serviceApi = this.serviceToServiceApi(service);
            interestingServicesApi.add(serviceApi);
        }
        for (Establishment establishment : interestingEstablishments){
            EstablishmentApi establishmentApi = this.establishmentToEstablishmentApi(establishment);
            interestingEstablishmentsApi.add(establishmentApi);
        }


        communityApi.setMembers(membersApi);
        communityApi.setInterestingServices(interestingServicesApi);
        communityApi.setInterestingEstablishments(interestingEstablishmentsApi);


        return communityApi;
    }


    private ServiceApi serviceToServiceApi(Service service){
        ServiceApi serviceApi = new ServiceApi();
        // seteo los atributos de serviceApi
        serviceApi.setId(service.getId().toString());
        serviceApi.setName(service.getName());
        return serviceApi;
    }

    private MemberApi memberToMemberApi(CommunityMember communityMember){
        MemberApi memberApi = new MemberApi();
        // seteo los atributos de serviceApi
        memberApi.setId(communityMember.getId().toString());
        memberApi.setName(communityMember.getPerson().getName());
        return memberApi;
    }

    private EstablishmentApi establishmentToEstablishmentApi(Establishment establishment){
        EstablishmentApi establishmentApi = new EstablishmentApi();
        // seteo los atributos de serviceApi
        establishmentApi.setId(establishment.getId().toString());
        establishmentApi.setName(establishment.getName());
        return establishmentApi;
    }











}
