package data;

import java.sql.*;

class DbHelper {

    private static final String DATABASE_NAME = "application_process.db";
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    void openConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    void closeConnection() {

        try {
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.out.println("Problem closing connections");
        }

    }

    Connection getConnection() {
        return connection;
    }

    ResultSet getResultSet() {
        return resultSet;
    }

    Statement getStatement() {
        return statement;
    }
}
