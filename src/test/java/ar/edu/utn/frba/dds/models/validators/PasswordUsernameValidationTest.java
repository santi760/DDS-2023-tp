package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.models.validators.validation.PasswordUsernameValidation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class PasswordUsernameValidationTest {

  @Test
  public void testValidatePasswordSameUsernameAndPassword(){
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = "Juan123";
    String password = "Juan123";

    assertFalse(validator.validatePassword(userName, password));
  }

  @Test
  public void testValidatePassword_differentUsernameAndPassword() {
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = "Maria456";
    String password = "Carlos789";
    assertTrue(validator.validatePassword(userName, password));

  }

  @Test
  public void testValidatePassword_nullPassword() {
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = "Pepe567";
    String password = null;
    assertTrue(validator.validatePassword(userName, password));

  }

  @Test
  public void testValidatePassword_nullUsername() {
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = null;
    String password = "password123";
   assertTrue(validator.validatePassword(userName, password));

  }

  @Test
  public void testValidatePassword_nullUsernameAndPassword() {
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = null;
    String password = null;
    assertFalse(validator.validatePassword(userName, password));
  }

  @Test
  public void testValidatePassword_emptyUsernameAndPassword() {
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = "";
    String password = "";
    assertFalse(validator.validatePassword(userName, password));
  }

  @Test
  public void testValidatePassword_blankUsernameAndPassword() {
    PasswordUsernameValidation validator = new PasswordUsernameValidation();
    String userName = " ";
    String password = " ";
    assertFalse(validator.validatePassword(userName, password));
  }
}

/*
* Caso de prueba donde la contraseña y el nombre de usuario son iguales:
userName: "Juan123"
password: "Juan123"
Se espera que el método validatePassword devuelva false.
Caso de prueba donde la contraseña y el nombre de usuario son distintos:
userName: "Maria456"
password: "Carlos789"
Se espera que el método validatePassword devuelva true.
Caso de prueba donde la contraseña es nula:
userName: "Pepe567"
password: null
Se espera que el método validatePassword devuelva true.
Caso de prueba donde el nombre de usuario es nulo:
userName: null
password: "password123"
Se espera que el método validatePassword devuelva true.
Caso de prueba donde tanto el nombre de usuario como la contraseña son nulos:
userName: null
password: null
Se espera que el método validatePassword devuelva true.
Caso de prueba donde el nombre de usuario y la contraseña son cadenas vacías:
userName: ""
password: ""
Se espera que el método validatePassword devuelva true.
Caso de prueba donde el nombre de usuario y la contraseña son cadenas de espacios en blanco:
userName: " "
password: " "
Se espera que el método validatePassword devuelva true.
*
* */