package web.mongo.dao;

import web.mongo.entity.UserItem;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public interface UserDao {
    
    UserItem selectById(String id);
    String insert(UserItem userItem);

}
