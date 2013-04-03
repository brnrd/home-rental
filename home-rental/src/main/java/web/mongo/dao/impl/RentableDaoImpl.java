package web.mongo.dao.impl;

import org.springframework.data.mongodb.core.MongoOperations;
import web.mongo.MongoConfig;
import web.mongo.dao.AbstractDao;
import web.mongo.dao.RentableDao;
import web.mongo.entity.RentableItem;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class RentableDaoImpl extends AbstractDao implements RentableDao {

    @Override
    public RentableItem selectById(String id) throws Exception {
        MongoOperations mongoOps = new MongoConfig().mongoTemplate();
        return mongoOps.findById(id, RentableItem.class);
    }

    @Override
    public String insert(RentableItem rentableItem) throws Exception {
        MongoOperations mongoOps = new MongoConfig().mongoTemplate();
        mongoOps.insert(rentableItem);
        return rentableItem.getId();
    }

}
