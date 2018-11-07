package services;

import beans.Project;
import beans.User;

import java.util.List;

public interface IProjectService extends CRUDService<Project> {

    int donation(User u, Project p, int val);
    List<Project> search(String terms);
}
