package ar.edu.utn.frba.dds.models.degree_confidence_calculator.entities;

import java.util.List;

public class CommunityToCalculate {
    private List<UserApi> usuarios;
    private List<IncidentApi> incidentes;


    @Override
    public String toString() {
        return "{" +
                "\"usuarios\":" + usuarios +
                ", \"incidentes\":" + incidentes +
                '}';
    }
}
