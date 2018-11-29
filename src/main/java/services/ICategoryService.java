package services;

import beans.Category;
import beans.Counterpart;
import beans.Project;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category getFromId(int id);
}
