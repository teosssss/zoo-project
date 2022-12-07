package zoo.model;

import javax.persistence.*;


@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name="scheduledActivity_id",nullable = false)
    private ScheduledActivity scheduledActivity;


    @Column(nullable=false)
    private int numPlaces;

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    public int getNumPlaces() {
        return numPlaces;
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