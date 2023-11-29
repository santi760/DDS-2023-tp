package ar.edu.utn.frba.dds.models.validators.validation;

import ar.edu.utn.frba.dds.models.validators.ValidationType;
import java.util.Objects;

public class PasswordUsernameValidation extends ValidationType {
  @Override
  public String getErrorMessage() {
    return "La contraseña no puede ser igual que el nombre de usuario";
  }

  @Override
  public boolean validatePassword(String userName, String password) {
    return !Objects.equals(password, userName);
  }

  public PasswordUsernameValidation(){}
}
