package web.mongo.dao;

import web.mongo.entity.UserItem;

/**
 *
 * @author monsieurblah
 */
public interface UserDao {
    
    UserItem selectByPk(String id);
    String insert(UserItem entity);

}
