package web.dao.impl;

import java.util.List;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import web.dao.UserDao;
import web.model.User;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {

    protected UserDaoImpl() {
        super(User.class);
    }
    
    @Override
    public Boolean saveUser(User user) {
        if (user == null) {
            return false;
        }
        saveOrUpdate(user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        List<User> res = findByCriteria(Restrictions.like("email", email, MatchMode.START));
        if (res != null && res.size() > 0) {
            return res.get(0);
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        List<User> res = findByCriteria(Restrictions.like("username", username, MatchMode.START));
        if (res != null && res.size() > 0) {
            return res.get(0);
        }
        return null;
    }
}