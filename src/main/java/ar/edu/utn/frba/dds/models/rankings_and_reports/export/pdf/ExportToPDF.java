package ar.edu.utn.frba.dds.models.rankings_and_reports.export.pdf;

import ar.edu.utn.frba.dds.models.rankings_and_reports.export.ExportStrategy;
import ar.edu.utn.frba.dds.models.rankings_and_reports.export.Exportable;

public class ExportToPDF implements ExportStrategy {

    private AdapterPDFExport adapter;

    public ExportToPDF(AdapterPDFExport adapter){
        this.adapter = adapter;
    }

    public String export(Exportable exportable) {
        return this.adapter.export(exportable);
    }

}
