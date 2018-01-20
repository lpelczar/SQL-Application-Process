package data;

import data.MentorsContract.MentorsEntry;
import models.*;
import java.sql.*;
import java.util.*;

public class MentorsDbHelper extends DbHelper {

    private MentorsStatementCreator mentorsStatementCreator = new MentorsStatementCreator();

    public List<String> getFirstNameAndLastNameColumn() {

        String statement = mentorsStatementCreator.selectFirstNameAndLastNameFromMentorsStatement();

        List<String> results = new ArrayList<>();
        try {
        ResultSet resultSet = query(statement);
        while (resultSet.next())
            results.add(resultSet.getString(MentorsEntry.COLUMN_FIRST_NAME) + " " +
                        resultSet.getString(MentorsEntry.COLUMN_LAST_NAME));
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<String> getNickNamesOfMentorsAtMiskolc() {

        String statement = mentorsStatementCreator.selectNickNameOfMentorsAtMiskolcStatement();

        List<String> results = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                results.add(resultSet.getString(MentorsEntry.COLUMN_NICK_NAME));
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<Entry> getAllMentors() {

        String statement = mentorsStatementCreator.selectAllMentorsStatement();

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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return mentors;
    }

    public boolean addMentor(Mentor mentor) {

        String insertStatement = mentorsStatementCreator.insertMentorsStatement(mentor);
        return update(insertStatement);
    }

    public Mentor getMentorById(int id) {

        String selectStatement = mentorsStatementCreator.selectMentorByIdStatement(id);

        Mentor mentor = null;
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return mentor;
    }

    public boolean updateMentorById(Mentor mentor) {

        String updateStatement = mentorsStatementCreator.updateMentorStatement(mentor);
        return update(updateStatement);
    }

    public List<Mentor> getMentorsByPhrase(String searchPhrase) {

        String selectSqlStatement = mentorsStatementCreator.selectMentorsByPhraseStatement(searchPhrase);

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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return mentors;
    }
}
