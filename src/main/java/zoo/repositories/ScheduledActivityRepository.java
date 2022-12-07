package zoo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zoo.model.ScheduledActivity;

import java.util.List;

public interface ScheduledActivityRepository
        extends CrudRepository<
        ScheduledActivity, Integer > {

    ScheduledActivity findById(Long id);
    Iterable<ScheduledActivity> findAll();

    @Query("SELECT s FROM ScheduledActivity s where s.activityType='MAIN'")
    List<ScheduledActivity> findMainActivities();
}
