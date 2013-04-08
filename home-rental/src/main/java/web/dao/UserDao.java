package web.dao;

import java.util.List;
import web.model.User;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */
public interface UserDao {
        
    Boolean save(User user);
    Boolean update(User user);
    Boolean delete(String user_id);
    
    User findByEmail(String email);
    User findById(String user_id);    
    List<User> selectAll();
}