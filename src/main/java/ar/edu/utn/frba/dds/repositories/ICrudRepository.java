package ar.edu.utn.frba.dds.repositories;

import java.util.List;

public interface ICrudRepository<T> {

    void create(T t);

    T read(T t);

    List<T> readAll();

    void update(T t);

    void delete(T t);

    void clearCache();
}
