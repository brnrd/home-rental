package web.dao.impl;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import web.dao.PropertyDao;
import web.model.Property;
import web.model.User;

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

    @Override
    public List<Property> findProperty(User user) {
        return findByCriteria(Restrictions.eq("owner", user));
    }
    
    @Override
    public List<String> selectDistinctCities() {
        return listDistinctCriteria("city", "property");
    }
    
    @Override
    public List<String> selectDistinctCountries() {
        return listDistinctCriteria("country", "property");
    }
}