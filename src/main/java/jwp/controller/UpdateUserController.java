package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import java.util.Map;

public class UpdateUserController extends AbstractController {
    UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        userDao.update(new User(
                params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email")));
        return jspView("redirect:/user/list");
    }
}
