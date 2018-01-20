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
            if (connection != null && connection.isClosed())
                connection.close();
            if (statement != null && statement.isClosed())
                statement.close();
            if (resultSet != null && resultSet.isClosed())
                resultSet.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

    }

    ResultSet query(String sqlStatement) throws SQLException {

        openConnection();
        statement = connection.createStatement();
        return statement.executeQuery(sqlStatement);
    }

    boolean update(String sqlStatement) {

        try {
            openConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return false;
    }
}
