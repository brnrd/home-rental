package web.dao;

import java.util.Calendar;
import java.util.List;
import sun.jdbc.odbc.ee.DataSource;
import web.model.User;


/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public interface IUserDao {
    
    void setDataSource(DataSource ds);
    
    void create(String username, String name, String firstName, String email, 
            String password, Calendar created, Boolean staff);
    
    List<User> getByUsername(String username);
    List<User> getByUsernameWithPassword(String username);
    List<User> getById(String id);
    
    List<User> selectAll();
    
    void delete(String id);

}
