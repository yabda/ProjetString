package services;

import beans.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    List<User> findAll();
    List<User> findAll(int limit);

    void insert(User t);
    User getFromId(int id);
    void update(User t);
    void destroy(int id);

    User isValid(String name, String password);
    String testName(String name);
    void updateUserSession(HttpSession session);
}
