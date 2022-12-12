package zoo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@Entity
public class ScheduledActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name="activity_id",nullable = false)
    private Activity activity;

    @Column(nullable = false)
    private ActivityType activityType;

    @Column(nullable=false)
    private ActivityStatus activityStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer places;


    public void setPlaces(Integer places) {
        this.places = places;
    }


    public Integer getPlaces() {
        return places;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }
}
