package web.dao;

import web.model.User;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */
public interface UserDao extends AbstractDao<User, String> {
    
    Boolean saveUser(User user);
    User findByEmail(String email);
}