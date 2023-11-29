package ar.edu.utn.frba.dds.repositories.users;

import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

public class ControlOrganismRepository extends GenericRepositoryJPA<ControlOrganism> {

    private static ControlOrganismRepository instance;

    public static synchronized ControlOrganismRepository getInstance() {
        if (instance == null) {
            instance = new ControlOrganismRepository();
        }
        instance.setEntities();
        return instance;
    }

    public ControlOrganism getControlOrganismWithUser(User user){

        return (ControlOrganism) entityManager().createQuery(" from " + ControlOrganism.class.getName() +
                " where user_id = :user_id")
            .setParameter("user_id", user)
            .getSingleResult();

    }

}
