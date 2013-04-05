package web.dao.impl;

import java.util.Calendar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.jdbc.odbc.ee.DataSource;
import web.dao.IUserDao;
import web.dao.mapper.UserMapper;
import web.model.User;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class UserDao implements IUserDao {
    
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public void create(String username, String name, String firstName, 
    String email, String password, Calendar created, Boolean staff) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("insert into user (username, name, firstname, email, "
                + "password, created, staff) values (?,?,?,?,?,?,?)", 
                new Object[] {username, name, firstName, email, password, 
                    created, staff});
    }

    @Override
    public List<User> getByUsername(String username) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select id, username, name, firstname, email, "
                + " created, staff from user where username = ?", 
                new Object[] {username}, new UserMapper());
    }
    
     @Override
    public List<User> getByUsernameWithPassword(String username) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select id, username, password, "
                + " staff from user where username = ?", 
                new Object[] {username}, new UserMapper());
    }

    @Override
    public List<User> getById(String id) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select id, username, name, firstname, email, "
                + " created, staff from user where id = ?", 
                new Object[] {id}, new UserMapper());
    }

    @Override
    public List<User> selectAll() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select id, username, name, firstname, email, "
                + "password, created, staff from user", new UserMapper());
    }

    @Override
    public void delete(String id) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("delete from user where id= ? ", new Object[] {id});
    }

}
