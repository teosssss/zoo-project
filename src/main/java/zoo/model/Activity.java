package zoo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<ScheduledActivity> scheduledActivities;

    @Size(max = 64)
    @Column(nullable = false)
    public String title;

    @Column
    public String description;

    public void setId(Long id) {
        this.id = id;
    }




    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle(){return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ScheduledActivity> getScheduledActivities() {
        return scheduledActivities;
    }

    public void setScheduledActivities(List<ScheduledActivity> scheduledActivities) {
        this.scheduledActivities = scheduledActivities;
    }

}
