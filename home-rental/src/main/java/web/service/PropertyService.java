package web.service;

import java.util.List;
import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface PropertyService {

    Property findById(Integer property_id);
    void saveProperty(Property property);
    void deleteProperty(Integer property_id);
    List<Property> findAll();
}