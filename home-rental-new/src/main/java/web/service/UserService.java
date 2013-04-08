package web.service;

import java.util.List;
import web.model.User;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

public interface UserService {
    
    User findById(String user_id);
    User findByEmail(String email);
    void saveUser(User user);
    void deleteUser(String username);
    List<User> findAll();
}