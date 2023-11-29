package ar.edu.utn.frba.dds.models.rankings_and_reports.rankings;

import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.rankings_and_reports.Ranking;
import lombok.Setter;


import javax.persistence.DiscriminatorValue;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter

public class EntitiesReportedIncidents extends Ranking<Entity> {


  @Override
  public List<Entity> getDataFromRanking() {
    return this.sortedEntities;
  }

  //private void setSortedEntities(En)

  public EntitiesReportedIncidents(){
    this.name = "Ranking cantidad de incidentes reportados";
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

    List<Incident> filteredIncidentsFromAnEntity = this.filterIncidentsFromEntity(anEntity);
    List<Incident> filteredIncidentsFromAnotherEntity = this.filterIncidentsFromEntity(anotherEntity);

    if(filteredIncidentsFromAnEntity.size() < filteredIncidentsFromAnotherEntity.size()){
      return 1;
    } else if (filteredIncidentsFromAnEntity.size() > filteredIncidentsFromAnotherEntity.size()) {
      return -1;
    }else{
      return 0;
    }

  }

  private List<Incident> filterIncidentsFromEntity(Entity entity){

    List<Incident> filteredIncidents = new ArrayList<>();
    List<Incident> incidentsOfEntity = entity.incidentsFromThisWeek();

    for (int i=0 ; incidentsOfEntity.size() > i ; i++){

      if(incidentsOfEntity.get(i).equalsAnotherIncidentFromSameProvsionOfService(filteredIncidents)){
        //DO NOTHING
      } else {
        filteredIncidents.add(incidentsOfEntity.get(i));
      }
    }

    return filteredIncidents;
  }




}
