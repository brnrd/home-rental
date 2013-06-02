package web.dao;

import java.util.List;
import web.model.Property;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface PropertyDao extends AbstractDao<Property, Integer> {

    Boolean saveProperty(Property property);
    List<Property> findProperty(User user);
    List<String> selectDistinctCities();
    List<String> selectDistinctCountries();
}
