package ar.edu.utn.frba.dds.repositories.entities.locations;

import ar.edu.utn.frba.dds.models.locations.Department;
import ar.edu.utn.frba.dds.repositories.GenericRepositoryJPA;

import javax.persistence.Query;
import java.util.List;

public class DepartmentRepository extends GenericRepositoryJPA<Department> {

    private static DepartmentRepository instance;

    public static synchronized DepartmentRepository getInstance() {
        if (instance == null) {
            instance = new DepartmentRepository();
        }
        instance.setEntities();
        return instance;
    }

    public Department readByName(String nombre){

        Query query =  entityManager().createQuery(" from " + Department.class.getName() + " where name = :parametro")
                .setParameter("parametro", nombre);


        List<Department> resultados = query.getResultList();

        return resultados.get(0);
    }

}
