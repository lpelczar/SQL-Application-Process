package data;


import data.MentorsContract.MentorsEntry;

import java.sql.*;
import java.util.*;

public class MentorsDbHelper extends DbHelper {

    private final String tableName = "mentors";

    public List<String> getFirstNameAndLastNameColumn() {

        String statement = "SELECT first_name, last_name FROM " + tableName +";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
        ResultSet resultSet = query(statement);
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

        String statement = "SELECT nick_name FROM " + tableName +" WHERE city = \"Miskolc\";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                results.add(resultSet.getString(MentorsEntry.COLUMN_NICK_NAME));
        } catch (SQLException e) {
            System.out.println("Error reading nick name column from mentor");
        } finally {
            closeConnection();
        }
        return results;
    }
}
