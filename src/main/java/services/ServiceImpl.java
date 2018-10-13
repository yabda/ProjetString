package services;


import beans.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import repository.ProjectRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;
import beans.Project;

@Service
public class ServiceImpl {

    @Autowired
    ProjectRepo pR;

    @Autowired
    EntityManager em;

    public Set<Project> getAllProj(){

        Iterable<Project> allProj;
        Set<Project> projects = new HashSet<Project>();
        allProj = pR.findAll();
        for (Project p:allProj) {
            projects.add(p);
        }
        return projects;
    }
}
