package services;

import beans.Project;
import beans.User;

public interface IProjectService extends CRUDService<Project> {

    int donation(User u, Project p, int val);
}
