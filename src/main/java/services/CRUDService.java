package services;

import java.util.List;

public interface CRUDService<T> {

    List<T> findAll();
    List<T> findAll(int limit);

    void insert(T t);
    T getFromId(int id);
    int update(T t);
    int destroy(int id);
}
