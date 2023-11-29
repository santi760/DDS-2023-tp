package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.Person;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;
import javax.persistence.Query;
import java.util.List;

public class CommunityRepository extends GenericRepositoryJPA<Community> {

    private static CommunityRepository instance;

    public static synchronized CommunityRepository getInstance() {
        if (instance == null) {
            instance = new CommunityRepository();
        }
        instance.setEntities();
        return instance;
    }

    public List<Community> readAllForMember(Long memberId){

        //String queryText = "select c from community c" +
         //       " join communitymember_x_community cc on c.id = cc.id_community" +
          //      " join communitymember cm on cc.id_community_member = cm.id" +
           //    " where cm.id = :parametro";

        String queryText = "select c from "+ Community.class.getName() +" c, " + CommunityMember.class.getName() +" cm"  +
                "   where cm.id = :parametro";
        Query query =  entityManager().createQuery(queryText, Community.class).setParameter("parametro", memberId);

        List<Community> resultados = query.getResultList();

        return resultados;
    }

    public Community readByName(String comunidadLeida){

        Query query =  entityManager().createQuery(" from " + Community.class.getName() + " where name = :parametro")
                .setParameter("parametro", comunidadLeida);


        List<Community> resultados = query.getResultList();

        return resultados.get(0);
    }

    public List<Community> readAllCommunityOfPerson(Long personId){
        String queryText = "select distinct c from " + Community.class.getName() + " c " +
               "join "+ CommunityMember.class.getName() + " cm on community_id = c.id " +
                "join " + Person.class.getName() + " p on person_id = :parametro";

        //String queryText2 = "select distinct c from " + Community.class.getName() +" c, " + CommunityMember.class.getName() +" " +
          //      "   where person_id = :parametro";
        //Query query1 = entityManager().createQuery(" from " + Community.class.getName() +" , " + CommunityMember.class.getName() +" " +
          //      "where person_id = :parametro").setParameter("parametro", personId);
        Query query = entityManager().createQuery(queryText).setParameter("parametro", personId);

        //Query query = entityManager().createQuery(" from " + Community.class.getName() + " where person_id = :parametro" + " and community_id = :parametro2")
          //      .setParameter("parametro", personId)
            //    .setParameter("parametro2", community.getId());

        return query.getResultList();
    }

}
