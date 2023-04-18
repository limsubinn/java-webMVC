package jwp.controller;

import core.db.MemoryUserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/list")
public class ListUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에 저장된 정보 가져오기
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        // 로그인 되어 있는 상태
        if (user != null) {
            request.setAttribute("users", MemoryUserRepository.getInstance().findAll());
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // 로그인 되어 있지 않은 상태
        response.sendRedirect("/user/login.jsp");
    }
}
