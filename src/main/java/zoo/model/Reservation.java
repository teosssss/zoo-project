package zoo.model;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    @JoinTable
    private ScheduledActivity scheduledActivity;

    //@Query("SELECT SUM(r.numPlaces) FROM Reservation r WHERE r.scheduledActivity=?1")
    //Integer sumReservedPlaces(ScheduledActivity scheduledActivity);

    @Column(nullable = false)
    private Integer placesReserved;

    public void setPlacesReserved(Integer placesReserved) {
        this.placesReserved = placesReserved;
    }





    public User getUser() {
        return user;
    }

    public ScheduledActivity getScheduledActivity() {
        return scheduledActivity;
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
}