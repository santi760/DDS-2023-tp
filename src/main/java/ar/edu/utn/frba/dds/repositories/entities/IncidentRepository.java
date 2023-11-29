package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import java.time.LocalDateTime;
import java.util.List;

public class IncidentRepository extends GenericRepositoryJPA<Incident> {

    private static IncidentRepository instance;

    public static synchronized IncidentRepository getInstance() {
        if (instance == null) {
            instance = new IncidentRepository();
        }
        instance.setEntities();
        return instance;
    }

    public List<Incident> getOpenIncidents(){

        return (List<Incident>) entityManager().createQuery(" from " + Incident.class.getName() + " where open = :open")
            .setParameter("open", true)
            .getResultList();

    }

    public List<Incident> getClosedIncidents(){

        return (List<Incident>) entityManager().createQuery(" from " + Incident.class.getName() + " where open = :open")
            .setParameter("open", false)
            .getResultList();

    }

    public List<Incident> getClosedIncidentsByCommunityId(Long communityId) {
        return (List<Incident>) entityManager().createQuery(" from " + Incident.class.getName() + " where open = :open and community_id = :communityId")
                .setParameter("open", false)
                .setParameter("communityId", communityId)
                .getResultList();
    }

    public List<Incident> getOpenIncidentsByCommunityId(Long communityId) {
        return (List<Incident>) entityManager().createQuery(
                        " from " + Incident.class.getName() + " where open = :open and community_id = :communityId")
                .setParameter("open", true)
                .setParameter("communityId", communityId)
                .getResultList();
    }


    public List<Incident> getIncidentsAtDate(LocalDateTime aDate){

        return (List<Incident>) entityManager().createQuery(" from " + Incident.class.getName() + " where openingDate = :openingDate")
            .setParameter("openingDate", aDate)
            .getResultList();

    }

    public List<Incident> getIncidentsFromEstablishment(Establishment anEstablishment){

        return (List<Incident>) entityManager().createQuery(" from " + Incident.class.getName() + " where associatedProvisionOfService_id in " +
                                     "( select id from " + ProvisionOfService.class.getName() + " where establishment_id = :establishment_id  ) ")
                                        .setParameter("establishment_id", anEstablishment)
            .getResultList();

    }

    public List<Incident> getIncidentsFromEntity(Entity entity) {
        return entityManager().createQuery(" from " + Incident.class.getName() + " where associatedProvisionOfService_id in " +
                " (select id from " + ProvisionOfService.class.getName() + " where establishment_id in " +
                " (select id from " + Establishment.class.getName() + " where entity_id = :entity_id))")
            .setParameter("entity_id", entity)
            .getResultList();
    }



}
