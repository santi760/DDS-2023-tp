package ar.edu.utn.frba.dds.models.proximity_finder.searchers;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.proximity_finder.ProximityFinder;

public class DepartmentSearcher extends ProximityFinder {

    @Override
    public Boolean memberIsCloser(CommunityMember member, Incident incident) {
        return member.getPerson().getDepartment().getName() == incident.getDepartment().getName() ;
    }
}
