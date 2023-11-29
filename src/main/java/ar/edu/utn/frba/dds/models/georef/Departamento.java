package ar.edu.utn.frba.dds.models.georef;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Departamento {
    public int id;
    public String nombre;

    public Centroide centroide;
}
