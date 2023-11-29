package ar.edu.utn.frba.dds.models.degree_confidence_calculator.entities;

public class UserApi {

    private int id;
    private int puntaje_inicial;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"puntaje_inicial\":\"" + puntaje_inicial + '\"' +
                '}';
    }
}
