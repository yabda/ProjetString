package services;

import beans.Project;
import beans.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Service("userService")
public class UserService implements UserServiceInterface {
    @PersistenceContext
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
    public void insert(User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setPassword(new String(md.digest(user.getPassword().getBytes())));
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
    public int update(User user) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setPassword(new String(md.digest(user.getPassword().getBytes())));
        Query q = em.createQuery("update User u set u.name = :name , u.password = :password , u.createdAt = :createdAt," +
                "  u.updatedAt = :updatedAt" +
                " where id = :id");
        q.setParameter("id", user.getId());
        q.setParameter("name", user.getName());
        q.setParameter("password", user.getPassword());
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
