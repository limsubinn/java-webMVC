package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private final View view;
    private final Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        view.render(model, request, response);
    }

    public ModelAndView addObject(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public View getView() {
        return view;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
