package services;

import beans.Project;
import beans.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService implements UserServiceInterface {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
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

    @Override
    @Transactional
    public void insert(User user) {
        MessageDigest md = null;
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

    public Boolean testName(String name) {
        if (name.isEmpty())
            return false;
        if (name.length() > 20)
            return false;
        List<User> users = findAll();
        for (User u: users) {
            if (u.getName().equals(name))
                return false;
        }
        return true;
    }

    @Override
    public User getFromId(int id) {
        Query q = em.createQuery("from User u where u.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        if (q.getResultList().size() > 0)
            return (User)q.getResultList().get(0);
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
        Query q = em.createQuery("update User u set u.name = :name , u.createdAt = :createdAt," +
                "  u.updatedAt = :updatedAt" +
                " where id = :id");
        q.setParameter("id", user.getId());
        q.setParameter("name", user.getName());
        q.setParameter("createdAt", user.getCreatedAt());
        q.setParameter("updatedAt", user.getUpdatedAt());

        return q.executeUpdate();
    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete User u where u.id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }
}
