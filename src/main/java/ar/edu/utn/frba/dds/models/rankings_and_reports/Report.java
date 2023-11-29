package ar.edu.utn.frba.dds.models.rankings_and_reports;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.rankings_and_reports.rankings.EntitiesAverageCloseTime;
import ar.edu.utn.frba.dds.models.rankings_and_reports.rankings.EntitiesReportedIncidents;
import ar.edu.utn.frba.dds.models.users.ProviderEntity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import java.util.*;

import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import lombok.Getter;
import lombok.Setter;
import ar.edu.utn.frba.dds.models.rankings_and_reports.export.Exportable;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.joda.time.DateTime;

@Getter
@Setter
public class Report implements Exportable{

    public String name;
    private List<Ranking> rankingsToInclude;
    public ProviderEntity providerEntity;
    private LocalDate processingDate; // para identificar de que semana se hizo el reporte
    private Map<String, List<String>> reportData;


    //* ------------------------- CONSTRUCTORS ------------------------- *//

    public Report(String name) {
        this.name = name;
        this.reportData = new HashMap<String, List<String>>();
        this.rankingsToInclude = new ArrayList<Ranking>();
        this.addRankings(new EntitiesReportedIncidents(), new EntitiesAverageCloseTime());
    }

    public Report(String name,ProviderEntity providerEntity) {
        this.name = name;
        this.reportData = new HashMap<String, List<String>>();
        this.rankingsToInclude = new ArrayList<Ranking>();
        this.addRankings(new EntitiesReportedIncidents(), new EntitiesAverageCloseTime());
        this.providerEntity = providerEntity;
    }


    public Report() {

        this.reportData = new HashMap<String, List<String>>();
        this.rankingsToInclude = new ArrayList<Ranking>();
            this.addRankings(new EntitiesReportedIncidents(), new EntitiesAverageCloseTime());
    }

    //* ------------------------- METHODS ------------------------- *//

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public LocalDate getProcessingDate(){
        return this.processingDate;
    }

    @Override // esto es para el modulo exportador
    public Map<String, List<String>> reportData() {
        return this.reportData;
    }

    public void setProcessingDate(LocalDate processingDate) {
        this.processingDate = processingDate;
    }

    public void generateReport(List<Entity> entities) {

        this.setProcessingDate(LocalDate.now());
        //ejecuto los rankings
        this.generateRankingsAndSaveData(entities);


    }

    private void generateRankingsAndSaveData(List<Entity> entities){

        for(int i = 0; this.rankingsToInclude.size() > i;i++){
            Ranking rankingToExecute = this.rankingsToInclude.get(i);
            rankingToExecute.generate(entities);
            List<Entity> orderedEntities = rankingToExecute.getDataFromRanking();
            reportData.put(rankingToExecute.getName(),this.convert(orderedEntities));
        }

    }

    public List<String> convert(List<Entity> entities){

        List<String> entitesToString = new ArrayList<>();
        entities.forEach(entity -> entitesToString.add(entity.getName()));
        return entitesToString;
    }
    public void addRanking(Ranking ranking){
        this.rankingsToInclude.add(ranking);
    }

    public void addRankings(Ranking... rankingsToInclude) {
        this.rankingsToInclude.addAll(Arrays.asList(rankingsToInclude));
    }

}
