package services;

import beans.Counterpart;
import beans.Project;

import java.util.List;

public interface ICounterpartService {
    List<Counterpart> findAll();
    List<Counterpart> findAll(int limit);

    void insert(Counterpart t);
    Counterpart getFromId(int id);
    void update(Counterpart t);
    void destroy(int counterpartId);

    List<Counterpart> getFromProject(Project p);
}
