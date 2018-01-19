package data;


import data.MentorsContract.MentorsEntry;

import java.sql.*;
import java.util.*;

public class MentorsDbHelper extends DbHelper {

    public List<String> getFirstNameAndLastNameColumn() {

        String statement = "SELECT first_name, last_name FROM mentors;";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
        ResultSet resultSet = readData(statement);
        while (resultSet.next())
            results.add(resultSet.getString(MentorsEntry.COLUMN_FIRST_NAME) + " " +
                        resultSet.getString(MentorsEntry.COLUMN_LAST_NAME));
        } catch (SQLException e) {
            System.out.println("Error reading first and last name column from mentor");
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<String> getNickNamesOfMentorsAtMiskolc() {

        String statement = "SELECT nick_name FROM mentors WHERE city = \"Miskolc\";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            ResultSet resultSet = readData(statement);
            while (resultSet.next())
                results.add(resultSet.getString(MentorsEntry.COLUMN_NICK_NAME));
        } catch (SQLException e) {
            System.out.println("Error reading nick name column from mentor");
        } finally {
            closeConnection();
        }
        return results;
    }

    private ResultSet readData(String sqlStatement) throws SQLException {

        Statement statement = getConnection().createStatement();
        return statement.executeQuery(sqlStatement);
    }
}
