package data;

import data.ApplicantsContract.ApplicantsEntry;

import java.sql.*;
import java.util.*;

public class ApplicantsDbHelper extends DbHelper {

    public List<String> getFullNameAndPhoneNumberOfWithNameCarol() {

        String statement = "SELECT first_name || \" \" || last_name AS full_name, phone_number\n" +
                           "FROM applicants\n" +
                           "WHERE first_name = \"Carol\";";
        List<String> results = new ArrayList<>();

        openConnection();
        try {
            ResultSet resultSet = readData(statement);
            while (resultSet.next())
                results.add(resultSet.getString("full_name") + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER));
        } catch (SQLException e) {
            System.out.println("Error reading nick name column from mentor");
        } finally {
            closeConnection();
        }
        return results;
    }


}
