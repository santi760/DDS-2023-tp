package ar.edu.utn.frba.dds.models.georef.entities;

import ar.edu.utn.frba.dds.models.georef.Departamento;

import java.util.List;

public class ListadoDeDepartamentos {
    public int cantidad;
    public int total;
    public int inicio;
    public List<Departamento> departamentos;

    private class Parametro{
        public List<String> campos;
        public int max;
        public List<String> provincia;
    }
}
