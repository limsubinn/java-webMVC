package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {
    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDAO = new AnswerDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String questionId = request.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDAO.findAllByQuestionId(Integer.parseInt(questionId));
        request.setAttribute("question", question);
        request.setAttribute("answers",answers);
        return "/qna/show.jsp";
    }
}
