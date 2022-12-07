package zoo.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zoo.model.Reservation;
import zoo.model.ScheduledActivity;

public interface ReservationRepository extends CrudRepository<
        Reservation, Integer > {
    Iterable<Reservation> findAll();

    Reservation findById(Long id);

    @Query("SELECT SUM(r.numPlaces) FROM Reservation r WHERE r.scheduledActivity=?1")
    Integer sumReservedPlaces(ScheduledActivity scheduledActivity);

}
