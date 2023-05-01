package jwp.dao;

import core.jdbc.JdbcTemplate;
import jwp.model.Question;

import java.util.List;

public class QuestionDao {
    JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTIONS";

        return jdbcTemplate.query(sql,
                rs -> new Question(
                        rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

}
