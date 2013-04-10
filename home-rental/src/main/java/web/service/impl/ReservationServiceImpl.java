package web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.ReservationDao;
import web.model.Property;
import web.model.Reservation;
import web.model.User;
import web.service.ReservationService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service("reservationService")
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservDao;
    
    @Override
    public Reservation findById(Integer reserv_id) {
        return reservDao.findById(reserv_id);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveReservation(Reservation reserv) {
        reservDao.saveReservation(reserv);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteReservation(Integer reserv_id) {
        Reservation reserv = reservDao.findById(reserv_id);
        if (reserv != null) {
            reservDao.delete(reserv);
        }
    }
    
    @Override
    public List<Reservation> findByProperty(Property property) {
        return reservDao.findByProperty(property);
    }
    
    @Override
    public List<Reservation> findByUser(User user) {
        return reservDao.findByUser(user);
    }
}