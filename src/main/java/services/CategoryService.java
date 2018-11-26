package services;

import beans.Category;
import beans.Counterpart;
import beans.Message;
import beans.Project;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("categoryService")
public class CategoryService implements CategoryServiceInterface{


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        Query q = em.createQuery("select cat from Category cat");
        return q.getResultList();
    }

    @Override
    public Category getFromId(int id) {
        Query q = em.createQuery("select c from Category c where c.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        List<Category> tmp = q.getResultList();
        if (tmp.size() > 0)
            return tmp.get(0);
        else
            return null;
    }

}
