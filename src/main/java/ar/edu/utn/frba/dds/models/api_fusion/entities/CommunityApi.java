package ar.edu.utn.frba.dds.models.api_fusion.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommunityApi {
    private String id;
    private String name;
    private String lastTimeMerged;
    private Double degreeOfConfidence;
    private List<MemberApi> members;
    private List<ServiceApi> interestingServices;
    private List<EstablishmentApi> interestingEstablishments;


    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"lastTimeMerged\":\"" + lastTimeMerged + '\"' +
                ", \"degreeOfConfidence\":" + degreeOfConfidence +
                ", \"members\":" + members +
                ", \"interestingServices\":" + interestingServices +
                ", \"interestingEstablishments\":" + interestingEstablishments +
                '}';
    }

}
