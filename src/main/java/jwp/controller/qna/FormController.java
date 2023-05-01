package jwp.controller.qna;

import core.mvc.Controller;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (UserSessionUtils.isLogined(req.getSession())) {
            return "/qna/form.jsp";
        }
        return "redirect:/user/loginForm";
    }
}
