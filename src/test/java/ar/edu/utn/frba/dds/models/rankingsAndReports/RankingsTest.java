package ar.edu.utn.frba.dds.models.rankingsAndReports;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.PublicTransportTpye;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityType;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityContainerType;
import ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType;
import ar.edu.utn.frba.dds.models.rankings_and_reports.rankings.EntitiesAverageCloseTime;
import ar.edu.utn.frba.dds.models.rankings_and_reports.rankings.EntitiesReportedIncidents;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.models.services.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class RankingsTest {

    List<Entity> entities;

    private int averageIncidentClosingTimePerWeek(List<Incident> incidents) {

        int summationTimeIncidentsThisWeek = 0;

        for(int i=0; i < incidents.size() ;i++){
            summationTimeIncidentsThisWeek += incidents.get(i).getDaysDifferenceOpenClose();
        }
        int averageOfIncidentsPerWeek = summationTimeIncidentsThisWeek / incidents.size();

        return averageOfIncidentsPerWeek;
    }

    @BeforeEach
    public void init(){


        List<Entity> entities = new ArrayList<>();
        EntityContainerType entityContainer = new EntityContainerType(EntityType.PUBLIC_TRANSPORT, PublicTransportTpye.RAILWAY);

        //iniciallizo Tren Sarmiento
        Entity trenSarmiento = mock(Entity.class);
        List<Incident> incidentsSarmiento = new ArrayList<>();

        ProvisionOfService liftServiceSarmiento = new ProvisionOfService(new Service("Ascensor"),new Establishment("Estacion Verde",EstablishmentType.STATION));
        incidentsSarmiento.add(new Incident(liftServiceSarmiento,new CommunityMember(),""));

        entities.add(trenSarmiento);

        // inicializo Tren Roca
        Entity trenRoca = mock(Entity.class);
        List<Incident> incidentsRoca = new ArrayList<>();

        Establishment establishmentRoca = new Establishment("Estacion Roja",EstablishmentType.STATION);
        ProvisionOfService liftServiceRoca = new ProvisionOfService(new Service("Ascensor"),establishmentRoca);
        ProvisionOfService mechanicStairsServiceRoca = new ProvisionOfService( new Service("Escalera mecanica"),establishmentRoca);

        incidentsRoca.add(new Incident(liftServiceRoca,new CommunityMember(),""));
        incidentsRoca.add(new Incident(mechanicStairsServiceRoca,new CommunityMember(),""));


        entities.add(trenRoca);

        // inicializao Tren Mitre

        Entity trenMitre = mock(Entity.class);
        List<Incident> incidentsMitre = new ArrayList<>();

        Establishment establishmentMitre = new Establishment("Estacion Azul",EstablishmentType.STATION);
        ProvisionOfService liftServiceMitre  = new ProvisionOfService(new Service("Ascensor"),establishmentMitre);
        ProvisionOfService mechanicStairsServiceMitre = new ProvisionOfService( new Service("Escalera mecanica"),establishmentMitre);
        ProvisionOfService rampServiceMitre = new ProvisionOfService( new Service("Rampa"),establishmentMitre);

        incidentsMitre.add(new Incident(liftServiceMitre,new CommunityMember(),""));
        incidentsMitre.add(new Incident(mechanicStairsServiceMitre,new CommunityMember(),""));
        incidentsMitre.add(new Incident(rampServiceMitre,new CommunityMember(),""));

        entities.add(trenMitre);

        when(trenRoca.incidentsFromThisWeek()).thenReturn(incidentsRoca);
        when(trenMitre.incidentsFromThisWeek()).thenReturn(incidentsMitre);
        when(trenSarmiento.incidentsFromThisWeek()).thenReturn(incidentsSarmiento);

        LocalDateTime closingTime = LocalDateTime.now().plusDays(2);
        entities.forEach(entity -> {
            List<Incident> currentIncidents=entity.incidentsFromThisWeek();
            currentIncidents.forEach(incident -> {
            int daysAmountToSumarize = currentIncidents.indexOf(incident) + 1 * (entities.indexOf(entity) + 1);
            incident.setOpen(false);
            incident.setClosingDate(closingTime.plusDays(daysAmountToSumarize));
        });});

        when(trenRoca.averageIncidentClosingTimePerWeek()).thenReturn(this.averageIncidentClosingTimePerWeek(incidentsRoca));
        when(trenRoca.getName()).thenReturn("Tren Roca");
        when(trenMitre.averageIncidentClosingTimePerWeek()).thenReturn(this.averageIncidentClosingTimePerWeek(incidentsMitre));
        when(trenMitre.getName()).thenReturn("Tren Mitre");
        when(trenSarmiento.averageIncidentClosingTimePerWeek()).thenReturn(this.averageIncidentClosingTimePerWeek(incidentsSarmiento));
        when(trenSarmiento.getName()).thenReturn("Tren Sarmiento");

        this.entities = entities;


    }



    @Test
    public void entitiesAverageCloseTimeRanking(){
        EntitiesAverageCloseTime ranking = new EntitiesAverageCloseTime();
        ranking.generate(this.entities);
        Assertions.assertEquals(ranking.getDataFromRanking().get(0).getName(),"Tren Sarmiento");

    }

    @Test
    public void entitiesReportedIncidentsRanking(){
        EntitiesReportedIncidents ranking = new EntitiesReportedIncidents();
        ranking.generate(this.entities);
        Assertions.assertEquals(ranking.getDataFromRanking().get(2).getName(),"Tren Sarmiento");

    }

}
