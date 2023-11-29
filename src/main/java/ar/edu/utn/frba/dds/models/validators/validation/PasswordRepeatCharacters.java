package ar.edu.utn.frba.dds.models.validators.validation;

import ar.edu.utn.frba.dds.models.validators.ValidationType;

public class PasswordRepeatCharacters extends ValidationType {

  @Override
  public String getErrorMessage() {
    return "Error. La contraseña no puede repetir 3 veces un caracter";
  }

  @Override
  public boolean validatePassword(String userName, String password) {
    // Recorremos los caracteres de la contraseña, verificando si hay tres caracteres consecutivos idénticos.
    for (int i = 0; i < password.length() - 2; i++) {
      if (password.charAt(i) == password.charAt(i+1) && password.charAt(i+1) == password.charAt(i+2)) {
        // Si encontramos tres caracteres consecutivos idénticos, la contraseña no es válida, por lo que devolvemos falso.
        return false;
      }
    }

    // Si no encontramos tres caracteres consecutivos idénticos, la contraseña es válida, por lo que devolvemos verdadero.
    return true;
  }

  public PasswordRepeatCharacters(){}
}
