package ar.edu.utn.frba.dds.models.validators.validation;

import ar.edu.utn.frba.dds.models.validators.FileReader;
import ar.edu.utn.frba.dds.models.validators.ValidationType;
import java.util.List;
import javax.persistence.Transient;

public class Password10000Validation extends ValidationType {

  // instanciar un objeto antes con el path al archivo.

  @Transient
  FileReader fileReader;

  @Transient
  private static List<String> contrasenias;

  @Override
  public String getErrorMessage() {
    return "La contraseña es demasiado común.";
  }

  @Override
  public boolean validatePassword(String userName, String password) {
    if (contrasenias == null) {
      contrasenias = this.fileReader.getLines();
    }

    return !contrasenias.contains(password);
  }

  public Password10000Validation(FileReader fileReader) {
    this.fileReader = fileReader;
  }

  public Password10000Validation(){}

}

