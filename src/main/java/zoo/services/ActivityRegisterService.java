package zoo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.model.ScheduledActivity;
import zoo.repositories.ScheduledActivityRepository;

@Service
public class ActivityRegisterService implements RegisterService<ScheduledActivity> {
    @Autowired
    private ScheduledActivityRepository activityRepository;


    @Override
    public void register(ScheduledActivity scheduledActivity) {
        activityRepository.save(scheduledActivity);
    }
}