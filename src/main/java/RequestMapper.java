import jwp.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/", new ForwardController("/home.jsp"));
        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/userList", new ForwardController("/user/list.jsp"));
        controllers.put("/user/userLogin", new ForwardController("/user/login.jsp"));
        controllers.put("/user/userUpdate", new ForwardController("/user/updateForm.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/signup", new CreateUserController());
    }

    public String run(HttpServletRequest request, HttpServletResponse response) {
        Controller controller = controllers.get(request.getRequestURI());
        String url = controller.execute(request, response);
        return url;
    }
}