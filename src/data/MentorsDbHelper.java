package data;


import data.MentorsContract.MentorsEntry;
import models.Mentor;

import java.sql.*;
import java.util.*;

public class MentorsDbHelper extends DbHelper {

    private final String tableName = "mentors";

    public List<String> getFirstNameAndLastNameColumn() {

        String statement = "SELECT " +
                           MentorsEntry.COLUMN_FIRST_NAME + "," +
                           MentorsEntry.COLUMN_LAST_NAME +
                           " FROM " + tableName + ";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
        ResultSet resultSet = query(statement);
        while (resultSet.next())
            results.add(resultSet.getString(MentorsEntry.COLUMN_FIRST_NAME) + " " +
                        resultSet.getString(MentorsEntry.COLUMN_LAST_NAME));
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<String> getNickNamesOfMentorsAtMiskolc() {

        String statement = "SELECT " + MentorsEntry.COLUMN_NICK_NAME +
                           " FROM " + tableName +" WHERE city = \"Miskolc\";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                results.add(resultSet.getString(MentorsEntry.COLUMN_NICK_NAME));
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<Mentor> getAllMentors() {

        String statement = "SELECT * FROM " + tableName + ";" ;

        List<Mentor> mentors = new ArrayList<>();
        openConnection();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                mentors.add(new Mentor(
                        resultSet.getInt(MentorsEntry.COLUMN_ID),
                        resultSet.getString(MentorsEntry.COLUMN_FIRST_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_LAST_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_NICK_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_PHONE_NUMBER),
                        resultSet.getString(MentorsEntry.COLUMN_EMAIL),
                        resultSet.getString(MentorsEntry.COLUMN_CITY),
                        resultSet.getInt(MentorsEntry.COLUMN_FAVOURITE_NUMBER)));
        } catch (SQLException e) {
            System.out.println("Error reading nick name column from mentor");
        } finally {
            closeConnection();
        }
        return mentors;
    }
}
