package web.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.dao.SearchDao;
import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List<Property> searchForSpecificCountry(String country, LocalDateTime checkin, LocalDateTime checkout, Integer guests) {
        String hql = "SELECT p.property_id, p.title, p.short_desc, p.price, p.city, p.country," +
                                     "p.type, p.note, p.rooms*2 AS places" +
                                     "FROM property AS p" +
                                     "WHERE p.country=:country" +
                                     "AND p.rent_period_start<=:checkin" +
                                     "AND p.rent_period_stop>=:checkout" +
                                     "AND NOT EXISTS (SELECT avg(r.hosts) AS guests_number FROM reservation AS r " +
                                     "WHERE p.property_id=r.target_property "+
                                     "AND r.date_rent_start<=:checkin" +
                                     "AND :checkout<=r.date_rent_stop HAVING places < guests_number+:guests)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("country", country);
        query.setParameter("checkin", checkin.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        query.setParameter("checkout", checkin.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        query.setParameter("guests", guests);
        return query.list();
    }
    
    @Override
    public List<Property> searchInRadius25(Integer lat, Integer lng, LocalDateTime checkin, LocalDateTime checkout, Integer guests) {
        
        return null;
    }
}