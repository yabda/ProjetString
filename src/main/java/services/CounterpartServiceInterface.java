package services;

import beans.Counterpart;
import beans.Project;

import java.util.List;

public interface CounterpartServiceInterface {
    List<Counterpart> findAll();
    List<Counterpart> findAll(int limit);

    void insert(Counterpart t);
    Counterpart getFromId(int id);
    int update(Counterpart t);
    int destroy(int id);

    List<Counterpart> getFromProject(Project p);
}
