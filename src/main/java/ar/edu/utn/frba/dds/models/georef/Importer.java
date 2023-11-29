package ar.edu.utn.frba.dds.models.georef;


import ar.edu.utn.frba.dds.models.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeMunicipios;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeProvincias;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Importer {
    private static int cantidadLocalidades = 4142;
    private static int cantidadMunicipios = 1814;
    private static int cantidadDepartamentos = 529;

    ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();



    public List<Localidad> locationsImporter() throws IOException {
        return servicioGeoref.listadoDeLocalidadesTodos(cantidadLocalidades).localidades;
    }

    public List<Departamento> departamentsImporter() throws IOException{
        return servicioGeoref.listadoDeDepartamentosTodos(cantidadDepartamentos).departamentos;

    }

    public List<Municipio> municipalitysImporter() throws IOException {
        return servicioGeoref.listadoDeMunicipiosTodos(cantidadMunicipios).municipios;
    }

    public List<Provincia> provinciesImporter() throws IOException {
        return servicioGeoref.listadoDeProvinciasTodas().provincias;
    }

}
