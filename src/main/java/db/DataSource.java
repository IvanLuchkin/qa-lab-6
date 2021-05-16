package db;

public class DataSource {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "4427";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";


    public DataSource() {
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getUrl() {
        return URL;
    }
}
