package web.dao;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import web.model.Property;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public interface PropertyDao {
    
    void setHibernateTemplate(HibernateTemplate hibernateTemplate);
    Boolean save(Property property);
    Boolean update(Property property);
    Boolean delete(String property_id);
    
    List<Property> selectAll();
}
