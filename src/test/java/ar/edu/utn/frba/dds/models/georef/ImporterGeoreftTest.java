package ar.edu.utn.frba.dds.models.georef;


import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeProvincias;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Test;
import retrofit2.http.PUT;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImporterGeoreftTest {


    ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();
    int cantidad = 200;

    @SneakyThrows
    @Test
    public void importarTodasLasProvincias(){

        Importer importador = new Importer();
        for (int i = 0; i < importador.provinciesImporter().size(); i++) {

            assertEquals(importador.provinciesImporter().get(i).getNombre(), servicioGeoref.listadoDeProvinciasTodas().provincias.get(i).getNombre());
            System.out.println(importador.provinciesImporter().get(i).getNombre() +" VS "+ servicioGeoref.listadoDeProvinciasTodas().provincias.get(i).getNombre());
        }
        LocationsSetter locationsSetter = new LocationsSetter();
        List<Province> provinces = locationsSetter.provinciesSetter(importador);

        for (int i = 0; i < importador.provinciesImporter().size(); i++) {

            assertEquals(importador.provinciesImporter().get(i).getNombre(),  provinces.get(i).getName());
            System.out.println(importador.provinciesImporter().get(i).getNombre() +" VS "+ provinces.get(i).getName());
        }

    }
    @SneakyThrows
    @Test
    public void importarTodasLosMunicipios(){

        Importer importador = new Importer();
        for (int i = 0; i < importador.municipalitysImporter().size(); i++) {

            assertEquals(importador.municipalitysImporter().get(i).getNombre(), servicioGeoref.listadoDeMunicipiosTodos(cantidad).municipios.get(i).getNombre());
            //System.out.println(importador.provinciesImporter().get(i).getNombre() +" VS "+ servicioGeoref.listadoDeProvinciasTodas().provincias.get(i).getNombre());
        }

        LocationsSetter locationsSetter = new LocationsSetter();
        List<Municipality> municipalities = locationsSetter.municipalitiesSetter(importador);

        for (int i = 0; i < importador.municipalitysImporter().size(); i++) {

            assertEquals(importador.municipalitysImporter().get(i).getNombre(),  municipalities.get(i).getName());
            System.out.println(importador.municipalitysImporter().get(i).getNombre() +" VS "+ municipalities.get(i).getName());
        }

    }
    @SneakyThrows
    @Test
    public void importarTodasLasLocalidades(){

        Importer importador = new Importer();
        for (int i = 0; i < importador.locationsImporter().size(); i++) {

            assertEquals(importador.locationsImporter().get(i).getNombre(), servicioGeoref.listadoDeLocalidadesTodos(cantidad).localidades.get(i).getNombre());
            //System.out.println(importador.provinciesImporter().get(i).getNombre() +" VS "+ servicioGeoref.listadoDeProvinciasTodas().provincias.get(i).getNombre());
        }

        LocationsSetter locationsSetter = new LocationsSetter();
        List<Locality> localities = locationsSetter.localitiesSetter(importador);

        for (int i = 0; i < importador.locationsImporter().size(); i++) {

            assertEquals(importador.locationsImporter().get(i).getNombre(),  localities.get(i).getName());
            System.out.println(importador.locationsImporter().get(i).getNombre() +" VS "+ localities.get(i).getName());
        }

    }
    @SneakyThrows
    @Test
    public void importarTodasLosDepartamentos(){

        Importer importador = new Importer();
        for (int i = 0; i < importador.departamentsImporter().size(); i++) {

            assertEquals(importador.departamentsImporter().get(i).getNombre(), servicioGeoref.listadoDeDepartamentosTodos(cantidad).departamentos.get(i).getNombre());
            //System.out.println(importador.provinciesImporter().get(i).getNombre() +" VS "+ servicioGeoref.listadoDeProvinciasTodas().provincias.get(i).getNombre());
        }

        LocationsSetter locationsSetter = new LocationsSetter();
        List<Department> departments = locationsSetter.departmentsSetter(importador);

        for (int i = 0; i < importador.departamentsImporter().size(); i++) {

            assertEquals(importador.departamentsImporter().get(i).getNombre(),  departments.get(i).getName());
            System.out.println(importador.departamentsImporter().get(i).getNombre() +" VS "+ departments.get(i).getName());
        }



    }

}
