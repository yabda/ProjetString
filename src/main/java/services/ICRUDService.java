package services;

import beans.Project;
import beans.User;

import java.util.List;

public interface ICRUDService<T> {

    /*
    * get the list of all the item T in Database
    * */
    List<T> findAll();

    /*
    * get the {limit} first item T in database
    *
    * @Param : int limit : number of item to fetch
    * */
    List<T> findAll(int limit);

    /*
    * Add an item T to database
    *
    * @Param T t: Item to add to database
    * */
    void insert(T t);

    /*
    * Get an unique item from database
    *
    * @Param int id: id of the wanted item
    * */
    T getFromId(int id);

    /*
    * Update an item in database
    *
    * @Param : T t: item that will be update to it current value
    * */
    void update(T t);

    /*
    * Destroy an item in database
    *
    * @param int id: id of the item to destroy in database
    * */
    void destroy(int id);
}
