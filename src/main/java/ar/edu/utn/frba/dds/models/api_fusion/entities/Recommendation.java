package ar.edu.utn.frba.dds.models.api_fusion.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {
    CommunityApi communityApi1;
    CommunityApi communityApi2;

    @Override
    public String toString() {
        return "{" +
                "\"communityApi1\":\"" + communityApi1 + '\"' +
                ", \"communityApi2\":" + communityApi2 +
                '}';
    }
}
