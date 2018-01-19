package data;

import data.ApplicantsContract.ApplicantsEntry;

import java.sql.*;
import java.util.*;

public class ApplicantsDbHelper extends DbHelper {

    public List<String> getFullNameAndPhoneNumberOfWithNameCarol() {

        String statement = "WHERE first_name = \"Carol\";";
        return getFullNameAndPhoneNumberOfApplicant(statement);
    }

    private List<String> getFullNameAndPhoneNumberOfApplicant(String whereStatement) {

        String statement = "SELECT first_name || \" \" || last_name AS full_name, phone_number\n" +
                           "FROM applicants\n" + whereStatement;
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            ResultSet resultSet = readData(statement);
            while (resultSet.next())
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_FULL_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER));
        } catch (SQLException e) {
            System.out.println("Error reading nick name column from mentor");
        } finally {
            closeConnection();
        }
        return results;
    }


}
