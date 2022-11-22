package zoo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ScheduledActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer places;


    public void setPlaces(Integer places) {
        this.places = places;
    }


    public Integer getPlaces() {
        return places;
    }

    public Integer getPlacesAvailable() {
        return places- reservations.size();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
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


}
