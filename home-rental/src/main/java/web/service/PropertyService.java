package web.service;

import java.util.List;
import web.model.Property;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface PropertyService {

    Property findById(Integer property_id);
    List<Property> findProperty(User user);
    void saveProperty(Property property);
    void deleteProperty(Integer property_id);
    List<Property> findAll();
}