package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.models.validators.validation.Password10000Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Password10000ValidationTest {


  @Test
  public void yankeesNoTienEnLaEnieAsPassword() {

    Password10000Validation password10000Validation = new Password10000Validation(new FileReader());

    String password = "Contraseña";
    String user = "user";


    // los yankees no tienen la ñ en el teclado por lo tanto es contraseña segura!!
    Assertions.assertTrue(password10000Validation.validatePassword(user,password));
  }

  @Test
  public void firstWordFileAsPassword() {

    Password10000Validation password10000Validation = new Password10000Validation(new FileReader());

    String password = "123456";
    String user = "user";

    // no nos debe dejar tener la password si esta en el archivo, debe devolver False
    Assertions.assertFalse(password10000Validation.validatePassword(user,password));
  }

  @Test
  public void lastWordFileAsPassword() {

    Password10000Validation password10000Validation = new Password10000Validation(new FileReader());

    String password = "brady";
    String user = "user";

    // no nos debe dejar tener esta contraseña
    Assertions.assertFalse(password10000Validation.validatePassword(user,password));
  }

}