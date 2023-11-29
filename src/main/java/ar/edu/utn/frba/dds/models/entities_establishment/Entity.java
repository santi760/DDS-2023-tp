package ar.edu.utn.frba.dds.models.entities_establishment;

import ar.edu.utn.frba.dds.models.community.Incident;
import ar.edu.utn.frba.dds.models.entities_establishment.entity_type.EntityContainerType;
import ar.edu.utn.frba.dds.repositories.entities.IncidentRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString

@javax.persistence.Entity
@Table(name = "entity")
public class Entity {

    // RECORDAR QUE ENTITY PUEDE SER ORGANIZACION O TRANPORTE PUBLICO

    @Id
    @GeneratedValue
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Embedded
    private EntityContainerType typeEntity;   // dependiendo si es Organizacion o T.Publico va a ser sucursal o estacion

    //TODO NO ESTOY SEGURO
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    private List<Establishment> establishments = new ArrayList<>();

    // este queda en transient, porque se calcula cada vez que se necesita. no es necesario persistilo
    @Transient
    private Integer AverageOfIncidentsPerWeek; //? lo usamos para  no calcular todoo el tiempo algo de los rankings

    //** -------------------------------------- CONSTRUCTORS -------------------------------------- **//

    public Entity() {

    }
    public Entity(String name, EntityContainerType typeEntity) {
        this.name = name;
        this.typeEntity = typeEntity;
    }
    public Entity(String name, List<Establishment> establishments, EntityContainerType typeEntity) {
        this.name = name;
        this.establishments = establishments;
        this.typeEntity = typeEntity;
    }

    public Entity(String name) {
        this.name = name;
    }


    //** -------------------------------------- METHODS -------------------------------------- **//

    public void addEstablishment(Establishment... establishments) {
        this.establishments.addAll(Arrays.asList(establishments));
    }

    public void removeEstablishment(Establishment establishment){
        this.establishments.remove(establishment);
    }


    public Establishment getFirstEstablishment(){
        return establishments.get(0);
    }


    public Establishment getLastEstablishment(){
        return establishments.get(establishments.size() - 1);
    }


    public List<Incident> getIncidentsOfEntity(){
        //** TODO  IncidentRepository.getIncidentsOfEntity(this)
        return new IncidentRepository().getInstance().getIncidentsFromEntity (this);
    }


    public List<Incident> incidentsFromThisWeek(){

        //TODO cuando persistamos este mesnaje hay que cambiarlo
        return this.getIncidentsOfEntity().stream().filter(incident -> incident.isFromThisWeek() &&
            !(incident.isWithin24Hours() && incident.getOpen())).toList();
    }

    public int averageIncidentClosingTimePerWeek() {

        if (this.AverageOfIncidentsPerWeek != null) {
            return this.AverageOfIncidentsPerWeek;
        }

        int summationTimeIncidentsThisWeek = 0;

        List<Incident> incidentsFromThisWeek = this.incidentsFromThisWeek();

        for(int i=0; i < incidentsFromThisWeek.size() ;i++){
            summationTimeIncidentsThisWeek += incidentsFromThisWeek.get(i).getDaysDifferenceOpenClose();
        }

        if(incidentsFromThisWeek.size() == 0){
            this.AverageOfIncidentsPerWeek = 0;
        }else {
            this.AverageOfIncidentsPerWeek = summationTimeIncidentsThisWeek / incidentsFromThisWeek.size();
        }

        return AverageOfIncidentsPerWeek;

    }



}

