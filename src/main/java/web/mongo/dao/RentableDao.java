package web.mongo.dao;

import web.mongo.entity.RentableItem;

/**
 *
 * @author monsieurblah
 */
public interface RentableDao {
    
    RentableItem selectById(String id);
    String insert(RentableItem rentableItem);
    
}
