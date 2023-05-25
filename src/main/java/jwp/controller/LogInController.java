package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogInController extends AbstractController {
    HttpSession session;
    UserDao userDao = new UserDao();

    @Override
    public void setSession(HttpSession httpSession) {
        this.session = httpSession;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String password = params.get("password");
        User logInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(logInUser)) {
            return jspView("redirect:/").addObject("user", user);
        }

        return jspView("redirect:/user/loginFailed");
    }
}
