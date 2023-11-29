package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.exceptions.PropertiesFileException;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderEntityImporter {

  private List<ProviderEntity> providerEntitiesList = new ArrayList<>();
  private EstablishmentsImporter reader = new EstablishmentsImporter();

  public void importEntitiesFrom(){
    /*List<String[]> providerEntities = reader.readCsv(this.getProviderEntitiesFilePath());

    for(String[] line : providerEntities){

      providerEntitiesList.add(new ProviderEntity(line[0]));

    }
*/
  }

  private String getProviderEntitiesFilePath() {

    InputStream inputStream = null;
    String propertyFilePath = null;

    try {
      Properties prop = new Properties();

      propertyFilePath = "prestadores.properties";
      inputStream = getClass().getClassLoader().getResourceAsStream(propertyFilePath);

      prop.load(inputStream);
      String worstPasswordsFilePath = prop.getProperty("providerentities");
      inputStream.close();

      return worstPasswordsFilePath;
    } catch (IOException e) {
      throw new PropertiesFileException("Property file '" + propertyFilePath + "' not found in the resources folder");
    }
  }

}




