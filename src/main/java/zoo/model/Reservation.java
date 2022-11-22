package zoo.model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name="activities_id")
    private ScheduledActivity scheduledActivity;

    //@Query("SELECT SUM(r.numPlaces) FROM Reservation r WHERE r.scheduledActivity=?1")
    //Integer sumReservedPlaces(ScheduledActivity scheduledActivity);

    @Column(nullable = false)
    private Integer placesReserved;

    public void setPlacesReserved(Integer placesReserved) {
        this.placesReserved = placesReserved;
    }

    public Integer getPlacesReserved() {
        return placesReserved;
    }




    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setScheduledActivity(ScheduledActivity scheduledActivity) {
        this.scheduledActivity = scheduledActivity;
    }

    public ScheduledActivity getScheduledActivity() {
        return scheduledActivity;
    }




}