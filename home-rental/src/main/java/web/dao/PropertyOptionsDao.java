package web.dao;

import web.model.Property;
import web.model.PropertyOptions;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface PropertyOptionsDao extends AbstractDao<PropertyOptions, Integer> {
 
    Boolean savePropertyOptions(PropertyOptions pOptions);
    PropertyOptions findByProperty(Property property);
}