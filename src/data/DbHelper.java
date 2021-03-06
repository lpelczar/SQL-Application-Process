package data;

import java.sql.*;

class DbHelper {

    private static final String DATABASE_NAME = "application_process.db";
    private Connection connection;
    private Statement statement;

    private void openConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    void closeConnection() {

        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) { /*ignored*/ }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) { /*ignored*/ }
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
