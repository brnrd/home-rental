package web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.PropertyDao;
import web.model.Property;
import web.service.PropertyService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service("propertyService")
@Transactional(readOnly = true)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;
    
    @Override
    public Property findById(Integer user_id) {
        return propertyDao.findById(user_id);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveProperty(Property property) {
        propertyDao.saveProperty(property);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProperty(Integer property_id) {
        Property property = propertyDao.findById(property_id);
        if (property != null) {
            propertyDao.delete(property);
        }
    }

    @Override
    public List<Property> findAll() {
        return propertyDao.selectAll("property");
    }
}