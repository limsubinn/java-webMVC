package jwp.dao;

import jwp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    User mapRow(ResultSet rs) throws SQLException;
}
