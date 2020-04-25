package integration.unity.akhil.gamedepot.utils;

public class Constants {
    public static int PAGE_SIZE = 8;
    public static String BASE_URL = "https://api.rawg.io/api/";
    public static class Popular{
        public static String DATE = "2019-01-01,2019-12-31";
        public static String ORDERING = "-added";
    }
    public static class Anticipated{
        public static String DATE = "2019-10-10,2020-10-10";
        public static String ORDERING = "-added";
    }
    public static class TopRated{
        public static String DATE = "2017-01-01,2019-12-31";
        public static String ORDERING = "-rating";
    }
}
