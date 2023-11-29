package ar.edu.utn.frba.dds.models.rankings_and_reports.export.pdf;

import ar.edu.utn.frba.dds.models.rankings_and_reports.export.Exportable;
import ar.edu.utn.frba.dds.models.rankings_and_reports.export.config.Config;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class AdapterApachePDFBox implements AdapterPDFExport{

    private String fileName;

    public AdapterApachePDFBox(String fileName) {this.fileName = fileName;}


    public String export(Exportable exportable) {
        PDDocument doc = new PDDocument();
        PDPage myPage = new PDPage();
        doc.addPage(myPage);
        try {
            PDPageContentStream cont = new PDPageContentStream(doc, myPage);
            cont.beginText();
            cont.setLeading(14.5f);
            cont.newLineAtOffset(25, 700);
            cont.setFont(PDType1Font.TIMES_ROMAN, 20);
            cont.showText(exportable.getName());
            cont.newLine();
            cont.setFont(PDType1Font.TIMES_ROMAN, 17);
            cont.showText(exportable.getProcessingDate().toString());
            cont.newLine();
            Set<String> keys = exportable.reportData().keySet();
            for(int i = 0; i < keys.size();i++){
                cont.newLine();
                cont.setFont(PDType1Font.TIMES_ROMAN,15);
                cont.showText(keys.stream().toList().get(i));
                List<String> results = exportable.reportData().get(keys.stream().toList().get(i));
                for (int j = 0; j < results.size(); j++){
                    cont.newLine();
                    cont.setFont(PDType1Font.TIMES_ROMAN,12);
                    cont.showText(results.get(j));
                }
            }

            cont.endText();
            cont.close();
            doc.save(completeFilePath());
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return completeFilePath();
    }

    private String completeFilePath(){
        return Config.EXPORTATION_PATH + this.fileName;
    }

    private void addData(PDPageContentStream page, Map<String, List<String>> data) throws IOException {
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {
            page.newLine();
            String datosDeLaFila = String.join(", ", entry.getValue());
            page.showText(datosDeLaFila);
        }
    }

}
