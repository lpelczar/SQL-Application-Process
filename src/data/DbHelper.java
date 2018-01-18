package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {

    private static final String DATABASE_NAME = "applications.db";

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection;
    }
}
