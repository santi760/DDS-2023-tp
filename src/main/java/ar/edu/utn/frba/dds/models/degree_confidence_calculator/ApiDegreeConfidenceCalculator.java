package ar.edu.utn.frba.dds.models.degree_confidence_calculator;

import ar.edu.utn.frba.dds.models.api_fusion.entities.CommunityApi;
import ar.edu.utn.frba.dds.models.degree_confidence_calculator.entities.CommunityToCalculate;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ApiDegreeConfidenceCalculator {

        private static ApiDegreeConfidenceCalculator instancia = null;
        private static final String urlAPI = "http://127.0.0.1:8008";
        private Retrofit retrofit;


        private ApiDegreeConfidenceCalculator() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(urlAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        public static ApiDegreeConfidenceCalculator getInstancia(){
            if (instancia == null){
                instancia = new ApiDegreeConfidenceCalculator();
            }
            return instancia;
        }

        public CommunityApi calculateDegreeConfident(CommunityToCalculate communityToEvaluate) throws IOException {
            DegreeOfConfidenceCalculator degreeOfConfidenceCalculator = this.retrofit.create(DegreeOfConfidenceCalculator.class);
            Call<CommunityApi> request = degreeOfConfidenceCalculator.communityEvaluated(communityToEvaluate);
            Response<CommunityApi> response = request.execute();
            return  response.body();
        }


    }

