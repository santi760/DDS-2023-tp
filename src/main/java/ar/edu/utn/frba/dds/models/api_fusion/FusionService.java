package ar.edu.utn.frba.dds.models.api_fusion;

import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunitiesToMerge;
import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunityApi;
import ar.edu.utn.frba.dds.models.api_fusion.entities.MergedCommunity;
import ar.edu.utn.frba.dds.models.api_fusion.entities.Recommendation;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface FusionService {
    @POST("recommendations")
    Call<List<Recommendation>> recommendations(@Body List<CommunityApi> communities);

    @POST("fusion")
    Call<MergedCommunity> communityToMerged(@Body CommunitiesToMerge communities);




}
