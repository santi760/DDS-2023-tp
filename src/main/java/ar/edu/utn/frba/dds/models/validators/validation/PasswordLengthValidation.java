package ar.edu.utn.frba.dds.models.validators.validation;

import ar.edu.utn.frba.dds.models.validators.ValidationType;

public class PasswordLengthValidation extends ValidationType {

  // deberia hacer para que esto sea un singleton ....

  @Override
  public String getErrorMessage() {
    return "La contraseña debe tener al menos 8 caracteres.";
  }

  @Override
  public boolean validatePassword(String userName , String password) {
    return password.length() >= 8;
  }

  public PasswordLengthValidation(){}
}


/* por si estamos falopa y queremos hacerlo con facilidad de cambio de variables :P
*
*   private static final int LONGITUD_MINIMA = 8;

  @Override
  public String validar(String contrasena, String usuario) {
    if (contrasena.length() < LONGITUD_MINIMA) {
      return "La contraseña debe tener al menos " + LONGITUD_MINIMA + " caracteres.";
    }
    return null;
  }
}
*
* */