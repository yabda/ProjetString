package services;

import beans.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {
    @PersistenceContext()
    private EntityManager em;


    @Override
    public List<User> findAll() {
        Query q = em.createQuery("select u from User u order by u.createdAt desc");
        return q.getResultList();
    }

    @Override
    public List<User> findAll(int limit) {
        Query q = em.createQuery("select u from User u order by u.createdAt desc");
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Transactional
    @Override
    public void insert(User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = md.digest(user.getPassword().getBytes());                   // hash the password before sending it to DB
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        user.setPassword(sb.toString());
        em.persist(user);
    }

    /*
    * Check if user credentials are valid
    * @Param String name : username
    * @Param String password : user password
    *
    * @Return authenticated user if valid, null otherwise
    * */
    public User isValid(String name, String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        List<User> users = findAll();
        for (User u: users) {
            if (u.getName().equals(name) && Arrays.equals(DatatypeConverter.parseHexBinary(u.getPassword()), md.digest(password.getBytes())))
                return u;
        }
        return null;
    }

    /*
    * Check if a username is free and follow name policies
    *
    * @param String name : username to check
    *
    * @Return Error message if there is one, null otherwise
    * */
    public String testName(String name) {
        if (name.isEmpty())
            return "Empty name";
        if (name.length() > 20)
            return "Name length must be between 1 and 20 characters";
        List<User> users = findAll();
        for (User u: users) {
            if (u.getName().equals(name))
                return "Name already taken !";
        }
        return null;
    }

    @Override
    public User getFromId(int id) {
        Query q = em.createQuery("select u from User u where u.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        List<User> tmp = q.getResultList();
        if (tmp.size() > 0)
            return tmp.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public void update(User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        em.clear();
        em.merge(user);
    }

    /*
    * Update object "user" in session to reflect changes in DB
    *
    * @Param HttpSession session : session to update
    * */
    public void updateUserSession(HttpSession session){

        User u = (User)session.getAttribute("user");
        u = getFromId(u.getId());
        session.setAttribute("user",u);

    }

    @Override
    public void destroy(int id) {
        Query q = em.createQuery("delete User u where u.id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
}
