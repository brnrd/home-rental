package web.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import web.dao.ReservationDao;
import web.model.Property;
import web.model.Reservation;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Repository
public class ReservationDaoImpl extends AbstractDaoImpl<Reservation, Integer> implements ReservationDao {

    protected ReservationDaoImpl() {
        super(Reservation.class);
    }
    
    @Override
    public Boolean saveReservation(Reservation reserv) {
        if (reserv == null) {
            return false;
        }
        saveOrUpdate(reserv);
        return true;
    }
    
    @Override
    public List<Reservation> findByProperty(Property property) {
        return findByCriteria(Restrictions.eq("property", property));
    }
    
    @Override
    public List<Reservation> findByUser(User user) {
        return findByCriteria(Restrictions.eq("user", user));
    }
}