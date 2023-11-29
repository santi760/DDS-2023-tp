package ar.edu.utn.frba.dds.models.Importers;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstablishmentImporterTest {
  @Test
  public void successfulEstablishmentsImporter() throws IOException {

    EstablishmentsImporter importer = new EstablishmentsImporter();
    Entity entity = new Entity();
    entity.setName("Subte B");


    Properties prop = new Properties();

    String filePath = prop.getProperty("establishments");
// Crear un FileInputStream para leer el archivo
    FileInputStream fileInputStream = new FileInputStream("src/main/resources/entidades_prestadoras.csv");

    // Obtener un InputStream desde el FileInputStream
    InputStream inputStream = (InputStream) fileInputStream;

    List<Establishment> establishments = importer.importEstablishments(inputStream,entity);

    Assertions.assertEquals("Carlos Pellegrini",establishments.get(0).getName());
    //Assertions.assertEquals("Galicia", importer.getProviderEntitiesList().get(1).getName());
    //Assertions.assertEquals(importer.getProviderEntitiesList().size(),3);
  }

}


