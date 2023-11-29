package ar.edu.utn.frba.dds.models.georef;

import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeMunicipios;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeProvincias;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static final String urlAPI = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static ServicioGeoref getInstancia(){
        if (instancia == null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }


    public ListadoDeProvincias listadoDeProvincias(String id) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArg = georefService.provincias(id);
        Response<ListadoDeProvincias> responseProvinciasArgs = requestProvinciasArg.execute();
        return responseProvinciasArgs.body();


    }

    public ListadoDeProvincias listadoDeProvinciasTodas() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArg = georefService.provincias();
        Response<ListadoDeProvincias> responseProvinciasArgs = requestProvinciasArg.execute();
        return responseProvinciasArgs.body();


    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincias(String id) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestMunicipiosDeProvincia = georefService.municipios(id);
        Response<ListadoDeMunicipios> responseMunicipiosDeProvincia = requestMunicipiosDeProvincia.execute();
        return  responseMunicipiosDeProvincia.body();
    }



    public ListadoDeMunicipios listadoDeMunicipios(String id) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestMunicipiosDeProvincia = georefService.municipiosId(id);
        Response<ListadoDeMunicipios> responseMunicipiosDeProvincia = requestMunicipiosDeProvincia.execute();
        return  responseMunicipiosDeProvincia.body();
    }

    public ListadoDeMunicipios listadoDeMunicipiosTodos(int cantidad) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestMunicipiosDeProvincia = georefService.municipiosTodos(cantidad);
        Response<ListadoDeMunicipios> responseMunicipiosDeProvincia = requestMunicipiosDeProvincia.execute();
        return  responseMunicipiosDeProvincia.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentos(String id) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestDepartamentossDeProvincia = georefService.departamentos(id);
        Response<ListadoDeDepartamentos> responseDepartamentossDeProvincia = requestDepartamentossDeProvincia.execute();
        return  responseDepartamentossDeProvincia.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentosTodos(int cantidad) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestDepartamentossDeProvincia = georefService.departamentosTodos(cantidad);
        Response<ListadoDeDepartamentos> responseDepartamentossDeProvincia = requestDepartamentossDeProvincia.execute();
        return  responseDepartamentossDeProvincia.body();
    }

    public ListadoDeLocalidades listadoDeLocalidades(String id) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeLocalidades> requestLocalidadesDeProvincia = georefService.localidades(id);
        Response<ListadoDeLocalidades> responseLocalidadesDeProvincia = requestLocalidadesDeProvincia.execute();
        return  responseLocalidadesDeProvincia.body();
    }

    public ListadoDeLocalidades listadoDeLocalidades() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeLocalidades> requestLocalidadesDeProvincia = georefService.localidades();
        Response<ListadoDeLocalidades> responseLocalidadesDeProvincia = requestLocalidadesDeProvincia.execute();
        return  responseLocalidadesDeProvincia.body();
    }

    public ListadoDeLocalidades listadoDeLocalidadesTodos(int cantidad) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeLocalidades> requestLocalidadesDeProvincia = georefService.localidadesTodos(cantidad);
        Response<ListadoDeLocalidades> responseLocalidadesDeProvincia = requestLocalidadesDeProvincia.execute();
        return  responseLocalidadesDeProvincia.body();
    }



}
