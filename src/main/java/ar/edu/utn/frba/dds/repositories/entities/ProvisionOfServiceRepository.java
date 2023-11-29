package ar.edu.utn.frba.dds.repositories.entities;

import ar.edu.utn.frba.dds.models.entities_establishment.Entity;
import ar.edu.utn.frba.dds.models.entities_establishment.Establishment;
import ar.edu.utn.frba.dds.models.services.ProvisionOfService;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class ProvisionOfServiceRepository extends GenericRepositoryJPA<ProvisionOfService> {
    private static ProvisionOfServiceRepository instance;

    public static synchronized ProvisionOfServiceRepository getInstance() {
        if (instance == null) {
            instance = new ProvisionOfServiceRepository();
        }
        instance.setEntities();
        return instance;
    }



    public List<ProvisionOfService> readAllForEntity(Long id){

       // String text = "SELECT ps.id, ps.name, ps.establishment_id, ps.service_id FROM provisionofservice ps " +
        //        " join establishment e on e.id = ps.establishment_id" +
          //      " join entity et on e.id = ps.establishment_id" +
            //    " where et.id = :parametro";

        String queryText = "SELECT ps FROM "+ ProvisionOfService.class.getName() +" ps, " + Establishment.class.getName()
                + " e, " + Entity.class.getName() + " et " + " where e.id = ps.establishment_id and et.id = e.entity_id and et.id = :parametro";

        Query query =  entityManager().createQuery(queryText, ProvisionOfService.class);
        query.setParameter("parametro", id);

        List<ProvisionOfService> resultados = query.getResultList();
        return resultados;
    }


    public List<ProvisionOfService> getProvisionsOfServiceByServiceId(Long serviceId) {
        String consulta = "SELECT p FROM " + ProvisionOfService.class.getName() + " p "  + " where service_id = :parametro";
        Query query = entityManager().createQuery(consulta, ProvisionOfService.class);
        query.setParameter("parametro", serviceId);
        List<ProvisionOfService> resultados = query.getResultList();
        return resultados;
    }


    public List<ProvisionOfService> getProvisionsOfServiceByEstablishmentId(Long establishmentId) {
        entityManager().clear();
        String consulta = "SELECT p FROM " + ProvisionOfService.class.getName() + " p " + " where establishment_id = :parametro";
        Query query = entityManager().createQuery(consulta, ProvisionOfService.class);
        query.setParameter("parametro", establishmentId);
        List<ProvisionOfService> resultados = query.getResultList();
        return resultados;
    }

}
