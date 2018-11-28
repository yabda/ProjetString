package services;

import beans.Project;
import beans.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService implements UserServiceInterface {
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
        System.out.println("pass before sha : "+user.getPassword());
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] result = md.digest(user.getPassword().getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        user.setPassword(sb.toString());
        System.out.println("pass de "+user.getId()+" sha : "+user.getPassword());

        em.persist(user);
    }

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
    public int update(User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        em.clear();
        em.merge(user);

        return 0;
    }

    public void updateUserSession(HttpSession session){

        User u = (User)session.getAttribute("user");
        u = getFromId(u.getId());
        session.setAttribute("user",u);

    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete User u where u.id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }
}
