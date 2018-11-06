package services;

import beans.Project;
import beans.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.SchemaOutputResolver;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("projectService")
public class ProjectService implements IProjectService {
    @PersistenceContext
    private EntityManager em;

    @Resource(name = "userService")
    private UserServiceInterface uS;


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

        Project p = em.merge(project);


        /*Query q = em.createQuery("UPDATE Project p set p.title = :title , p.description = :description, " +
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
        q.setParameter("failed", project.isFailed());*/

        return (p!=project?0:1);
    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete Project where id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }

    @Override
    @Transactional
    public int donation(User u, Project p, int val){
        if (p.getCurrent()<p.getGoal() || val <=0 || p.getDeadLine().after(new Date())|| u ==null){
            if (p.getCurrent()+val <= p.getGoal()) {
                p.setCurrent(p.getCurrent()+val);
            }
            else
            {
                p.setCurrent(p.getGoal());
            }
            Set<User> userParticipation = p.getUsersParticipation();
            userParticipation.add(u);
            p.setUsersParticipation(userParticipation);
            Map<User,Float> participations = p.getParticipations();
            System.out.println("participantions : "+participations.toString());
            if (participations.get(u)!=null){
                System.out.println("l'utilisateur a déja donné a hauteur de : "+participations.get(u));
                System.out.println("on y ajoute : "+val);
                participations.replace(u,(participations.get(u)+val));
            }else{
                System.out.println("premiere participation");
                participations.put(u,(float)val);
                Set<Project> participe = null;

                if(u.getParticipeProjects()!=null)
                {
                    participe = u.getParticipeProjects();
                }
                participe.add(p);


                u.setParticipeProjects(participe);
            }
            p.setParticipations(participations);
            update(p);
            uS.update(u);


            return 1;

        }
        else {
            System.out.println("project over, no donation added");
             return -1;  // null donation or project already over
        }

    }

}
