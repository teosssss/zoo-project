package zoo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zoo.model.ActivityStatus;
import zoo.model.Reservation;
import zoo.repositories.ReservationRepository;

@Service
public class ReservationRegisterService implements RegisterService<Reservation> {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void register(Reservation model) {
        Integer reservedPlaces = reservationRepository.sumReservedPlaces(model.getScheduledActivity());
        if (reservedPlaces==null){
            reservedPlaces=0;
        }
        if (reservedPlaces + model.getNumPlaces()>model.getScheduledActivity().getPlaces()){
            throw new IllegalStateException("Failed to save request not enough places");
        }
        if (reservedPlaces+model.getNumPlaces()==model.getScheduledActivity().getPlaces()){
            model.getScheduledActivity().setActivityStatus(ActivityStatus.FULL);

        }

        reservationRepository.save(model);

    }
}
