package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class CommunityMemberRepository extends GenericRepositoryJPA<CommunityMember> {


    //pongo esto porque tengo que hacer la logica en otro lado, nose si verdaderamente se maneja asi los repos
    private static CommunityMemberRepository instance;

    public static synchronized CommunityMemberRepository getInstance() {
        if (instance == null) {
            instance = new CommunityMemberRepository();
        }
        instance.setEntities();
        return instance;
    }


    //TODO: poner un nombre mas declarativo a este metodo
    public CommunityMember readByUserId(Long userId) {
        Query query = entityManager().createQuery(" from " + CommunityMember.class.getName() + " where person_id = :parametro").setParameter("parametro", userId);
        List<CommunityMember> resultados = query.getResultList();
        return resultados.get(0);
    }

    public List<CommunityMember> readCommunityMembers(Long person) {
        String text = "SELECT e FROM communitymember e where e.user_id = :parametro";

        Query query = entityManager().createQuery(" from " + CommunityMember.class.getName() + " where person_id = :parametro")
                .setParameter("parametro", person);

        return query.getResultList();
    }

    public CommunityMember readCommunityMemberOfCommunity(Community community, Long idPerson){
        String queryText = "select cm from " + CommunityMember.class.getName() + " cm " +
                "join "+ Community.class.getName() + " c on community_id = c.id " +
                "join " + Person.class.getName() + " p on person_id = :parametro1 and c.id = :parametro2";

        Query query = entityManager().createQuery(queryText).setParameter("parametro1", idPerson).setParameter("parametro2", community.getId());
        if(query.getResultList().size() != 0){
            return (CommunityMember) query.getResultList().get(0);
        }else{
            return null;
        }

    }
}


