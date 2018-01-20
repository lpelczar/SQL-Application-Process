package data;


import data.MentorsContract.MentorsEntry;
import models.Entry;
import models.Mentor;

import java.sql.*;
import java.util.*;

public class MentorsDbHelper extends DbHelper {

    public List<String> getFirstNameAndLastNameColumn() {

        String statement = "SELECT " +
                           MentorsEntry.COLUMN_FIRST_NAME + "," +
                           MentorsEntry.COLUMN_LAST_NAME +
                           " FROM " + MentorsEntry.TABLE_NAME + ";";
        List<String> results = new ArrayList<>();

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
                           " FROM " + MentorsEntry.TABLE_NAME +" WHERE city = \"Miskolc\";";
        List<String> results = new ArrayList<>();

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

    public List<Entry> getAllMentors() {

        String statement = "SELECT * FROM " + MentorsEntry.TABLE_NAME + ";" ;

        List<Entry> mentors = new ArrayList<>();
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
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return mentors;
    }

    public boolean addMentor(Mentor mentor) {

        String insertStatement = createInsertApplicantStatement(mentor);

        try {
            update(insertStatement);
            return true;
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return false;
    }

    private String createInsertApplicantStatement(Mentor mentor) {

        return "INSERT INTO " + MentorsEntry.TABLE_NAME + " (" +
                MentorsEntry.COLUMN_FIRST_NAME + "," +
                MentorsEntry.COLUMN_LAST_NAME + "," +
                MentorsEntry.COLUMN_NICK_NAME + "," +
                MentorsEntry.COLUMN_PHONE_NUMBER + "," +
                MentorsEntry.COLUMN_EMAIL + "," +
                MentorsEntry.COLUMN_CITY + "," +
                MentorsEntry.COLUMN_FAVOURITE_NUMBER + ")" +
                " VALUES (" +
                "'" + mentor.getFirstName() + "'," +
                "'" + mentor.getLastName() + "'," +
                "'" + mentor.getNickName() + "'," +
                "'" + mentor.getPhoneNumber() + "'," +
                "'" + mentor.getEmail() + "'," +
                "'" + mentor.getCity() + "'," +
                mentor.getFavouriteNumber() + ");" ;
    }

    public Mentor getMentorById(int id) {

        Mentor mentor = null;
        String selectStatement = "SELECT * FROM " + MentorsEntry.TABLE_NAME +
                " WHERE " + MentorsEntry.COLUMN_ID + " = " + id + ";" ;

        try {
            ResultSet resultSet = query(selectStatement);
            while (resultSet.next())
                mentor = new Mentor(
                        resultSet.getInt(MentorsEntry.COLUMN_ID),
                        resultSet.getString(MentorsEntry.COLUMN_FIRST_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_LAST_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_NICK_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_PHONE_NUMBER),
                        resultSet.getString(MentorsEntry.COLUMN_EMAIL),
                        resultSet.getString(MentorsEntry.COLUMN_CITY),
                        resultSet.getInt(MentorsEntry.COLUMN_FAVOURITE_NUMBER));
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return mentor;
    }

    public boolean updateMentorById(Mentor mentor) {

        String updateStatement = createUpdateStatement(mentor);

        try {
            update(updateStatement);
            return true;
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return false;
    }

    private String createUpdateStatement(Mentor mentor) {

        String updateStatement = "UPDATE " + MentorsEntry.TABLE_NAME + " SET ";
        if (!mentor.getFirstName().equals("0"))
            updateStatement += MentorsEntry.COLUMN_FIRST_NAME + " = '" + mentor.getFirstName() + "',";
        if (!mentor.getLastName().equals("0"))
            updateStatement += MentorsEntry.COLUMN_LAST_NAME + " = '" + mentor.getLastName() + "',";
        if (!mentor.getNickName().equals("0"))
            updateStatement += MentorsEntry.COLUMN_NICK_NAME + " = '" + mentor.getNickName() + "',";
        if (!mentor.getPhoneNumber().equals("0"))
            updateStatement += MentorsEntry.COLUMN_PHONE_NUMBER + " = '" + mentor.getPhoneNumber() + "',";
        if (!mentor.getEmail().equals("0"))
            updateStatement += MentorsEntry.COLUMN_EMAIL + " = '" + mentor.getEmail() + "',";
        if (!mentor.getCity().equals("0"))
            updateStatement += MentorsEntry.COLUMN_CITY + " = '" + mentor.getCity() + "',";
        updateStatement += MentorsEntry.COLUMN_FAVOURITE_NUMBER + " = " + mentor.getFavouriteNumber() +
        " WHERE " + MentorsEntry.COLUMN_ID + " = " + mentor.getId() + ";";
        return updateStatement;
    }

    public List<Mentor> getMentorsByPhrase(String searchPhrase) {

        String selectSqlStatement = createSelectMentorsByPhraseStatement(searchPhrase);

        List<Mentor> mentors = new ArrayList<>();
        try {
            ResultSet resultSet = query(selectSqlStatement);
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
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return mentors;
    }

    private String createSelectMentorsByPhraseStatement(String searchPhrase) {

        return "SELECT * FROM " + MentorsEntry.TABLE_NAME +
               " WHERE " +
                MentorsEntry.COLUMN_FIRST_NAME + " LIKE '%" + searchPhrase + "%' OR " +
                MentorsEntry.COLUMN_LAST_NAME + " LIKE '%" + searchPhrase + "%' OR " +
                MentorsEntry.COLUMN_NICK_NAME + " LIKE '%" + searchPhrase + "%' OR " +
                MentorsEntry.COLUMN_PHONE_NUMBER + " LIKE '%" + searchPhrase + "%' OR " +
                MentorsEntry.COLUMN_EMAIL + " LIKE '%" + searchPhrase + "%' OR " +
                MentorsEntry.COLUMN_CITY + " LIKE '%" + searchPhrase + "%' OR " +
                MentorsEntry.COLUMN_FAVOURITE_NUMBER + " LIKE '%" + searchPhrase + "%' " +
                ";" ;
    }
}
