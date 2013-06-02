package web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.PropertyOptionsDao;
import web.model.Property;
import web.model.PropertyOptions;
import web.service.PropertyOptionsService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service("propertyOptionsService")
@Transactional(readOnly = true)
public class PropertyOptionsServiceImpl implements PropertyOptionsService {

    @Autowired
    private PropertyOptionsDao propertyOptionsDao;
    
    @Override
    public PropertyOptions findById(Integer options_id) {
        return propertyOptionsDao.findById(options_id);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void savePropertyOptions(PropertyOptions pOptions) {
        propertyOptionsDao.savePropertyOptions(pOptions);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deletePropertyOptions(Integer options_id) {
        PropertyOptions pOptions = propertyOptionsDao.findById(options_id);
        if (pOptions != null) {
            propertyOptionsDao.delete(pOptions);
        }
    }
    
    @Override
    public PropertyOptions findByProperty(Property property) {
        return propertyOptionsDao.findByProperty(property);
    }
}