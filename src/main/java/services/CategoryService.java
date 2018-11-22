package services;

import beans.Category;
import beans.Counterpart;
import beans.Project;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("CategoryService")
public class CategoryService implements CategoryServiceInterface{


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        Query q = em.createQuery("select cat from Category cat");
        return q.getResultList();
    }

}
