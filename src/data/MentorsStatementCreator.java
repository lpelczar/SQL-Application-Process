package data;

import data.MentorsContract.MentorsEntry;
import models.Mentor;

public class MentorsStatementCreator {

    public String selectFirstNameAndLastNameFromMentorsStatement() {

        return "SELECT " +
                MentorsEntry.COLUMN_FIRST_NAME + "," +
                MentorsEntry.COLUMN_LAST_NAME +
                " FROM " + MentorsEntry.TABLE_NAME + ";";
    }

    public String selectNickNameOfMentorsAtMiskolcStatement() {

        return "SELECT " + MentorsEntry.COLUMN_NICK_NAME +
                " FROM " + MentorsEntry.TABLE_NAME +" WHERE city = \"Miskolc\";";
    }

    public String selectAllMentorsStatement() {

        return "SELECT * FROM " + MentorsEntry.TABLE_NAME + ";" ;
    }

    public String insertMentorsStatement(Mentor mentor) {

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

    public String selectMentorByIdStatement(int id) {

        return "SELECT * FROM " + MentorsEntry.TABLE_NAME +
                " WHERE " + MentorsEntry.COLUMN_ID + " = " + id + ";" ;
    }

    public String updateMentorStatement(Mentor mentor) {

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

    public String selectMentorsByPhraseStatement(String searchPhrase) {

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
