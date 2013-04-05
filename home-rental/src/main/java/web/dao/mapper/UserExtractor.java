package web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import web.model.User;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class UserExtractor implements ResultSetExtractor {
    
    private static final int ID = 1;
    private static final int USERNAME = 2;
    private static final int NAME = 3;
    private static final int FIRSTNAME = 4;
    private static final int EMAIL = 5;
    private static final int PASSWORD = 6;
    private static final int CREATED = 7;
    private static final int STAFF = 8;

    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        User user = new User();
        user.setId(rs.getString(ID));
        user.setUsername(rs.getString(USERNAME));
        user.setName(rs.getString(NAME));
        user.setFirstname(rs.getString(FIRSTNAME));
        user.setEmail(rs.getString(EMAIL));
        user.setPassword(rs.getString(PASSWORD));
        user.setCreated(rs.getObject(CREATED, Calendar.class));
        user.setStaff(rs.getBoolean(STAFF));
        return user;
    }

}
