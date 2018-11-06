package services;

import beans.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAll();
    List<User> findAll(int limit);

    void insert(User t);
    User getFromId(int id);
    int update(User t);
    int destroy(int id);

    public User isValid(String name, String password);
    public String testName(String name);
}
