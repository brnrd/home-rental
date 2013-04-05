package web.dao;

import java.util.Calendar;
import java.util.List;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import web.model.User;


/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public interface IUserDao {
    
    void setDataSource(DriverManagerDataSource ds);
    
    void create(String username, String name, String firstName, String email, 
            String password, Calendar created, Boolean staff);
    
    List<User> getByUsername(String username);
    List<User> getById(String id);
    List<User> getByUsernameWithPassword(String username);
    
    List<User> selectAll();
    
    void delete(String id);

}
