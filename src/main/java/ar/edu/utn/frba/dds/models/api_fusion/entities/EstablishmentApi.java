package ar.edu.utn.frba.dds.models.api_fusion.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstablishmentApi {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"name\":\"" + name + '\"' +
                '}';
    }
}
