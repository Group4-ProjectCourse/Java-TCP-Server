package app.controller;

import app.util.Path;
import app.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static app.util.JsonUtil.dataToJson;
import static app.util.RequestUtil.clientAcceptsJson;
import static app.Application.cardDao;
import static app.util.RequestUtil.getParamUUID;

public class CardController {
    public static Route fetchOneCard = (Request req, Response res) -> {

//        if (clientAcceptsJson(req))
//            return dataToJson(cardDao.getCardByUUID(getParamUUID(req)));
//
//        return ViewUtil.notAcceptable.handle(req, res);
        return dataToJson(cardDao.getCardByUUID(getParamUUID(req)));
    };

    public static Route fetchAllCards = (Request req, Response res) -> {

        Map<String, Object> model = new HashMap<>(2);
        model.put("cards", cardDao.getAllCards());

        return ViewUtil.render(req, model, Path.Views.CARDS_ALL);
    };
}
