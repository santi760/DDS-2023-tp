package ar.edu.utn.frba.dds.models.rankings_and_reports.export;

public class Exporter {

    public String export(Exportable exportable, String exportFormat, String fileName){
        ExportStrategy strategy = FactoryExportFormat.create(exportFormat, fileName);
        return strategy.export(exportable);
    }

}