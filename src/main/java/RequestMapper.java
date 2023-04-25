import jwp.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static final Map<String, Controller> controllers = new HashMap<>();
    private Controller controller;

    public RequestMapper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        controller = controllers.get(request.getRequestURI());
        if (controller == null) {
            controller = new ForwardController(request.getRequestURI());
        }
    } static {
        controllers.put("/", new ForwardController("/home.jsp"));
        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/userList", new ForwardController("/user/list.jsp"));
        controllers.put("/user/userLogin", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/signup", new CreateUserController());
    }

    public String run() {
        String url = controller.execute(request, response);
        return url;
    }
}