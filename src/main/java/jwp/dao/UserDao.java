package jwp.dao;

import jwp.model.User;

import java.util.List;

public class UserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public void insert(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public void update(User user) {
        String sql = "update users set password=?, name=?, email=? where userId=?";
        jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());
        });
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";

        return jdbcTemplate.query(sql,rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                rs.getString("email")));
    }

    public User findByUserId(String userId) {
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

        return jdbcTemplate.queryForObject(sql,
                pstmt -> pstmt.setString(1, userId),
                rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email")));
    }
}