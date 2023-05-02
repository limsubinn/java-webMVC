package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateController implements Controller {
    QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(
                Integer.parseInt(req.getParameter("questionId")),
                req.getParameter("title"),
                req.getParameter("contents"));

        System.out.println(questionDao.update(question));
        return "redirect:/";
    }
}
