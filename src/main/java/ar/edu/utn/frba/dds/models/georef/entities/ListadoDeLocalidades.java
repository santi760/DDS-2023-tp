package ar.edu.utn.frba.dds.models.georef.entities;

import ar.edu.utn.frba.dds.models.georef.Localidad;
import ar.edu.utn.frba.dds.models.georef.Municipio;

import java.util.List;

public class ListadoDeLocalidades {

    public int inicio;
    public int cantidad;
    public int total;
    public List<Localidad> localidades;

    private class Parametro{
        public List<String> campos;
        public int max;
        public List<String> provincia;
    }
}
