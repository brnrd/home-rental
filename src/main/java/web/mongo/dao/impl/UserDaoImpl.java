package web.mongo.dao.impl;

import web.mongo.dao.AbstractDao;
import web.mongo.dao.UserDao;
import web.mongo.entity.UserItem;

/**
 *
 * @author monsieurblah
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public UserItem selectById(String id) {
        return (UserItem)mongoTemplate.findById(id, UserItem.class);
    }

    @Override
    public String insert(UserItem userItem) {
        mongoTemplate.insert(userItem);
        return userItem.getId();
    }

}
