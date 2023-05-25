package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;


public class ListUserController extends AbstractController {
    HttpSession session;
    UserDao userDao = new UserDao();

    @Override
    public void setSession(HttpSession httpSession) {
        this.session = httpSession;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/user/list.jsp").addObject("users", userDao.findAll());
        }
        return jspView("redirect:/user/loginForm");
    }
}
