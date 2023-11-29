package ar.edu.utn.frba.dds.models.georef;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Municipio {
    public int id;
    public String nombre;

    public Centroide centroide;
}
