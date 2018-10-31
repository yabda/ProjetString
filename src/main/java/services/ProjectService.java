package services;

import beans.Project;
import beans.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service("projectService")
public class ProjectService implements IProjectService {
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
        Query q = em.createQuery("select p from Project p where p.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        List<Project> tmp = q.getResultList();
        if (tmp.size() > 0)
            return tmp.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public int update(Project project) {
        Query q = em.createQuery("UPDATE Project p set p.title = :title , p.description = :description, " +
                "p.goal = :goal, p.current = :current, " +
                "p.deadLine = :deadline, p.createdAt = :createdAt, " +
                "p.updatedAt = :updatedAt, p.failed = :failed " +
                "WHERE p.id = :id");
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

    public int donation(User u, Project p, int val){

        EntityGraph<Project> eg = em.createEntityGraph(Project.class);
        eg.addSubgraph("usersParticipation");


        Hibernate.initialize(p.getUsersParticipation());
        if (p.getCurrent()<p.getGoal() || val <=0 || p.getDeadLine().after(new Date())){
            if (p.getCurrent()+val <= p.getGoal()) {
                p.setCurrent(p.getCurrent()+val);
            }
            else
            {
                p.setCurrent(p.getGoal());
            }
            Set<User> participators = p.getUsersParticipation();
            participators.add(u);
            p.setUsersParticipation(participators);
            //Maps<User,float> partiipations = p.getParticipations();
            //participators.

        }
        else {

             return -1;  // null donation or project already over
        }

        return 0;
    }

}
