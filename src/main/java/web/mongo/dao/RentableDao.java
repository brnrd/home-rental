package web.mongo.dao;

import web.mongo.entity.RentableItem;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public interface RentableDao {
    
    RentableItem selectById(String id);
    String insert(RentableItem rentableItem);
    
}
