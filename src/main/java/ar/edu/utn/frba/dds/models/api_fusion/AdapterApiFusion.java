package ar.edu.utn.frba.dds.models.api_fusion;

import ar.edu.utn.frba.dds.models.api_fusion.entities.*;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdapterApiFusion {
    private static AdapterApiFusion instance = null;
    private static ApiFusionService apiFusionService;

    AdapterApiFusion(){
        apiFusionService = ApiFusionService.getInstance();
    }

    public static AdapterApiFusion instancia(){
        if(instance == null){
            instance = new AdapterApiFusion();
        }
        return instance;
    }

    public MergedCommunity communityMerged(Community community1, Community community2) throws IOException {
        CommunitiesToMerge communities = new CommunitiesToMerge();
        CommunityApi communityApi1 = communityToCommunityApi(community1,community1.getMembers(),community1.getInteretingSerices(),community1.getInteretingEstablishment());
        CommunityApi communityApi2 = communityToCommunityApi(community2,community2.getMembers(),community2.getInteretingSerices(),community2.getInteretingEstablishment());
        communities.setCommunity1(communityApi1);
        communities.setCommunity2(communityApi2);

        MergedCommunity communityApi = apiFusionService.communityMerge(communities);

        return communityApi;
    }

    public CommunitiesToMerge communityMerged2(Community community1, Community community2) throws IOException {
        CommunitiesToMerge communities = new CommunitiesToMerge();
        CommunityApi communityApi1 = communityToCommunityApi(community1,community1.getMembers(),community1.getInteretingSerices(),community1.getInteretingEstablishment());
        CommunityApi communityApi2 = communityToCommunityApi(community2,community2.getMembers(),community2.getInteretingSerices(),community2.getInteretingEstablishment());
        communities.setCommunity1(communityApi1);
        communities.setCommunity2(communityApi2);

        return communities;
    } //borrar

    public List<Recommendation> recommendations(List<Community> communitiesToEvaluate) throws IOException {
        List<CommunityApi> recommendations = new ArrayList<>();

        for (Community community : communitiesToEvaluate){
            CommunityApi communityApi = this.communityToCommunityApi(community,community.getMembers(),community.getInteretingSerices(),community.getInteretingEstablishment());
            recommendations.add(communityApi);
        }

        List<Recommendation> recommendationsApi = apiFusionService.recommendations(recommendations);


        return recommendationsApi;
    }




    public CommunityApi communityToCommunityApi(Community community, Set<CommunityMember> members, Set<Service> interestingServices, Set<Establishment> interestingEstablishments){
        CommunityApi communityApi = new CommunityApi();
        // seteo los atributos de communityApi
        communityApi.setId(community.getId().toString());
        communityApi.setName(community.getName());
        communityApi.setLastTimeMerged(community.getLastTimeMerged().toString());
        communityApi.setDegreeOfConfidence(community.getDegreeOfConfidence());

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
