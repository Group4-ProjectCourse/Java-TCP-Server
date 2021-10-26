package app.controller;

import app.util.Path;
import app.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static app.Application.cardDao;
public class IndexController {
    public static Route serveIndexPage = (Request req, Response res) -> {
        Map<String, Object> model = new HashMap<>(2);

        return ViewUtil.render(req, model, Path.Views.INDEX);
    };
}
