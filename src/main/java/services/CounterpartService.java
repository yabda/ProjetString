package services;

import beans.Counterpart;
import beans.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service("counterpartService")
public class CounterpartService implements ICounterpartService {
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
    @Transactional
    public void insert(Counterpart counterpart) {
        em.persist(counterpart);
    }

    @Override
    public Counterpart getFromId(int id) {
        Query q = em.createQuery("from Counterpart c where c.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        List<Counterpart> tmp = q.getResultList();
        if (tmp.size() > 0)
            return tmp.get(0);
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
        ret.sort(Comparator.comparing(Counterpart::getPrice));

        return ret;
    }

    @Override
    public void update(Counterpart counterpart) {
        Query q = em.createQuery("update Counterpart c set c.price = :price , c.name = :name , c.description = :description" +
                " where c.id = :id");
        q.setParameter("id", counterpart.getId());
        q.setParameter("price", counterpart.getPrice());
        q.setParameter("name", counterpart.getName());
        q.setParameter("description", counterpart.getDescription());

        q.executeUpdate();
    }

    @Override
    @Transactional
    public void destroy(int counterpartId) {

        Counterpart c=em.find(Counterpart.class,counterpartId);
        em.remove(c);
    }
}
