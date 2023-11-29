package ar.edu.utn.frba.dds.models.validators;

import ar.edu.utn.frba.dds.exceptions.PropertiesFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class FileReader {

  public List<String> getLines() {
    String path = getWorstPasswordFilePathProperty();
    File file = new File(path);
    validateFile(file); // validamos antes de correrlo!
    return getLinesOf(file);
  }


  private void validateFile(File f) {
    if (!f.exists()) {
      throw new IllegalArgumentException("Error. Archivo inexistente.");
    }
    if (!f.canRead()) {
      throw new IllegalArgumentException("Error. No se puede leer el archivo.");
    }
    if (f.isDirectory()) {
      throw new IllegalArgumentException("Error. El path corresponde a un directorio.");
    }
  }

  private List<String> getLinesOf(File archivo) {
    List<String> newLines = new ArrayList<>();
    try {
      Scanner fileScanner = new Scanner(archivo);
      while (fileScanner.hasNextLine()) {
        newLines.add(fileScanner.nextLine());
      }
      fileScanner.close();
      return newLines;
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Error. El archivo no existe.");
    }
  }

  private String getWorstPasswordFilePathProperty() {

    InputStream inputStream = null;
    String propertyFilePath = null;

    try {
      Properties prop = new Properties();

      propertyFilePath = "config.properties";
      inputStream = getClass().getClassLoader().getResourceAsStream(propertyFilePath);

      prop.load(inputStream);
      String worstPasswordsFilePath = prop.getProperty("badpasswords");
      inputStream.close();

      return worstPasswordsFilePath;
    } catch (IOException e) {
      throw new PropertiesFileException("Property file '" + propertyFilePath + "' not found in the resources folder");
    }
  }

}// end class
