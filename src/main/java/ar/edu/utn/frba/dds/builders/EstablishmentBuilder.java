package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.entities_establishment.EstablishmentType;
import ar.edu.utn.frba.dds.models.locations.*;

public class EstablishmentBuilder {


    Establishment establishment = new Establishment();

    public EstablishmentBuilder() {

    }


    private EstablishmentType typeEstablishment;





    public EstablishmentBuilder withName(String name){
        this.establishment.setName((name));
        return this;
    }

    public EstablishmentBuilder withEstablishmentType(EstablishmentType establishmentType){
        this.establishment.setTypeEstablishment(establishmentType);
        return this;
    }

    public EstablishmentBuilder withProvince(Province province) {
        this.establishment.setProvince(province);
        return this;
    }

    public EstablishmentBuilder withDepartment(Department department) {
        this.establishment.setDepartment(department);
        return this;
    }

    public EstablishmentBuilder withMunicipality(Municipality municipality) {
        this.establishment.setMunicipality(municipality);
        return this;
    }

    public EstablishmentBuilder withLocality(Locality locality) {
        this.establishment.setLocality(locality);
        return this;
    }

    public EstablishmentBuilder withDescription(String description) {
        this.establishment.setDescription(description);
        return this;
    }


    public Establishment build(){
        // validaciones si
        return this.establishment;
    }
}
