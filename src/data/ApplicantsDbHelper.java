package data;

import data.ApplicantsContract.ApplicantsEntry;
import models.Applicant;
import models.Entry;

import java.sql.*;
import java.util.*;

public class ApplicantsDbHelper extends DbHelper {

    public List<String> getFullNameAndPhoneNumberWithNameCarol() {

        String statement = "WHERE first_name = \"Carol\";";
        return getFullNameAndPhoneNumberOfApplicant(statement);
    }

    public List<String> getFullNameAndPhoneNumberWithDomain() {

        String statement = "WHERE email LIKE \"%@adipiscingenimmi.edu\";";
        return getFullNameAndPhoneNumberOfApplicant(statement);
    }

    private List<String> getFullNameAndPhoneNumberOfApplicant(String whereStatement) {

        String statement = "SELECT first_name || \" \" || last_name AS full_name, phone_number\n" +
                           "FROM " + ApplicantsEntry.TABLE_NAME + " " + whereStatement;

        List<String> results = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_FULL_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER));
        } catch (SQLException e) {
            System.out.println("Error reading applicant");
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<String> addApplicantAndGetHisData(Applicant applicant) {

        String sqlStatement = createAddingApplicantStatement(applicant);
        String selectSqlStatement = "SELECT * FROM " + ApplicantsEntry.TABLE_NAME + " WHERE " +
                ApplicantsEntry.COLUMN_APPLICATION_CODE + " = " + applicant.getApplicationCode() + ";";

        List<String> results = new ArrayList<>();
        try {
            update(sqlStatement);
            closeConnection();
            ResultSet resultSet = query(selectSqlStatement);
            while (resultSet.next()) {
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_ID) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_LAST_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_EMAIL) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_APPLICATION_CODE));
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return results;
    }

    private String createAddingApplicantStatement(Applicant applicant) {

        return "INSERT INTO " + ApplicantsEntry.TABLE_NAME + " (" +
                ApplicantsEntry.COLUMN_FIRST_NAME + "," +
                ApplicantsEntry.COLUMN_LAST_NAME + "," +
                ApplicantsEntry.COLUMN_PHONE_NUMBER + "," +
                ApplicantsEntry.COLUMN_EMAIL + "," +
                ApplicantsEntry.COLUMN_APPLICATION_CODE + ")" +
                " VALUES (" +
                "'" + applicant.getFirstName() + "'," +
                "'" + applicant.getLastName() + "'," +
                "'" + applicant.getPhoneNumber() + "'," +
                "'" + applicant.getEmail() + "'," +
                applicant.getApplicationCode() + ");" ;
    }

    public List<String> updateApplicantAndGetPhoneNumber() {

        String sqlStatement = "UPDATE " + ApplicantsEntry.TABLE_NAME +
                " SET " + ApplicantsEntry.COLUMN_PHONE_NUMBER + " = '003670/223-7459'" +
                " WHERE " + ApplicantsEntry.COLUMN_FIRST_NAME + " = 'Jemima' AND " +
                ApplicantsEntry.COLUMN_LAST_NAME + " = 'Foreman';" ;

        String selectSqlStatement = "SELECT " + ApplicantsEntry.COLUMN_PHONE_NUMBER + " FROM " +
                ApplicantsEntry.TABLE_NAME + " WHERE " + ApplicantsEntry.COLUMN_FIRST_NAME + " = 'Jemima' AND " +
                ApplicantsEntry.COLUMN_LAST_NAME + " = 'Foreman';" ;

        List<String> results = new ArrayList<>();
        try {
            update(sqlStatement);
            closeConnection();
            ResultSet resultSet = query(selectSqlStatement);
            while (resultSet.next()) {
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER));
            }
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return results;
    }

    public boolean deleteApplicantWithEmailEnding() {

        String deleteStatement = "DELETE FROM " + ApplicantsEntry.TABLE_NAME +
                " WHERE " + ApplicantsEntry.COLUMN_EMAIL + " LIKE " + "'%@mauriseu.net';" ;
        return update(deleteStatement);
    }

    public List<Entry> getAllApplicants() {

        String statement = "SELECT * FROM " + ApplicantsEntry.TABLE_NAME + ";" ;

        List<Entry> applicants = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                applicants.add(new Applicant(
                        resultSet.getInt(ApplicantsEntry.COLUMN_ID),
                        resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME),
                        resultSet.getString(ApplicantsEntry.COLUMN_LAST_NAME),
                        resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER),
                        resultSet.getString(ApplicantsEntry.COLUMN_EMAIL),
                        resultSet.getInt(ApplicantsEntry.COLUMN_APPLICATION_CODE)));
        } catch (SQLException e) {
            System.out.println("Error reading nick name column from mentor");
        } finally {
            closeConnection();
        }
        return applicants;
    }

    public boolean addApplicant(Applicant applicant) {

        String insertStatement = createAddingApplicantStatement(applicant);
        return update(insertStatement);
    }

    public Applicant getApplicantById(int id) {

        Applicant applicant = null;
        String selectStatement = "SELECT * FROM " + ApplicantsEntry.TABLE_NAME +
                " WHERE " + ApplicantsEntry.COLUMN_ID + " = " + id + ";" ;

        try {
            ResultSet resultSet = query(selectStatement);
            while (resultSet.next())
                applicant = new Applicant(
                        resultSet.getInt(ApplicantsEntry.COLUMN_ID),
                        resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME),
                        resultSet.getString(ApplicantsEntry.COLUMN_LAST_NAME),
                        resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER),
                        resultSet.getString(ApplicantsEntry.COLUMN_EMAIL),
                        resultSet.getInt(ApplicantsEntry.COLUMN_APPLICATION_CODE));
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return applicant;
    }

    public boolean updateApplicantById(Applicant applicant) {

        String updateStatement = createUpdateStatement(applicant);
        return update(updateStatement);
    }

    private String createUpdateStatement(Applicant applicant) {

        String updateStatement = "UPDATE " + ApplicantsEntry.TABLE_NAME + " SET ";
        if (!applicant.getFirstName().equals("0"))
            updateStatement += ApplicantsEntry.COLUMN_FIRST_NAME + " = '" + applicant.getFirstName() + "',";
        if (!applicant.getLastName().equals("0"))
            updateStatement += ApplicantsEntry.COLUMN_LAST_NAME + " = '" + applicant.getLastName() + "',";
        if (!applicant.getPhoneNumber().equals("0"))
            updateStatement += ApplicantsEntry.COLUMN_PHONE_NUMBER + " = '" + applicant.getPhoneNumber() + "',";
        if (!applicant.getEmail().equals("0"))
            updateStatement += ApplicantsEntry.COLUMN_EMAIL + " = '" + applicant.getEmail() + "',";
        updateStatement += ApplicantsEntry.COLUMN_APPLICATION_CODE + " = " + applicant.getApplicationCode() +
                " WHERE " + ApplicantsEntry.COLUMN_ID + " = " + applicant.getId() + ";";
        return updateStatement;
    }

    public List<Applicant> getApplicantsByPhrase(String searchPhrase) {

        String selectSqlStatement = createSelectApplicantsByPhraseStatement(searchPhrase);

        List<Applicant> applicants = new ArrayList<>();
        try {
            ResultSet resultSet = query(selectSqlStatement);
            while (resultSet.next())
                applicants.add(new Applicant(
                        resultSet.getInt(ApplicantsEntry.COLUMN_ID),
                        resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME),
                        resultSet.getString(ApplicantsEntry.COLUMN_LAST_NAME),
                        resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER),
                        resultSet.getString(ApplicantsEntry.COLUMN_EMAIL),
                        resultSet.getInt(ApplicantsEntry.COLUMN_APPLICATION_CODE)));
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return applicants;
    }

    private String createSelectApplicantsByPhraseStatement(String searchPhrase) {

        return "SELECT * FROM " + ApplicantsEntry.TABLE_NAME +
                " WHERE " +
                ApplicantsEntry.COLUMN_FIRST_NAME + " LIKE '%" + searchPhrase + "%' OR " +
                ApplicantsEntry.COLUMN_LAST_NAME + " LIKE '%" + searchPhrase + "%' OR " +
                ApplicantsEntry.COLUMN_PHONE_NUMBER + " LIKE '%" + searchPhrase + "%' OR " +
                ApplicantsEntry.COLUMN_EMAIL + " LIKE '%" + searchPhrase + "%' OR " +
                ApplicantsEntry.COLUMN_APPLICATION_CODE + " LIKE '%" + searchPhrase + "%' " +
                ";" ;
    }

}
