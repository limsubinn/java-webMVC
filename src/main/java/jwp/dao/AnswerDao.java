package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import jwp.model.Answer;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public List<Answer> findAll(int questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";

        return jdbcTemplate.query(sql,
                pstmt -> pstmt.setInt(1, questionId),
                rs -> new Answer(
                        rs.getInt("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("questionId")));
    }
}
