package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null) {
            String pw = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            user.update(new User(userId, pw, name, email));

            response.sendRedirect("/user/list");
        }
    }
}