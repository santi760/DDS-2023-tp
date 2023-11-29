package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.community.NotificationMessage;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

public class NotificationMessageRepository extends GenericRepositoryJPA<NotificationMessage>{

    private static NotificationMessageRepository instance;

    public static synchronized NotificationMessageRepository getInstance() {
        if (instance == null) {
            instance = new NotificationMessageRepository();
        }
        instance.setEntities();
        return instance;
    }
}
