package services;

import beans.Project;
import beans.User;

import java.util.List;

public interface ICRUDService<T> {

    List<T> findAll();
    List<T> findAll(int limit);

    void insert(T t);
    T getFromId(int id);
    int update(T t);
    int destroy(int id);
}
