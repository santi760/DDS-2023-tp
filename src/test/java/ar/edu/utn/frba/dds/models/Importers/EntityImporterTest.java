package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.Test;

public class EntityImporterTest {
  @Test
  public void successfulEntityImporter() throws IOException {

    EntityImporter importer = new EntityImporter();

    Properties prop = new Properties();

    String filePath = prop.getProperty("entities");
// Crear un FileInputStream para leer el archivo
    FileInputStream fileInputStream = new FileInputStream("src/main/resources/organismos_control.csv");

    // Obtener un InputStream desde el FileInputStream
    InputStream inputStream = (InputStream) fileInputStream;

    List<Entity> entities = importer.importEntities(inputStream);

    Assertions.assertEquals("JUMBO",entities.get(0).getName());
    //Assertions.assertEquals("Galicia", importer.getProviderEntitiesList().get(1).getName());
    //Assertions.assertEquals(importer.getProviderEntitiesList().size(),3);
  }

}


