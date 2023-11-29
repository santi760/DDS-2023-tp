package ar.edu.utn.frba.dds.repositories.users;

import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import java.util.List;

public class ProviderEntityRepository extends GenericRepositoryJPA<ProviderEntity> {

    private static ProviderEntityRepository instance;

    public static synchronized ProviderEntityRepository getInstance() {
        if (instance == null) {
            instance = new ProviderEntityRepository();
        }
        instance.setEntities();
        return instance;
    }

    public ProviderEntity getProviderEntityWithUser(User user){

        return (ProviderEntity) entityManager().createQuery(" from " + ProviderEntity.class.getName() +
                " where user_id = :user_id")
            .setParameter("user_id", user)
            .getSingleResult();

    }

    public List<ProviderEntity> getEntitiesFromOrganism (ControlOrganism controlOrganism){
        //entityManager().clear();
        return (List<ProviderEntity>) entityManager().createQuery(" from " + ProviderEntity.class.getName() + " where controlOrganism_id = :controlOrganism_id")
            .setParameter("controlOrganism_id", controlOrganism)
            .getResultList();

    }

    public List<ProviderEntity> readNotIncludedOrganism (ControlOrganism controlOrganism){
        //entityManager().clear();
        return (List<ProviderEntity>) entityManager().createQuery(" from " + ProviderEntity.class.getName() + " where controlOrganism_id <>  :controlOrganism_id"
            + " or controlOrganism_id is null ")
            .setParameter("controlOrganism_id", controlOrganism)
            .getResultList();

    }

    public void removeOrganismFromProvider(ProviderEntity providerEntity){

        entityTransaction.begin();
        entityManager().createQuery(" update " + ProviderEntity.class.getName() + " set " + " controlOrganism_id = null " + " where id = :id")
            .setParameter("id", providerEntity.getId()).executeUpdate();
        entityTransaction.commit();
    }

    public void addOrganism(ProviderEntity providerEntity, ControlOrganism controlOrganism){

        entityTransaction.begin();
        entityManager().createQuery(" update " + ProviderEntity.class.getName() + " set " + " controlOrganism_id = :controlOrganism_id "
                + " where id = :id")
            .setParameter("controlOrganism_id",controlOrganism.getId())
            .setParameter("id", providerEntity.getId()).executeUpdate();
        entityTransaction.commit();
    }

}
