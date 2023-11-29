package ar.edu.utn.frba.dds.repositories;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericRepositoryJPA<T> implements WithSimplePersistenceUnit {
    private final Class<T> type;

    protected EntityTransaction entityTransaction;


    /** ------------------------ CONSTRUCTORS ------------------------ **/

    public GenericRepositoryJPA() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    /** ------------------------ METHODS ------------------------ **/

    public void setEntityTransaction(EntityTransaction entityTransaction) {
        this.entityTransaction = entityTransaction;
    }

    public void setEntities(){
        EntityTransaction tx = entityManager().getTransaction();
        this.setEntityTransaction(tx);
    }

    public void create(T t) {
        entityTransaction.begin();
        entityManager().persist(t);
        entityTransaction.commit();
    }

    public T read(final Object id) {
        entityManager().clear();
        return (T) entityManager().find(type, id);
    }

    public List<T> readAll() {
         entityManager().clear();
        CriteriaBuilder cb = this.entityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        TypedQuery<T> query = entityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void update(final T t) {
        entityTransaction.begin();
        entityManager().merge(t);
        entityTransaction.commit();
    }

    public void delete(final Object objeto) {
        entityTransaction.begin();
        entityManager().remove(entityManager().merge(objeto));
        entityTransaction.commit();
    }

    public void clearCache(){
        entityManager().clear();
    }

}
