package ar.edu.utn.frba.dds.models.rankings_and_reports.rankings;

import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Ranking;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import java.util.ArrayList;
import java.util.List;


public class IncidentsImpactDegree extends Ranking<Incident> {

  public IncidentsImpactDegree(){
    this.name = "Ranking promedio de tiempo de cierre de incidentes";
  }

  @Override
  public List<Incident> getDataFromRanking() {
    return this.sortedEntities;
  }

  @Override
  public void generate(List<Incident> entities) {
    //TODO
  }

  @Override
    public String getName() {
        return this.name;
    }


}
