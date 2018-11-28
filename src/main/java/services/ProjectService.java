package services;

import beans.Category;
import beans.Project;
import beans.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service("projectService")
public class ProjectService implements IProjectService {
    @PersistenceContext
    private EntityManager em;

    @Resource(name = "userService")
    private IUserService uS;


    public List<Project> search(String terms){
        List<Project> resultDirect = new ArrayList<Project>();
        List<Project> resultOther = new ArrayList<Project>();
        for (Project p :findAll()) {
            if(p.getTitle().contains(terms)||p.getCategory().getName().contains(terms)){
                    resultDirect.add(p);
            }
            if(p.getDescription().contains(terms) && !resultDirect.contains(p)){
                resultOther.add(p);
            }
        }
        resultDirect.addAll(resultOther);
        return resultDirect;
    }


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
        return (p!=project?0:1);

    }

    @Override
    @Transactional
    public void update(Project p, String projectName, Category category, Date d, String description, int goal){

        p.setTitle(projectName);
        p.setCategory(category);
        p.setDeadLine(d);
        p.setDescription(description);
        p.setGoal(goal);

        update(p);
    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete Project where id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }


    /*
    * do all operations needed to manage donation to a project
    * add cash to the project current
    *
    * add user to the project Userparticipations list if not already in
    * add user to the project participations list or update amount if already in
    * and push project to DB
    *
    * */
    @Override
    @Transactional
    public int donation(User u, Project p, int val){
        if (val <=0 || p.getDeadLine().after(new Date())|| u ==null){
            p.setCurrent(p.getCurrent()+val);                                       //update current project amount
            Map<Integer,Float> participations = p.getParticipations();
            if (participations.containsKey(u.getId())){                             //Update total donation amount if already in participant
                participations.replace(u.getId(),(participations.get(u.getId())+val));
            }else{                                                                  //if first donation by this user add him to needed list
                Set<User> userParticipation = p.getUsersParticipation();
                userParticipation.add(u);
                p.setUsersParticipation(userParticipation);

                participations.put(u.getId(),(float)val);
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
            return 1;
        }
        else {
            return -1;  // null donation or project already over
        }
    }
}
