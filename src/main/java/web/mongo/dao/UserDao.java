package web.mongo.dao;

import web.mongo.entity.UserItem;

/**
 *
 * @author monsieurblah
 */
public interface UserDao {
    
    UserItem selectById(String id);
    String insert(UserItem userItem);

}
