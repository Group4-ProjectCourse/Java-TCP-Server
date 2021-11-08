package app.controller;

import app.App;
import app.database.DbResponse;
import app.database.MagneticCard;
import app.util.Path;
import app.util.RequestUtil;
import app.util.ViewUtil;
import com.google.gson.Gson;
import org.bson.conversions.Bson;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;
import sun.awt.X11.XSystemTrayPeer;

import java.util.HashMap;
import java.util.Map;

import static app.util.JsonUtil.dataToJson;
import static app.App.cardDao;
import static app.util.RequestUtil.getParamUUID;
import static java.lang.System.out;

public class CardController {
    public static Route fetchOneCard = (Request req, Response res) -> {

//        if (clientAcceptsJson(req))
//            return dataToJson(cardDao.getCardByUUID(getParamUUID(req)));
//
//        return ViewUtil.notAcceptable.handle(req, res);
        return dataToJson(cardDao.getCardByUUID(getParamUUID(req)));
    };

    public static Route verifyOneCard = (Request req, Response res) -> {
        MagneticCard receivedCard = RequestUtil.getCard(req);
        out.println("Received UUID: " + receivedCard.getUUID());
        String receivedPassword = receivedCard.getPassword();
        boolean uuidVerified = App.mongoManager.verifyUUID(receivedCard);
        out.println("UUID verified: " + uuidVerified);
        res.header("Content-Type", "application/json;charset=utf-8");

        if(receivedPassword == null && uuidVerified) {
            res.status(HttpStatus.ACCEPTED_202);
            return new Gson().toJson(new DbResponse("UUID was found", 202));
        }
        else if (receivedPassword == null) {
            res.status(HttpStatus.NOT_ACCEPTABLE_406);
            return new Gson().toJson(new DbResponse("Card with such UUID: " + receivedCard.getUUID() + " was not found!", (short) 406));
        }
        else if (uuidVerified && App.mongoManager.verifyPassword(receivedCard)) {
            res.status(HttpStatus.OK_200);
            return new Gson().toJson(new DbResponse("UUID and Password were verified!", 200));
        }
        else {
            res.status(HttpStatus.UNAUTHORIZED_401);
            return new Gson().toJson(new DbResponse("Incorrect password for the card: "  + receivedCard.getUUID(), (short) 401));
        }
    };

    public static Route verifyOneCardPass = (Request req, Response res) -> {
        if(App.mongoManager.verifyUUID(RequestUtil.getCard(req))) {
            res.status(HttpStatus.OK_200);
            return 200;
        }
        else {
            res.status(HttpStatus.NOT_ACCEPTABLE_406);
            return 406;
        }
    };

    public static Route fetchAllCards = (Request req, Response res) -> {

        Map<String, Object> model = new HashMap<>(2);
        model.put("cards", cardDao.getAllCards());

        return ViewUtil.render(req, model, Path.Views.CARDS_ALL);
    };
}
