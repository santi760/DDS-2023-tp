package ar.edu.utn.frba.dds.models.georef;

import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeProvincias;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioGeorefTest {


  @Test
  public void getLocationFromApi() throws IOException{

    ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
    ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvincias("22");
    // ----------------- USAMOS COMPONENTE EXTERNO GEOREF PARA CONSEGUIR LA LATITUD Y LONGITUD

    double lat =  listadoDeProvincias.provincias.get(0).centroide.getLat();
    double lon =  listadoDeProvincias.provincias.get(0).centroide.getLon();

    assertEquals(-26.3864309061226,lat);
    assertEquals(-60.7658307438603,lon);

  }

  @Test
  public void assignLocationToMember() throws IOException{
    CommunityMember member = new CommunityMember();


    ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
    ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvincias("22");
    // ----------------- USAMOS COMPONENTE EXTERNO GEOREF PARA CONSEGUIR LA LATITUD Y LONGITUD

    String nombreProvincia =  listadoDeProvincias.provincias.get(0).nombre;

    Province province = new Province(nombreProvincia);

    member.getPerson().setProvince(province);

    assertEquals("Chaco",member.getPerson().getProvince().getName());

  }




}