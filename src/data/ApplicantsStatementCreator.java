package data;

import data.ApplicantsContract.ApplicantsEntry;
import models.Applicant;

public class ApplicantsStatementCreator {

    public String whereFirstNameEqualsCarolStatement() {

        return "WHERE first_name = \"Carol\";";
    }

    public String whereEmailLikeStatement() {

        return "WHERE email LIKE \"%@adipiscingenimmi.edu\";";
    }

    public String selectFullNameAndPhoneNumberStatement(String whereClause) {

        return "SELECT first_name || \" \" || last_name AS full_name, phone_number\n" +
                "FROM " + ApplicantsEntry.TABLE_NAME + " " + whereClause;
    }

    public String insertApplicantStatement(Applicant applicant) {

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

    public String selectApplicantByApplicationCodeStatement(int id) {

        return "SELECT * FROM " + ApplicantsEntry.TABLE_NAME + " WHERE " +
                ApplicantsEntry.COLUMN_APPLICATION_CODE + " = " + id + ";";
    }

    public String updateJemimaForemanStatement() {

        return "UPDATE " + ApplicantsEntry.TABLE_NAME +
                " SET " + ApplicantsEntry.COLUMN_PHONE_NUMBER + " = '003670/223-7459'" +
                " WHERE " + ApplicantsEntry.COLUMN_FIRST_NAME + " = 'Jemima' AND " +
                ApplicantsEntry.COLUMN_LAST_NAME + " = 'Foreman';" ;
    }

    public String selectPhoneNumberOfJemimaForeman() {

        return "SELECT " + ApplicantsEntry.COLUMN_PHONE_NUMBER + " FROM " +
                ApplicantsEntry.TABLE_NAME + " WHERE " + ApplicantsEntry.COLUMN_FIRST_NAME + " = 'Jemima' AND " +
                ApplicantsEntry.COLUMN_LAST_NAME + " = 'Foreman';" ;
    }

    public String deleteWithEmailEndingStatement() {

        return "DELETE FROM " + ApplicantsEntry.TABLE_NAME +
                " WHERE " + ApplicantsEntry.COLUMN_EMAIL + " LIKE " + "'%@mauriseu.net';" ;
    }

    public String selectAllApplicantsStatement() {

        return "SELECT * FROM " + ApplicantsEntry.TABLE_NAME + ";" ;
    }

    public String selectApplicantByIdStatement(int id) {

        return "SELECT * FROM " + ApplicantsEntry.TABLE_NAME +
                " WHERE " + ApplicantsEntry.COLUMN_ID + " = " + id + ";" ;
    }

    public String updateApplicantStatement(Applicant applicant) {

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

    public String selectApplicantsByPhraseStatement(String searchPhrase) {

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
