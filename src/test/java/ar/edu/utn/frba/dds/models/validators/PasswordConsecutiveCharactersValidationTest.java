package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.models.validators.validation.PasswordConsecutiveCharactersValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordConsecutiveCharactersValidationTest {


  public PasswordConsecutiveCharactersValidation passwordConsecutiveCharactersValidation = new PasswordConsecutiveCharactersValidation();
  public static String UsernameGlobal = "usernameGlobal";

  @Test
  public void testSecurePassword() {
    // Prueba que la función devuelve verdadero para una contraseña segura
    assertTrue(passwordConsecutiveCharactersValidation.validatePassword(UsernameGlobal, "s3gur0P@$$w0rd"));
  }

  @Test
  public void testContrainseguraSecuenciaNumericaAscendente() {
    // Prueba que la función devuelve falso para una contraseña insegura con una secuencia numérica ascendente
    assertFalse(passwordConsecutiveCharactersValidation.validatePassword(UsernameGlobal, "abc1234"));
  }

  @Test
  public void testContraseñaInseguraSecuenciaNumericaDescendente() {
    // Prueba que la función devuelve falso para una contraseña insegura con una secuencia numérica descendente
    assertFalse(passwordConsecutiveCharactersValidation.validatePassword(UsernameGlobal, "4321xyz"));
  }

  @Test
  public void testContraSeguraSinSecuenciaNumerica() {
    // Prueba que la función devuelve verdadero para una contraseña que no contiene ninguna secuencia numérica
    assertTrue(passwordConsecutiveCharactersValidation.validatePassword(UsernameGlobal, "contraseñaFuerte"));
  }

  @Test
  public void TestContraSeguraSecuencaNumericaDescendente() {
    // Prueba que la función devuelve falso para una contraseña insegura con una secuencia numérica descendente
    assertFalse(passwordConsecutiveCharactersValidation.validatePassword(UsernameGlobal, "4321xyz"));
  }

  @Test
  public void TestSecuenciaNumericaPeroSegura() {
    // Prueba que la función devuelve verdadero para una contraseña que contiene una secuencia numérica pero es segura
    assertTrue(passwordConsecutiveCharactersValidation.validatePassword(UsernameGlobal, "p@$$w0rd123"));
  }

}