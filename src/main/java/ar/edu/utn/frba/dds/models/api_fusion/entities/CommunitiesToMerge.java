package ar.edu.utn.frba.dds.models.api_fusion.entities;

import ar.edu.utn.frba.dds.models.community.Community;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommunitiesToMerge {
    CommunityApi community1;
    CommunityApi community2;


    @Override
    public String toString() {
        return "{" +
                "\"community1\":" + community1 +
                ", \"community2\":" + community2 +
                '}';
    }
}
