package data;

import data.ApplicantsContract.ApplicantsEntry;
import models.*;
import java.sql.*;
import java.util.*;

public class ApplicantsDbHelper extends DbHelper {

    private ApplicantsStatementCreator applicantsStatementCreator = new ApplicantsStatementCreator();

    public List<String> getFullNameAndPhoneNumberWithNameCarol() {

        String statement = applicantsStatementCreator.whereFirstNameEqualsCarolStatement();
        return getFullNameAndPhoneNumberOfApplicant(statement);
    }

    public List<String> getFullNameAndPhoneNumberWithDomain() {

        String statement = applicantsStatementCreator.whereEmailLikeStatement();
        return getFullNameAndPhoneNumberOfApplicant(statement);
    }

    private List<String> getFullNameAndPhoneNumberOfApplicant(String whereStatement) {

        String statement = applicantsStatementCreator.selectFullNameAndPhoneNumberStatement(whereStatement);

        List<String> results = new ArrayList<>();
        try {
            ResultSet resultSet = query(statement);
            while (resultSet.next())
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_FULL_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER));
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<String> addApplicantAndGetHisData(Applicant applicant) {

        String statement = applicantsStatementCreator.insertApplicantStatement(applicant);
        String selectStatement = applicantsStatementCreator.selectApplicantByApplicationCodeStatement(
                applicant.getApplicationCode());

        List<String> results = new ArrayList<>();
        try {
            update(statement);
            closeConnection();
            ResultSet resultSet = query(selectStatement);
            while (resultSet.next()) {
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_ID) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_FIRST_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_LAST_NAME) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_EMAIL) + " " +
                            resultSet.getString(ApplicantsEntry.COLUMN_APPLICATION_CODE));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return results;
    }

    public List<String> updateApplicantAndGetPhoneNumber() {

        String statement = applicantsStatementCreator.updateJemimaForemanStatement();
        String selectStatement = applicantsStatementCreator.selectPhoneNumberOfJemimaForeman();

        List<String> results = new ArrayList<>();
        try {
            update(statement);
            closeConnection();
            ResultSet resultSet = query(selectStatement);
            while (resultSet.next()) {
                results.add(resultSet.getString(ApplicantsEntry.COLUMN_PHONE_NUMBER));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return results;
    }

    public boolean deleteApplicantWithEmailEnding() {

        String deleteStatement = applicantsStatementCreator.deleteWithEmailEndingStatement();
        return update(deleteStatement);
    }

    public List<Applicant> getAllApplicants() {

        String statement = applicantsStatementCreator.selectAllApplicantsStatement();

        List<Applicant> applicants = new ArrayList<>();
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
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return applicants;
    }

    public boolean addApplicant(Applicant applicant) {

        String insertStatement = applicantsStatementCreator.insertApplicantStatement(applicant);
        return update(insertStatement);
    }

    public Applicant getApplicantById(int id) {

        String statement = applicantsStatementCreator.selectApplicantByIdStatement(id);

        Applicant applicant = null;
        try {
            ResultSet resultSet = query(statement);
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

        String updateStatement = applicantsStatementCreator.updateApplicantStatement(applicant);
        return update(updateStatement);
    }

    public List<Applicant> getApplicantsByPhrase(String searchPhrase) {

        String statement = applicantsStatementCreator.selectApplicantsByPhraseStatement(
                searchPhrase);

        List<Applicant> applicants = new ArrayList<>();
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
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return applicants;
    }
}
