package web.mongo.dao;

import web.mongo.entity.UserItem;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public interface UserDao {
    
    UserItem selectById(String id) throws Exception;
    String insert(UserItem userItem) throws Exception;

}
