package web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class UserMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {
        UserExtractor ue = new UserExtractor();
        return ue.extractData(rs);
    }

}
