package web.service.impl;

import java.util.List;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(String user_id) {
        return userDao.findById(user_id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void deleteUser(String user_id) {
        User user = userDao.findById(user_id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAll("user");
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void connected(User current_user) {
        current_user.setLastConnection(LocalDateTime.now());
        userDao.saveUser(current_user);
    }
}