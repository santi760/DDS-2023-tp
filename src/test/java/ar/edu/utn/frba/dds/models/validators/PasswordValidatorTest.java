package ar.edu.utn.frba.dds.models.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class PasswordValidatorTest {

  // bueno aca toca testear
   @Test
  public void generate3passworderrors(){
    PasswordValidator passwordValidator = new PasswordValidator();
    String password = "justin";
    String user = "justin";

    List<String> errors = passwordValidator.validate(user,password);
    System.out.println(errors);
    Assertions.assertEquals(3,errors.size());

  }

  @Test
  public void generate4passworderrors(){
    PasswordValidator passwordValidator = new PasswordValidator();
    String password = "1234";
    String user = "1234";

    List<String> errors = passwordValidator.validate(user,password);
    System.out.println(errors);

    Assertions.assertEquals(4,errors.size());
    }

  @Test
  public void generate2passworderrors(){
    PasswordValidator passwordValidator = new PasswordValidator();
    String password = "";
    String user = "nachoBorda";

    List<String> errors = passwordValidator.validate(user,password);
    System.out.println(errors);

    Assertions.assertEquals(1,errors.size());
    //[La contraseña no puede ser igual que el nombre de usuario, Error. La contraseña no puede repetir 3 veces un caracter]
  }

}