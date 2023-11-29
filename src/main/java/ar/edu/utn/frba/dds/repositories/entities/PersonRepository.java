package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class PersonRepository extends GenericRepositoryJPA<Person> {

    private static PersonRepository instance;

    public static synchronized PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Person readByUserId(Long id){

        Query query =  entityManager().createQuery(" from " + Person.class.getName() + " where user_id = :parametro")
                .setParameter("parametro", id);


        List<Person> resultados = query.getResultList();

        return resultados.get(0);
    }


    public CommunityMember getCommunityMemberOfPersonByCommunity(Person person, Community community) {

        Query query = entityManager().createQuery(" from " + CommunityMember.class.getName() + " where person_id = :parametro" + " and community_id = :parametro2")
                .setParameter("parametro", person.getId())
                .setParameter("parametro2", community.getId());

        List<CommunityMember> communityMembers = query.getResultList();

        return communityMembers.get(0);


    }
}
