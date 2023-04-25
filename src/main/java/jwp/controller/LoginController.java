package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 로그인 성공
        if ((user != null) && user.matchPassword(password)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }

        // 로그인 실패
        return "redirect:/user/loginFailed";
    }
}