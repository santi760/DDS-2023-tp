package ar.edu.utn.frba.dds.models.rankings_and_reports;


import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


//TODO  el ranking inicialmente lo piden de manera semanal .
// nosotros deberiamos hacer que el ranking tenga un tiempo de generacion customizable. semana,mes,año,etc
// es decir pasamos una fecha de inicio y una fecha de final

@Setter
@Getter

public abstract class Ranking <T>{

   protected String name;


   protected List<T> sortedEntities;

   public List<T> getDataFromRanking() {
      return null;
   }

   public void generate(List<T> entities) {

   }

   public String getName() {
      return null;
   }


}

