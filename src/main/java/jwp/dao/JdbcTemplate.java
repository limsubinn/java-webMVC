package jwp.dao;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    public void update(String sql, PreparedStatementSetter pstmtSetter) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            pstmtSetter.setValues(pstmt);
            T object = null;
            if (rs.next()) {
                object = rowMapper.mapRow(rs);
            }

            return object;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper) {
        List<T> objects = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()){

            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

}
