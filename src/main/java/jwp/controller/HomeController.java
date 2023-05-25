package jwp.controller;

import core.mvc.*;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class HomeController extends AbstractController {
    HttpSession session;
    QuestionDao questionDAO = new QuestionDao();

    @Override
    public void setSession(HttpSession httpSession) {
        this.session = httpSession;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        List<Question> questions = questionDAO.findAll();
        return jspView("/home.jsp").addObject("questions", questions);
    }
}
