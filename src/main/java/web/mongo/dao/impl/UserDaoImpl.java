package web.mongo.dao.impl;

import org.springframework.data.mongodb.core.MongoOperations;
import web.mongo.MongoConfig;
import web.mongo.dao.AbstractDao;
import web.mongo.dao.UserDao;
import web.mongo.entity.UserItem;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class UserDaoImpl implements UserDao {
    
    private MongoOperations mongoOps;

    public UserDaoImpl() throws Exception {
        this.mongoOps = new MongoConfig().mongoTemplate();
    }
    
    @Override
    public UserItem selectById(String id) throws Exception {
        return mongoOps.findById(id, UserItem.class);
    }

    @Override
    public String insert(UserItem userItem) throws Exception {
        mongoOps.insert(userItem);
        return userItem.getId();
    }
    

}
