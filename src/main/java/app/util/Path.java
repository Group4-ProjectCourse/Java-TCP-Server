package app.util;

import lombok.Getter;

public class Path {
    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Web {
        @Getter public static final String INDEX = "/index/";
        @Getter public static final String LOGIN = "/login/";
        @Getter public static final String LOGOUT = "/logout/";
        @Getter public static final String CARDS = "/cards/";
        @Getter public static final String CARD_VERIFY = "/cards/verify/";
        @Getter public static final String ONE_CARD = "/cards/:uuid/";
    }

    public static class Views {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String LOGIN = "/velocity/login/login.vm";
        public final static String CARDS_ALL = "/velocity/card/all.vm";
        public static final String CARDS_ONE = "/velocity/card/one.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
    }
}
