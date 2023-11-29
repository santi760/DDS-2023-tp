package ar.edu.utn.frba.dds.models.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.repositories.entities.PersonRepository;
import org.junit.jupiter.api.Test;

public class PersonRepositoryTest {

  @Test
  public void PersonTest(){
    Long n = 1L;
    Person person = PersonRepository.getInstance().read(n);

    assertEquals("Carlos", person.getName());
    assertEquals("Rodriguez", person.getSurname());
    assertEquals(1, person.getDepartment().getId());

    n = 3L;
    Person anotherPerson = PersonRepository.getInstance().read(n);

    assertEquals("pabloGiralt@gmail.com", anotherPerson.getEmail());
    assertEquals("1134826296",anotherPerson.getPhoneNumber());


  }

}
