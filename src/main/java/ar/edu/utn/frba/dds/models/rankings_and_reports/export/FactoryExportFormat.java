package ar.edu.utn.frba.dds.models.rankings_and_reports.export;

import ar.edu.utn.frba.dds.models.rankings_and_reports.export.pdf.AdapterApachePDFBox;
import ar.edu.utn.frba.dds.models.rankings_and_reports.export.pdf.ExportToPDF;

public class FactoryExportFormat {

    public static ExportStrategy create(String format, String filename){
        ExportStrategy exportStrategy;
        switch (format) {
            case "PDF": exportStrategy = createExportToPDF(filename); break;
            default: throw new NoExisteFormatoException();
        }

        return exportStrategy;
    }

    private static ExportToPDF createExportToPDF(String fileName) {
        return new ExportToPDF(new AdapterApachePDFBox(fileName));
    }


}
