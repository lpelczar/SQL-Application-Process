package data;

import data.ApplicantsContract.ApplicantsEntry;
import models.Applicant;

import java.sql.*;
import java.util.*;

public class ApplicantsDbHelper extends DbHelper {

    private final String tableName = "applicants";

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
                           "FROM " + tableName + " " + whereStatement;
        List<String> results = new ArrayList<>();

        openConnection();
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

    public List<String> addApplicant(Applicant applicant) {

        String sqlStatement = createAddingApplicantStatement(applicant);
        String selectSqlStatement = "SELECT * FROM " + tableName + " WHERE " +
                ApplicantsEntry.COLUMN_APPLICATION_CODE + " = " + applicant.getApplicationCode() + ";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            update(sqlStatement);
            ResultSet resultSet = query(selectSqlStatement);
            while (resultSet.next()) {
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME) + " " +
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

        return "INSERT INTO " + tableName + " (" +
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

        String sqlStatement = "UPDATE " + tableName +
                " SET " + ApplicantsEntry.COLUMN_PHONE_NUMBER + " = '003670/223-7459'" +
                " WHERE " + ApplicantsEntry.COLUMN_FIRST_NAME + " = 'Jemima' AND " +
                ApplicantsEntry.COLUMN_LAST_NAME + " = 'Foreman';" ;
        String selectSqlStatement = "SELECT " + ApplicantsEntry.COLUMN_PHONE_NUMBER + " FROM " + tableName +
                " WHERE " + ApplicantsEntry.COLUMN_FIRST_NAME + " = 'Jemima' AND " +
                ApplicantsEntry.COLUMN_LAST_NAME + " = 'Foreman';" ;
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            update(sqlStatement);
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


}
