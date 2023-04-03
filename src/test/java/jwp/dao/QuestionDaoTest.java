package jwp.dao;

import core.jdbc.ConnectionManager;
import jwp.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionDaoTest {
    QuestionDao questionDao;

    @BeforeEach
    public void setup() {
        questionDao = new QuestionDao();
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void crud() throws SQLException {
        Question expected = new Question("writer", "title", "contents",0);
        Question actual = questionDao.insert(expected);
        assertEquals(expected, actual);

        actual.updateTitleAndContents("title","con");
        questionDao.update(actual);
        Question updated = questionDao.findByQuestionId(actual.getQuestionId());
        assertEquals(actual.getTitle(),updated.getTitle());
        assertEquals(actual.getContents(), updated.getContents());

        questionDao.delete(actual.getQuestionId());

        assertEquals(6, questionDao.findAll().size());
    }

}