package services;

import beans.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("projectService")
public class ProjectService implements CRUDService<Project> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Project> findAll() {
        Query q = em.createQuery("select p from Project p order by p.createdAt desc");
        return q.getResultList();
    }

    @Override
    public List<Project> findAll(int limit) {
        Query q = em.createQuery("select p from Project p order by p.createdAt desc");
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    @Transactional
    public void insert(Project project) {
        em.persist(project);
    }

    @Override
    public Project getFromId(int id) {
        Query q = em.createQuery("from Project p where id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        if (q.getResultList().size() > 0)
            return (Project)q.getResultList().get(0);
        else
            return null;
    }

    @Override
    public int update(Project project) {
        Query q = em.createQuery("update Project set title = :title and description = :description and goal = :goal and current = :current and deadline = :deadline and createdAt = :createdAt and updatedAt = :updatedAt and failed = :failed" +
                " where id = :id");
        q.setParameter("id", project.getId());
        q.setParameter("title", project.getTitle());
        q.setParameter("description", project.getDescription());
        q.setParameter("goal", project.getGoal());
        q.setParameter("current", project.getCurrent());
        q.setParameter("deadline", project.getDeadLine());
        q.setParameter("createdAt", project.getCreatedAt());
        q.setParameter("updatedAt", project.getUpdatedAt());
        q.setParameter("failed", project.isFailed());

        return q.executeUpdate();
    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete Project where id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }
}
