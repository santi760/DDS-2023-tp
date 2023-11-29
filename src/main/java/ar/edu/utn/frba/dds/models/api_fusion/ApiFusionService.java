package ar.edu.utn.frba.dds.models.api_fusion;

import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunitiesToMerge;
import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunityApi;
import ar.edu.utn.frba.dds.models.api_fusion.entities.MergedCommunity;
import ar.edu.utn.frba.dds.models.api_fusion.entities.Recommendation;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.List;

public class ApiFusionService {

    private static ApiFusionService instancia = null;
    private static final String urlAPI = "https://service-01-merge-community-utn-production.up.railway.app/api/";
    private Retrofit retrofit;


    private ApiFusionService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiFusionService getInstance() {
        if (instancia == null) {
            instancia = new ApiFusionService();
        }
        return instancia;
    }

    public MergedCommunity communityMerge(CommunitiesToMerge communities) throws IOException {
        FusionService fusionService = this.retrofit.create(FusionService.class);
        Call<MergedCommunity> request = fusionService.communityToMerged(communities);
        Response<MergedCommunity> response = request.execute();
        return response.body();
    }

    public List<Recommendation> recommendations(List<CommunityApi> communities) throws IOException {
         FusionService apiFusionService = this.retrofit.create(FusionService.class);
         Call<List<Recommendation>> request = apiFusionService.recommendations(communities);
         Response<List<Recommendation>> response = request.execute();
         return response.body();
    }


}
