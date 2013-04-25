package web.dao;

import java.util.List;
import org.joda.time.LocalDateTime;
import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface SearchDao {
    
    List<Property> searchForSpecificCountry(String country, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
    List<Property> searchInRadius25(Integer lat, Integer lng, LocalDateTime checkin, LocalDateTime checkout, Integer guests);
}