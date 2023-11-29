package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.exceptions.PropertiesFileException;
import ar.edu.utn.frba.dds.models.users.ControlOrganism;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControlOrganismImporter {


  private ArrayList<ControlOrganism> controlOrganismsList = new ArrayList<>();
  private EstablishmentsImporter reader = new EstablishmentsImporter();

  public void importOrganismsFrom(){
   /* ArrayList<String[]> controlOrganisms = reader.readCsv(this.getControlOrganismsFilePath());

    for(String[] line : controlOrganisms){
      controlOrganismsList.add(new ControlOrganism(line[0]));

    }*/

  }

  private String getControlOrganismsFilePath() {

    InputStream inputStream = null;
    String propertyFilePath = null;

    try {
      Properties prop = new Properties();

      propertyFilePath = "organismos.properties";
      inputStream = getClass().getClassLoader().getResourceAsStream(propertyFilePath);

      prop.load(inputStream);
      String worstPasswordsFilePath = prop.getProperty("controlorganisms");
      inputStream.close();

      return worstPasswordsFilePath;
    } catch (IOException e) {
      throw new PropertiesFileException("Property file '" + propertyFilePath + "' not found in the resources folder");
    }
  }

}



