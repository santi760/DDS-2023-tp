package ar.edu.utn.frba.dds.models.api_fusion.entities;

import java.util.List;

public class MergedCommunity {
    List<CommunityApi> mergedCommunity;
    CommunityApi community2;
    CommunityApi community3;


    @Override
    public String toString() {
        return "{" +
                "\"mergedCommunity\":" + mergedCommunity +
                '}';
    }
}
