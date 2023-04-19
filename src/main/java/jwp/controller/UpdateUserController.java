package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String pw = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        user.update(new User(userId, pw, name, email));

        return "redirect:/user/list";
    }
}