package ar.edu.utn.frba.dds.repositories.users;

import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import javax.persistence.Query;
import java.util.List;

public class UserRepository extends GenericRepositoryJPA<User> {

    private static UserRepository instance;

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        instance.setEntities();
        return instance;
    }

        public User readByUsername(String username){

            Query query =  entityManager().createQuery(" from " + User.class.getName() + " where name = :parametro")
                    .setParameter("parametro", username);

            List<User> users = query.getResultList();

            return users.get(0);
    }

}

