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
    }

    void closeConnection() {

        try {
            if (connection != null && !connection.isClosed())
                connection.close();
            if (statement != null && !statement.isClosed())
                statement.close();
            if (resultSet != null && !resultSet.isClosed())
                resultSet.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

    }

    ResultSet query(String sqlStatement) throws SQLException {

        statement = connection.createStatement();
        return statement.executeQuery(sqlStatement);
    }

    void update(String sqlStatement) throws SQLException {

        connection.setAutoCommit(false);
        statement = connection.createStatement();
        statement.executeUpdate(sqlStatement);
        connection.commit();
    }
}
