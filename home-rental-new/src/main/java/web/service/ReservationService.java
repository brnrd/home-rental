package web.service;

import java.util.List;
import web.model.Property;
import web.model.Reservation;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface ReservationService {

    Reservation findById(Integer reserv_id);
    void saveReservation(Reservation reserv);
    void deleteReservation(Integer reserv_id);
    List<Reservation> findByProperty(Property property);
    List<Reservation> findByUser(User user);
}