package ar.edu.utn.frba.dds.models.rankings_and_reports.export;

import ar.edu.utn.frba.dds.models.rankings_and_reports.Ranking;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;


public interface Exportable {

    public String name = null;
    public LocalDate processingDate = null;
    public Map<String, List<String>> reportData();

    public String getName();

    public LocalDate getProcessingDate();
}
