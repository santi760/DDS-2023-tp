package ar.edu.utn.frba.dds.exceptions;

public class PropertiesFileException extends RuntimeException {

  public PropertiesFileException(String message) {
    super("Error opening the properties file: " + message);
  }
}
