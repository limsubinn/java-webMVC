package core.mvc;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface Controller {
    ModelAndView execute(Map<String, String> params) throws Exception;
    default void setSession(HttpSession httpSession) {};
}
