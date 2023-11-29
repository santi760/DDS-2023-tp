package ar.edu.utn.frba.dds.models.rankings_and_reports.rankings;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Ranking;

import javax.persistence.DiscriminatorValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntitiesAverageCloseTime extends Ranking<Entity> {

  @Override
  public List<Entity> getDataFromRanking() {
    return this.sortedEntities;
  }


  public EntitiesAverageCloseTime(){
    this.name = "Ranking promedio de tiempo de cierre de incidentes";
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void generate(List<Entity> entities) {

    this.sortedEntities = new ArrayList<Entity>(entities);
    Collections.sort(this.sortedEntities,(anEntity, anotherEntity) -> this.compare(anEntity,anotherEntity));
   // this.sortedEntities = entities;

  }
  private int compare(Entity anEntity, Entity anotherEntity){

    if(anEntity.averageIncidentClosingTimePerWeek() > anotherEntity.averageIncidentClosingTimePerWeek()){
      return 1;
    } else if (anEntity.averageIncidentClosingTimePerWeek() < anotherEntity.averageIncidentClosingTimePerWeek()) {
      return -1;
    }else{
      return 0;
    }
  }


}
