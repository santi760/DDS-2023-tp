package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.models.validators.validation.PasswordLengthValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordLengthValidationTest {



  @Test
  public void testPasswordLessThan8Characters(){

    String password0 = "contra";
    String password1 = "cinco";
    String password2 = "7777777";

    PasswordLengthValidation passwordLengthValidation = new PasswordLengthValidation();

    // no deberia dejarme tener ninguna de estas contraseñas
    assertFalse(passwordLengthValidation.validatePassword( null,password0));
    assertFalse(passwordLengthValidation.validatePassword( null,password1));
    assertFalse(passwordLengthValidation.validatePassword( null,password2));

  }

  @Test
  public void testEmptyPassword(){

    String emptypassword = "";

    PasswordLengthValidation passwordLengthValidation = new PasswordLengthValidation();

    // no deberia dejarme tener ninguna de estas contraseñas
    assertFalse(passwordLengthValidation.validatePassword( null,emptypassword));
  }



  @Test
  public void testPassword8Characters(){

    String password0 = "88888888";
    String password1 = "contrase";

    PasswordLengthValidation passwordLengthValidation = new PasswordLengthValidation();

    // Debe dejarme tener una contraseña de 8 caracteres.
    assertTrue(passwordLengthValidation.validatePassword( null,password0));
    assertTrue(passwordLengthValidation.validatePassword( null,password1));
  }


  @Test
  public void testPasswordGreaterThan8Characters(){

    String password0 = "88888888asdasdasd";
    String password1 = "fdsfdasfwdfdsfasdefwdfdsfdsfdsf";

    PasswordLengthValidation passwordLengthValidation = new PasswordLengthValidation();

    // Debe dejarme tener una contraseña mayor de 8 caracteres.
    assertTrue(passwordLengthValidation.validatePassword( null,password0));
    assertTrue(passwordLengthValidation.validatePassword( null,password1));
  }


}// end class