package web.dao;

import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface PropertyDao extends AbstractDao<Property, Integer> {

    Boolean saveProperty(Property property);
}
