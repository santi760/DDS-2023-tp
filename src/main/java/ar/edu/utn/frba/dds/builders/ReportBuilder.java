package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.rankings_and_reports.Ranking;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Report;
import java.time.LocalDate;


public class ReportBuilder {

    Report report = new Report();

    public ReportBuilder() {

    }

    public ReportBuilder withName(String name) {
        this.report.setName(name);
        return this;
    }

    public ReportBuilder withRankingsToInclude(Ranking... rankings) {
        this.report.addRankings(rankings);
        return this;
    }

    public ReportBuilder withProcessingDate(LocalDate processingDate) {
        this.report.setProcessingDate(processingDate);
        return this;
    }

    public Report build() {
        return this.report;
    }
}
