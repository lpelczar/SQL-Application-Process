package data;

import models.Mentor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static data.MentorsContract.*;

public class MentorsDbHelper extends DbHelper {

    public void create() {
        Connection connection = getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE " + MentorsEntry.TABLE_NAME + " (" +
                    MentorsEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL," +
                    MentorsEntry.COLUMN_LAST_NAME + " TEXT NOT NULL," +
                    MentorsEntry.COLUMN_NICK_NAME + " TEXT NOT NULL," +
                    MentorsEntry.COLUMN_PHONE_NUMBER + " TEXT NOT NULL," +
                    MentorsEntry.COLUMN_EMAIL + " TEXT NOT NULL," +
                    MentorsEntry.COLUMN_CITY + " TEXT NOT NULL," +
                    MentorsEntry.COLUMN_FAVOURITE_NUMBER + " INTEGER," +
                    "); ";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void insert(String sqlStatement) {

        Connection connection = getConnection();
        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
            statement.close();
            connection.commit();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public List<Mentor> select(String sqlStatement) {

        List<Mentor> mentors = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            while (resultSet.next()) {
                mentors.add(new Mentor(
                        resultSet.getString(MentorsEntry.COLUMN_FIRST_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_LAST_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_NICK_NAME),
                        resultSet.getString(MentorsEntry.COLUMN_PHONE_NUMBER),
                        resultSet.getString(MentorsEntry.COLUMN_EMAIL),
                        resultSet.getString(MentorsEntry.COLUMN_CITY),
                        resultSet.getInt(MentorsEntry.COLUMN_FAVOURITE_NUMBER)));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return mentors;
    }
}
