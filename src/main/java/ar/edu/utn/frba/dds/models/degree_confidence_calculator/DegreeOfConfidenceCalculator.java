package ar.edu.utn.frba.dds.models.degree_confidence_calculator;

import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunityApi;
import ar.edu.utn.frba.dds.models.degree_confidence_calculator.entities.CommunityToCalculate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DegreeOfConfidenceCalculator {

        @POST("calculate")
        Call<CommunityApi> communityEvaluated(@Body CommunityToCalculate communityToEvaluate);

    }

