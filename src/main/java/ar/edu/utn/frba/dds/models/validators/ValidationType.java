package ar.edu.utn.frba.dds.models.validators;

public abstract class ValidationType {

  public String getErrorMessage() {
    return null;
  }

  public boolean validatePassword(String userName, String password) {
    return false;
  }
}
