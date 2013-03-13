package web.mongo.dao.impl;

import web.mongo.dao.AbstractDao;
import web.mongo.dao.RentableDao;
import web.mongo.entity.RentableItem;

/**
 *
 * @author monsieurblah
 */
public class RentableDaoImpl extends AbstractDao implements RentableDao {

    @Override
    public RentableItem selectById(String id) {
        return (RentableItem)mongoTemplate.findById(id, RentableItem.class);
    }

    @Override
    public String insert(RentableItem rentableItem) {
        mongoTemplate.insert(rentableItem);
        return rentableItem.getId();
    }

}
