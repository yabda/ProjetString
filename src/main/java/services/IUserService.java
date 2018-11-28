package services;

import beans.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    List<User> findAll();
    List<User> findAll(int limit);

    void insert(User t);
    User getFromId(int id);
    int update(User t);
    int destroy(int id);

    public User isValid(String name, String password);
    public String testName(String name);
    public void updateUserSession(HttpSession session);
}
