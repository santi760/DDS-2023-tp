package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import javax.persistence.Query;
import java.util.List;

public class ServiceRepository extends GenericRepositoryJPA<Service> {

    private static ServiceRepository instance;

    public static synchronized ServiceRepository getInstance() {
        if (instance == null) {
            instance = new ServiceRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Service readByName(String nombre){

        Query query =  entityManager().createQuery(" from " + Service.class.getName() + " where name = :parametro")
                .setParameter("parametro", nombre);


        List<Service> resultados = query.getResultList();

        return resultados.get(0);
    }


}
