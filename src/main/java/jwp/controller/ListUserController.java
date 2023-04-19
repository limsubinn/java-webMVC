package jwp.controller;

import core.db.MemoryUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 세션에 저장된 정보 가져오기
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        // 로그인 되어 있는 상태
        if (user != null) {
            request.setAttribute("users", MemoryUserRepository.getInstance().findAll());
            request.setAttribute("user", user);

            return "/user/list.jsp";
        }

        // 로그인 되어 있지 않은 상태
        return "redirect:/user/login";
    }
}
