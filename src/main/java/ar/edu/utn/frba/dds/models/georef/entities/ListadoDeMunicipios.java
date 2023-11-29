package ar.edu.utn.frba.dds.models.georef.entities;

import ar.edu.utn.frba.dds.models.georef.Municipio;

import java.util.List;

public class ListadoDeMunicipios {
    public int inicio;
    public int cantidad;
    public int total;
    public List<Municipio> municipios;

    private class Parametro{
        public List<String> campos;
        public int max;
        public List<String> provincia;
    }
}
