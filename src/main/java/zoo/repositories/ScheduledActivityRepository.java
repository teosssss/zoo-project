package zoo.repositories;

import org.springframework.data.repository.CrudRepository;
import zoo.model.ScheduledActivity;

public interface ScheduledActivityRepository
        extends CrudRepository<
        ScheduledActivity, Integer > {

    ScheduledActivity findById(Long id);
    Iterable<ScheduledActivity> findAll();
}
