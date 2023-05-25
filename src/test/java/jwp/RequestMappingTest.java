package jwp;

import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.mvc.RequestMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RequestMappingTest {
    private RequestMapping requestMapping;
    private MockHttpServletResponse response;

    @BeforeEach
    void init() {
        requestMapping = new RequestMapping();
        response = new MockHttpServletResponse();
    }

    @Test
    void home() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET","/");
        Controller controller = requestMapping.getController(request);
        ModelAndView modelAndView = controller.execute(createParams(request));
        modelAndView.render(request, response);
        assertEquals("/home.jsp", response.getForwardedUrl());
    }

    private Map<String, String> createParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> params.put(paramName, request.getParameter(paramName)));

        return params;
    }
}