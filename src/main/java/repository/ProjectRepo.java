package repository;

import beans.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



// Repository is use to easily get data from DB
@Repository
public interface ProjectRepo extends CrudRepository<Project,Integer> {

}
