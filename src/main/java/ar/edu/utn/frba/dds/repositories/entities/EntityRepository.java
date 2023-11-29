package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.services.Service;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class EntityRepository extends GenericRepositoryJPA<Entity> {

    private static EntityRepository instance;

    public static synchronized EntityRepository getInstance() {
        if (instance == null) {
            instance = new EntityRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Entity readAllForServicies(Service servicio) {

        String text = "SELECT e.id, e.name, e.type, e.typePublicTransport, e.providerEntity_id FROM service s " +
            " join provisionofservice ps on s.id = ps.service_id" +
            " join establishment e on e.id = ps.establishment_id" +
            " join entity e on e.id = e.entity_id" +
            "where s.id = :parametro";

        Query query = entityManager().createQuery(text, Entity.class);
        query.setParameter("parametro", servicio.getId());

        List<Entity> resultados = query.getResultList();

        return resultados.get(0);
    }

    //no hace falta este metodo por ahora
    public List<Entity> readAllForMember(Long member) {

        String text = "select e from " + Entity.class.getName() + " e" +
            // " join communitymember_x_entity ce on e.id = ce.id_entity" +
            " join " + Community.class.getName() + " c" + //on ce.id_communityMember = c.id
            " where c.id = :parametro";
        String queryText = "select distinct e from " + Entity.class.getName() + " e," + Community.class.getName() + " c" +
            " where c.id = :parametro";

        Query query = entityManager().createQuery(queryText, Entity.class);
        query.setParameter("parametro", member);

        List<Entity> resultados = query.getResultList();


        return resultados;
    }

    //este metodo no se usa por ahora
    public Set<Entity> readAllForCommunity(Long id) {

        String text = "select e.id, e.name, e.type, e.typePublicTransport, e.providerEntity_id from entity e" +
            " join communitymember_x_entity ce on e.id = ce.id_entity" +
            " join communitymember c on ce.id_communityMember = c.id" +
            " join communitymember_x_community cc on cc.id_community_member = ce.id" +
            " join community cm on cm.id = cc.id_community" +
            " where cm.id = :parametro";

        Query query = entityManager().createQuery(text, Entity.class);
        query.setParameter("parametro", id);

        Set<Entity> resultados = (Set<Entity>) query.getResultList();

        return null;
    }

    public Entity getEntityNamed (String anEntityName){

        return (Entity) entityManager().createQuery(" from " + Entity.class.getName() + " where name = :name")
            .setParameter("name", anEntityName)
            .getSingleResult();

    }




    public List<Entity>  getEntitiesFromProvider (ProviderEntity aProvider){
        entityManager().clear();
        return (List<Entity>) entityManager().createQuery(" from " + Entity.class.getName() + " where providerEntity_id = :providerEntity_id")
            .setParameter("providerEntity_id", aProvider)
            .getResultList();

    }


    public void updateProvider(Entity entity, ProviderEntity providerEntity){

        entityTransaction.begin();
        entityManager().createQuery(" update " + Entity.class.getName() + " set " + " providerEntity_id = :providerEntity_id " + " where id = :id")
            .setParameter("providerEntity_id", providerEntity.getId())
            .setParameter("id", entity.getId()).executeUpdate();
        entityTransaction.commit();
    }
  
  
    public void deleteEntityFromProvider(Entity entity, ProviderEntity providerEntity){
         entityManager().createQuery("update " + Entity.class.getName() + "set " +
                " providerEntity_id = null " + " where providerEntity_id = :providerEntity_id"
             + " and id = :id")
            .setParameter("providerEntity_id", providerEntity)
              .setParameter("id",entity);
    }

}

