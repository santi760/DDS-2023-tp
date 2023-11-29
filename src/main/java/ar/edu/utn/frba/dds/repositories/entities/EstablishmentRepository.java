package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import javax.persistence.Query;
import java.util.List;

public class EstablishmentRepository extends GenericRepositoryJPA<Establishment> {

    private static EstablishmentRepository instance;

    public static synchronized EstablishmentRepository getInstance() {
        if (instance == null) {
            instance = new EstablishmentRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Establishment readByName(String nombre){

        Query query =  entityManager().createQuery(" from " + Establishment.class.getName() + " where name = :parametro")
                .setParameter("parametro", nombre);


        List<Establishment> resultados = query.getResultList();

        return resultados.get(0);
    }

    public List<Establishment> getEstablishmentsFromEntity(Entity entity){
        //entityManager().clear();
        return (List<Establishment>) entityManager().createQuery(" from " + Establishment.class.getName() +
            " where entity_id = :entity_id ").
            setParameter("entity_id", entity).getResultList();
    }

    public void removeRelationWithEntity(Establishment establishment){

        entityTransaction.begin();
        entityManager().createQuery(" update " + Establishment.class.getName() + " set " + " entity_id = null " + " where id = :id")
            .setParameter("id", establishment.getId()).executeUpdate();
        entityTransaction.commit();
    }

}
