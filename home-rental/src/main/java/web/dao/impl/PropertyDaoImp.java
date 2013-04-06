package web.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import web.dao.PropertyDao;
import web.model.Property;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class PropertyDaoImp implements PropertyDao {
    
    
    
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean save(Property property) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean update(Property property) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean delete(String property_id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Property> selectAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
