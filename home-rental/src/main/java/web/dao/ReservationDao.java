package web.dao;

import java.util.List;
import web.model.Property;
import web.model.Reservation;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface ReservationDao extends AbstractDao<Reservation, Integer> {
    
    Boolean saveReservation(Reservation reserv);
    List<Reservation> findByProperty(Property property);
    List<Reservation> findByUser(User user);
}