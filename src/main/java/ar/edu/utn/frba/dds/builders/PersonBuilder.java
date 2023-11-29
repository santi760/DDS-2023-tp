package ar.edu.utn.frba.dds.builders;

import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.models.locations.Locality;
import ar.edu.utn.frba.dds.models.locations.Municipality;
import ar.edu.utn.frba.dds.models.locations.Province;
import ar.edu.utn.frba.dds.models.users.User;

public class PersonBuilder {

  private Person person = new Person();


  public PersonBuilder() {

  }


  public PersonBuilder withName(String name) {
    this.person.setName(name);
    return this;
  }

  public PersonBuilder withSurname(String surname) {
    this.person.setSurname(surname);
    return this;
  }

  public PersonBuilder withEmail(String email) {
    this.person.setEmail(email);
    return this;
  }

  public PersonBuilder withPhoneNumber(String phoneNumber) {
    this.person.setPhoneNumber(phoneNumber);
    return this;
  }

  public PersonBuilder withProvince(Province province) {
    this.person.setProvince(province);
    return this;
  }

  public PersonBuilder withDepartment(Department department) {
    this.person.setDepartment(department);
    return this;
  }

  public PersonBuilder withMunicipality(Municipality municipality) {
    this.person.setMunicipality(municipality);
    return this;
  }

  public PersonBuilder withLocality(Locality locality) {
    this.person.setLocality(locality);
    return this;
  }

  public PersonBuilder withUser(User user) {
    this.person.setUser(user);
    return this;
  }

  public Person build() {
    // validaciones si necesitamos
    return this.person;
  }


}
