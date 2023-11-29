package ar.edu.utn.frba.dds.models.rankings_and_reports.export.pdf;

import ar.edu.utn.frba.dds.models.rankings_and_reports.export.Exportable;

public interface AdapterPDFExport {

    public String export(Exportable exportable);

}
