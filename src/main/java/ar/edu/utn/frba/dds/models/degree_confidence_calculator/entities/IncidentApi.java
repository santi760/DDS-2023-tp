package ar.edu.utn.frba.dds.models.degree_confidence_calculator.entities;

import java.time.LocalDateTime;

public class IncidentApi {

    private int id;
    private String idEstablecimiento;
    private String idServicioAfectado;
    private LocalDateTime fechaDeApertura;
    private LocalDateTime fechaDeCierre;
    private String idUsuarioDeApertura;
    private String idUsuarioDeCierre;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"idServicioAfectado\":\"" + idServicioAfectado + '\"' +
                ", \"fechaDeApertura\":\"" + fechaDeApertura + '\"' +
                ", \"fechaDeCierre\":\"" + fechaDeCierre + '\"' +
                ", \"idUsuarioDeApertura\":" + idUsuarioDeApertura +
                ", \"idUsuarioDeCierre\":" + idUsuarioDeCierre +
                '}';
    }
}
