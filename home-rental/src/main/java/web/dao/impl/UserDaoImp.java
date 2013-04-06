package web.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import web.dao.UserDao;
import web.model.User;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Component
public class UserDaoImp implements UserDao {

    public UserDaoImp() {}
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    
    @Override
    public Boolean save(User user) {
        if (user == null) {
            return false;
        }
        System.out.println("DAO - save : User before save : " + user.getId() + "\t" + user.getUsername());
        try {
            hibernateTemplate.save(user);
            System.out.println("DAO - save : User after save : " + user.getId() + "\t" + user.getUsername());
        } catch (Exception e) {
            System.out.println("Problem DB : " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public Boolean update(User user) {
        if (user == null) {
            return false;
        }
        try {
            hibernateTemplate.update(user);
        } catch (Exception e) {
            System.out.println("Problem DB : " + e);
            return false;
        }
        return true;
    }
    
    @Override
    public Boolean delete(String user_id) {
        User user = (User) hibernateTemplate.get(User.class, user_id);
        if (user == null) {
            return false;
        }
        try {
            hibernateTemplate.delete(user);
        } catch (Exception e) {
            System.out.println("Problem DB : " + e);
            return false;
        }
        return true;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        user = (User) hibernateTemplate.find("FROM user WHERE email=?"+email).get(0);
        return user;
    }

    @Override
    public User findById(String user_id) {
        User user = null;
        user = (User) hibernateTemplate.get(User.class, user_id);
        return user;
    }

    @Override
    public List<User> selectAll() {
        return hibernateTemplate.find("FROM user");
    }
}