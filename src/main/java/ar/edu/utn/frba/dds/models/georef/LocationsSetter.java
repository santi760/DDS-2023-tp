package ar.edu.utn.frba.dds.models.georef;


import ar.edu.utn.frba.dds.models.locations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationsSetter {


    public List<Province> provinciesSetter(Importer importador) throws IOException {
        List<Province> provincies = new ArrayList<>();
        List<Provincia> provincias = importador.provinciesImporter();

        for (Provincia provincia : provincias ) {
            int id = provincia.getId();
            String name = provincia.getNombre();
            double lat = provincia.getCentroide().getLat();
            double lon = provincia.getCentroide().getLon();

            Coordinate coordinate = new Coordinate(lat, lon);
            Province province = new Province(id, name, coordinate);
            provincies.add(province);
        }
        return provincies;
    }

    public List<Municipality> municipalitiesSetter(Importer importador) throws IOException {
        List<Municipality> municipalities = new ArrayList<>();
        List<Municipio> municipios = importador.municipalitysImporter();

        for (Municipio municipio : municipios ) {
            int id = municipio.getId();
            String name = municipio.getNombre();
            double lat = municipio.getCentroide().getLat();
            double lon = municipio.getCentroide().getLon();

            Coordinate coordinate = new Coordinate(lat, lon);
            Municipality municipality = new Municipality(id, name, coordinate);
            municipalities.add(municipality);
        }
        return municipalities;
    }

    public List<Locality> localitiesSetter(Importer importador) throws IOException {
        List<Locality> localities = new ArrayList<>();
        List<Localidad> localidades = importador.locationsImporter();

        for (Localidad localidad : localidades ) {
            String id = localidad.getId();
            String name = localidad.getNombre();
            double lat = localidad.getCentroide().getLat();
            double lon = localidad.getCentroide().getLon();

            Coordinate coordinate = new Coordinate(lat, lon);
            Locality locality = new Locality(id, name, coordinate);
            localities.add(locality);
        }
        return localities;
    }

    public List<Department> departmentsSetter(Importer importador) throws IOException {
        List<Department> departments = new ArrayList<>();
        List<Departamento> departamentos = importador.departamentsImporter();

        for (Departamento departamento : departamentos ) {
            int id = departamento.getId();
            String name = departamento.getNombre();
            double lat = departamento.getCentroide().getLat();
            double lon = departamento.getCentroide().getLon();

            Coordinate coordinate = new Coordinate(lat, lon);
            Department department = new Department(id, name, coordinate);
            departments.add(department);
        }
        return departments;
    }
}
