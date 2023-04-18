package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 로그인 성공
        if (user.matchPassword(password)) {
            session.setAttribute("user", user);
            response.sendRedirect("/");
            return;
        }

        // 로그인 실패
        response.sendRedirect("/user/login_failed.jsp");
    }
}