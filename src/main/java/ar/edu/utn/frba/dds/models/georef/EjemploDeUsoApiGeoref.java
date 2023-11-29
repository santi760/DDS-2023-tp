package ar.edu.utn.frba.dds.models.georef;

import ar.edu.utn.frba.dds.models.georef.*;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeMunicipios;
import ar.edu.utn.frba.dds.models.georef.entities.ListadoDeProvincias;

import java.io.IOException;

public class EjemploDeUsoApiGeoref {
    public static void main(String[] args) throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.getInstancia();

        ListadoDeMunicipios listadoDeMunicipios = servicioGeoref.listadoDeMunicipiosDeProvincias("820196");
        ListadoDeProvincias listadoDeProvincias = servicioGeoref.listadoDeProvinciasTodas();
        ListadoDeMunicipios listadoDeMunicipiosId = servicioGeoref.listadoDeMunicipiosTodos(2000);
        ListadoDeDepartamentos listadoDepartamentos = servicioGeoref.listadoDeDepartamentosTodos(2000);
        ListadoDeLocalidades listadoDeLocalidadesTodos = servicioGeoref.listadoDeLocalidadesTodos(2000);

        ListadoDeLocalidades listadoDeLocalidades = servicioGeoref.listadoDeLocalidades("14098270000");

        System.out.println(listadoDeProvincias.provincias.get(0).getCentroide().getLat());
        System.out.println(listadoDeProvincias.provincias.get(0).centroide.getLon());
        System.out.println(listadoDeLocalidades.localidades.get(0).centroide.getLat());
        System.out.println(listadoDeMunicipiosId.municipios.get(0).nombre);


/*
        for (Provincia unaProvincia : listadoDeProvincias.provincias) {
            System.out.println(unaProvincia.nombre);

        }
*/
        /*for (Municipio unMunicipio : listadoDeMunicipiosId.municipios) {
            System.out.println(unMunicipio.nombre);

        }*/


        /*for (Departamento unDepartamento : listadoDepartamentos.departamentos) {
            System.out.println(unDepartamento.nombre);

        }*/

        for (Localidad unaLocalidad : listadoDeLocalidadesTodos.localidades) {
            System.out.println(unaLocalidad.nombre);

        }
    }
}

