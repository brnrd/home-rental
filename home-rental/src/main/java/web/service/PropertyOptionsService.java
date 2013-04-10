package web.service;

import web.model.Property;
import web.model.PropertyOptions;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface PropertyOptionsService {
    
    PropertyOptions findById(Integer options_id);
    void savePropertyOptions(PropertyOptions pOptions);
    void deletePropertyOptions(Integer options_id);
    PropertyOptions findByProperty(Property property);
}