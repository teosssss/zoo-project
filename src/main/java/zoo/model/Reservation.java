package zoo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {


    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private ScheduledActivity scheduledActivity;
    @Id
    private Long id;


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
}