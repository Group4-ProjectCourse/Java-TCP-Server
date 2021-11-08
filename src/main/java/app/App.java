package app;

import app.controller.CardController;
import app.controller.IndexController;
import app.database.CardDao;
import app.database.MongoManager;
import app.util.Filters;
import app.util.Path;
import app.util.ViewUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static CardDao cardDao;
    public static MongoManager mongoManager;
    //public static UserDao userDao;

    static {
        cardDao = new CardDao();
        mongoManager = new MongoManager();
        //userDao = new UserDao();
    }

    public static void main(String[] args) {
        // Configure Spark
        port(8085);
        ipAddress("192.168.1.195");
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen();

        // Set up before-filters (called before each get/post)
        before("*", Filters.addTrailingSlashes);
        before("*", Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX, IndexController.serveIndexPage);
        get(Path.Web.CARDS, CardController.fetchAllCards);
        get(Path.Web.ONE_CARD, CardController.fetchOneCard);
        post(Path.Web.CARD_VERIFY, CardController.verifyOneCard);
        get("*", ViewUtil.notFound);

        //Set up after-filters (called after each get/post)
        //after("*", Filters.addGzipHeader);

        enableCors();
    }

    private static void enableCors() {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
        });

        logger.info("CORS support enabled.");
    }
}

//get("/hello/:name", (request, response) -> "Hello " + request.params(":name"));