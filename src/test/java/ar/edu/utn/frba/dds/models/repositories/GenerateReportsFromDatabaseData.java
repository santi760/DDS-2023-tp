package ar.edu.utn.frba.dds.models.repositories;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Report;
import ar.edu.utn.frba.dds.models.rankings_and_reports.export.Exporter;
import ar.edu.utn.frba.dds.models.rankings_and_reports.export.config.Config;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import ar.edu.utn.frba.dds.repositories.users.ProviderEntityRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GenerateReportsFromDatabaseData {

  @Test
      public  void reporteTest(){

    ProviderEntity providerEntity =  ProviderEntityRepository.getInstance().read(2L);
    List<Entity> entities = providerEntity.getEntities().stream().toList();
    Report reporte = new Report("Reporte Semanal");
    reporte.generateReport(entities);
    Exporter exporter = new Exporter();
  //exporter.export(reporte, "PDF", (providerEntity.getName() + LocalDate.now()));
    Assert.assertEquals(Config.EXPORTATION_PATH + (providerEntity.getName() + LocalDate.now() + ".pdf"),
        exporter.export(reporte,"PDF", (providerEntity.getName() + LocalDate.now() + ".pdf")));

  }
}
