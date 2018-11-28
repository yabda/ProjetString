package services;

import beans.Category;
import beans.Project;
import beans.User;

import java.util.Date;
import java.util.List;

public interface IProjectService extends CRUDService<Project> {

    int donation(User u, Project p, int val);
    List<Project> search(String terms);
    void update(Project p, String projectName, Category category, Date d, String description, int goal);
}
