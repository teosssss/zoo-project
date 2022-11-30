package zoo.repositories;

import org.springframework.data.repository.CrudRepository;
import zoo.model.Activity;

public interface ActivityRepository extends CrudRepository<
        Activity, Integer > {
    Iterable<Activity> findAll();

    Activity findById(Long id);

}
