package ar.edu.utn.frba.dds.models.georef;

import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeMunicipios;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Path;

import java.util.List;

public interface GeorefService {
    @GET("provincias")
    Call<ListadoDeProvincias> provincias();

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("id") String id);


    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") String idProvincia);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipiosTodos(@Query("max") int cantidad);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipiosId(@Query("id") String idMunicipio);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("id") String idDepartamento);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentosTodos(@Query("max") int cantidad);

    @GET("localidades")
    Call<ListadoDeLocalidades> localidades();

    @GET("localidades")
    Call<ListadoDeLocalidades> localidades(@Query("id") String idLocalidad);

    @GET("localidades")
    Call<ListadoDeLocalidades> localidadesTodos(@Query("max") int cantidad);




}
