package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.models.validators.validation.PasswordRepeatCharacters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordRepeatCharactersTest {


  @Test
  public void thePasswordDoesNotContainThreeIdenticalConsecutiveCharacters(){
    PasswordRepeatCharacters passwordRepeatCharacters = new PasswordRepeatCharacters();

    assertTrue(passwordRepeatCharacters.validatePassword("johndoe", "Abcde1"));
    assertTrue(passwordRepeatCharacters.validatePassword("janedoe", "p@ssw0rd"));
    assertTrue(passwordRepeatCharacters.validatePassword("bobsmith", "Secret123!"));
    assertTrue(passwordRepeatCharacters.validatePassword("alicelee", "abcdEFGH789"));
  }




  @Test
  void thePasswordContainIdenticalConsecutiveCharacters() {
    PasswordRepeatCharacters passwordRepeatCharacters = new PasswordRepeatCharacters();

    // Casos de prueba inválidos
    assertFalse(passwordRepeatCharacters.validatePassword("johndoe", "aaaAbcde1"));
    assertFalse(passwordRepeatCharacters.validatePassword("alicelee", "abbbcdEFGH789"));
    assertFalse(passwordRepeatCharacters.validatePassword("johnsmith", "Abcccde1"));
  }

}