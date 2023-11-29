package ar.edu.utn.frba.dds.repositories.entities.locations;

import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class LocalityRepository extends GenericRepositoryJPA<Locality> {

    private static LocalityRepository instance;

    public static synchronized LocalityRepository getInstance() {
        if (instance == null) {
            instance = new LocalityRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Locality readByName(String nombre){

        Query query =  entityManager().createQuery(" from " + Locality.class.getName() + " where name = :parametro")
                .setParameter("parametro", nombre);


        List<Locality> resultados = query.getResultList();

        return resultados.get(0);
    }
}
