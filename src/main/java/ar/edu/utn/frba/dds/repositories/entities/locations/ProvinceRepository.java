package ar.edu.utn.frba.dds.repositories.entities.locations;

import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class ProvinceRepository extends GenericRepositoryJPA<Province> {

    private static ProvinceRepository instance;

    public static synchronized ProvinceRepository getInstance() {
        if (instance == null) {
            instance = new ProvinceRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Province readByName(String nombre){

        Query query =  entityManager().createQuery(" from " + Province.class.getName() + " where name = :parametro")
                .setParameter("parametro", nombre);


        List<Province> resultados = query.getResultList();

        return resultados.get(0);
    }
}
