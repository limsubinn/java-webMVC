package jwp.controller;

import core.mvc.*;
import jwp.dao.UserDao;
import jwp.model.User;

import java.util.Map;

public class UpdateUserFormController extends AbstractController {
    UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        User user = userDao.findByUserId(userId);

        if (user != null) {
            return jspView("/user/updateForm.jsp").addObject("user", user);
        }

        return jspView("redirect:/");
    }
}
