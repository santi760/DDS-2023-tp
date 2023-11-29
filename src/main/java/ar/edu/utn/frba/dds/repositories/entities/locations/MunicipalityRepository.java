package ar.edu.utn.frba.dds.repositories.entities.locations;

import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class MunicipalityRepository extends GenericRepositoryJPA<Municipality> {

    private static MunicipalityRepository instance;

    public static synchronized MunicipalityRepository getInstance() {
        if (instance == null) {
            instance = new MunicipalityRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Municipality readByName(String nombre){

        Query query =  entityManager().createQuery(" from " + Municipality.class.getName() + " where name = :parametro")
                .setParameter("parametro", nombre);


        List<Municipality> resultados = query.getResultList();

        return resultados.get(0);
    }
}
