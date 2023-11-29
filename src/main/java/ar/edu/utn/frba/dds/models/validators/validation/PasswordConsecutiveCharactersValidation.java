package ar.edu.utn.frba.dds.models.validators.validation;

import ar.edu.utn.frba.dds.models.validators.ValidationType;

public class PasswordConsecutiveCharactersValidation extends ValidationType {
  @Override
  public String getErrorMessage() {
    return "Error. La contraseña no puede tener 4 caracteres consecutivos.";
  }

  @Override
  public boolean validatePassword(String userName, String password) {
    char[] passwordInArray = password.toCharArray();
    for (int i = 0; i < passwordInArray.length - 3; i++) {
      if ((passwordInArray[i] == passwordInArray[i + 1] - 1
          && passwordInArray[i] == passwordInArray[i + 2] - 2
          && passwordInArray[i] == passwordInArray[i + 3] - 3)
          || (passwordInArray[i] == passwordInArray[i + 1] + 1
          && passwordInArray[i] == passwordInArray[i + 2] + 2
          && passwordInArray[i] == passwordInArray[i + 3] + 3)) {
        return false;
      }
    }
    return true;
  }

  public PasswordConsecutiveCharactersValidation(){}
}

/*

* */