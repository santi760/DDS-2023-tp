package ar.edu.utn.frba.dds.models.proximity_finder;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.Incident;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

public class CustomProximityFinder {

  List<ProximityFinder> proximityFinders = new ArrayList<ProximityFinder>();


  public Boolean memberIsCloser(CommunityMember member, Incident incident) {

    int numberOfCorrectAppliedFilters = 0;
    for ( ProximityFinder proximityFinder : proximityFinders ){
       if(proximityFinder.memberIsCloser(member,incident)){
         numberOfCorrectAppliedFilters++;
       }
    }

    return numberOfCorrectAppliedFilters == proximityFinders.size();
  }


  public void addProximityFinder(ProximityFinder proximityFinder){
    proximityFinders.add(proximityFinder);
  }
}
