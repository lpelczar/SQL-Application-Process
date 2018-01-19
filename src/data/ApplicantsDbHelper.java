package data;

import data.ApplicantsContract.ApplicantsEntry;

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

    public List<String> addApplicant(String firstName, String lastName, String phoneNumber, String email, int applicationCode) {

        String statement = "INSERT INTO " + tableName + " (" +
                "first_name," + "last_name," + "phone_number," + "email," + "application_code)" +
                "VALUES (" + firstName + "," + lastName + "," + phoneNumber + "," + email + "," + applicationCode +
                ")" + "SELECT * FROM applicants WHERE application_code =" + applicationCode + ";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                results.add(
                        resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME) + " " +
                        resultSet.getString(ApplicantsEntry.COLUMN_LAST_NAME) + " " +
                        resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER) + " " +
                        resultSet.getString(ApplicantsEntry.COLUMN_EMAIL) + " " +
                        resultSet.getString(ApplicantsEntry.COLUMN_APPLICATION_CODE));
        } catch (SQLException e) {
            System.out.println("Error adding applicant");
        } finally {
            closeConnection();
        }
        return results;
    }


}
