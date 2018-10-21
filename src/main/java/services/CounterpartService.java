package services;

import beans.Counterpart;
import beans.Project;
import beans.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service("counterpartService")
public class CounterpartService implements CounterpartServiceInterface {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Counterpart> findAll() {
        Query q = em.createQuery("select c from Counterpart c order by c.price asc ");
        return q.getResultList();
    }

    @Override
    public List<Counterpart> findAll(int limit) {
        Query q = em.createQuery("select c from Counterpart c order by c.price asc ");
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public void insert(Counterpart counterpart) {
        em.persist(counterpart);
    }

    @Override
    public Counterpart getFromId(int id) {
        Query q = em.createQuery("from Counterpart c where id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        if (q.getResultList().size() > 0)
            return (Counterpart) q.getResultList().get(0);
        else
            return null;
    }

    public List<Counterpart> getFromProject(Project p) {
        List<Counterpart> tmp = findAll();

        ArrayList<Counterpart> ret = new ArrayList<>();
        for (Counterpart c : tmp) {
            if (c.getBelongProject().getId() == p.getId())
                ret.add(c);
        }
        return ret;
    }

    @Override
    public int update(Counterpart counterpart) {
        Query q = em.createQuery("update Counterpart set price = :price and name = :name and description = :description" +
                " where id = :id");
        q.setParameter("id", counterpart.getId());
        q.setParameter("price", counterpart.getPrice());
        q.setParameter("name", counterpart.getName());
        q.setParameter("description", counterpart.getDescription());

        return q.executeUpdate();
    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete Counterpart where id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }
}
