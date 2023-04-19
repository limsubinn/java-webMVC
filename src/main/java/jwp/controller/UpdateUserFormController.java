package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String query = request.getQueryString();
        String userId = query.split("=")[1];
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null) {
            request.setAttribute("user", user);
            return "/user/updateForm.jsp";
        }

        return "redirect:/";
    }
}