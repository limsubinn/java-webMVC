package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import jwp.model.Answer;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate<>();

    public Answer insert(Answer answer) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();
        String sql = "INSERT INTO ANSWERS" +
                "(writer, contents, createdDate, questionId)" +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, pstmt -> {
                    pstmt.setString(1, answer.getWriter());
                    pstmt.setString(2, answer.getContents());
                    pstmt.setDate(3, answer.getCreatedDate());
                    pstmt.setInt(4, answer.getQuestionId());
                }, keyHolder);

        return findByAnswerId(keyHolder.getId());
    }

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

    public Answer findByAnswerId(int answerId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE answerId = ?";

        return jdbcTemplate.queryForObject(sql,
                pstmt -> pstmt.setInt(1, answerId),
                rs -> new Answer(
                        rs.getInt("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("questionId")));
    }
}
