package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update(String sql, PreparedStatementSetter pstmtSetter) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper rowMapper) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            pstmtSetter.setValues(pstmt);
            User user = null;
            if (rs.next()) {
                user = rowMapper.mapRow(rs);
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> query(String sql, RowMapper rowMapper) {
        List<User> users = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()){

            while (rs.next()) {
                User user = rowMapper.mapRow(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
