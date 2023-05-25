package jwp.controller.qna;

import core.mvc.*;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class FormController extends AbstractController {
    HttpSession session;

    @Override
    public void setSession(HttpSession httpSession) {
        this.session = httpSession;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/qna/form.jsp");
        }
        return jspView("redirect:/user/loginForm");
    }
}
