package web.dao.impl;

import org.springframework.stereotype.Repository;
import web.dao.PropertyDao;
import web.model.Property;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Repository
public class PropertyDaoImpl extends AbstractDaoImpl<Property, Integer> implements PropertyDao {

    protected PropertyDaoImpl() {
        super(Property.class);
    }
    
    @Override
    public Boolean saveProperty(Property property) {
        if (property == null) {
            return false;
        }
        saveOrUpdate(property);
        return true;
    }
}