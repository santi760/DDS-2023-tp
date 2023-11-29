package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import ar.edu.utn.frba.dds.models.validators.PasswordValidator;
import ar.edu.utn.frba.dds.models.validators.validation.Password10000Validation;
import ar.edu.utn.frba.dds.models.validators.validation.PasswordConsecutiveCharactersValidation;
import ar.edu.utn.frba.dds.models.validators.validation.PasswordLengthValidation;
import ar.edu.utn.frba.dds.models.validators.validation.PasswordRepeatCharacters;
import ar.edu.utn.frba.dds.models.validators.validation.PasswordUsernameValidation;
import ar.edu.utn.frba.dds.repositories.entities.PasswordValidatorRepository;
import org.junit.jupiter.api.Test;

public class PasswordValidatorRepositoryTest {

  @Test
  public void PasswordValidatorTest(){
    Long n = 1L;
    PasswordValidator validator = PasswordValidatorRepository.getInstance().read(n);

    assertEquals(validator.getId(), 1);


  }

}
