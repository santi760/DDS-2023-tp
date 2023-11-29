package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.validators.PasswordValidator;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

public class PasswordValidatorRepository extends GenericRepositoryJPA<PasswordValidator>{

    private static PasswordValidatorRepository instance;

    public static synchronized PasswordValidatorRepository getInstance() {
        if (instance == null) {
            instance = new PasswordValidatorRepository();
        }
        instance.setEntities();
        return instance;
    }
}
