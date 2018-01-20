package controllers;

import data.*;
import models.*;
import views.*;

import java.util.*;

public class ApplicationProcessController {

    private ApplicationProcessView applicationProcessView;
    private MentorsDbHelper mentorsDbHelper;
    private ApplicantsDbHelper applicantsDbHelper;
    private InputGetter inputGetter;

    public ApplicationProcessController() {
        this.applicationProcessView = new ApplicationProcessView();
        this.mentorsDbHelper = new MentorsDbHelper();
        this.applicantsDbHelper = new ApplicantsDbHelper();
        this.inputGetter = new InputGetter();
    }

    public void start() {
        boolean isAppRunning = true;
        while (isAppRunning) {
            applicationProcessView.displayMenu();
            String userInput = applicationProcessView.getUserInput();
            switch (userInput) {
                case "1":
                    showFirstAndLastNameColumnsFromMentors();
                    break;
                case "2":
                    showNickNamesOfMentorsAtMiskolc();
                    break;
                case "3":
                    showFullNameAndPhoneNumberOfApplicantsWithNameCarol();
                    break;
                case "4":
                    showFullNameAndPhoneNumberOfApplicantsWithDomain();
                    break;
                case "5":
                    addApplicantAndShowHisData();
                    break;
                case "6":
                    updateApplicantAndShowHisPhoneNumber();
                    break;
                case "7":
                    deleteApplicantsWithEmailEnding();
                    break;
                case "8":
                    listAllMentors();
                    break;
                case "9":
                    listAllApplicants();
                    break;
                case "10":
                    addMentor();
                    break;
                case "11":
                    updateMentor();
                    break;
                case "12":
                    addApplicant();
                    break;
                case "13":
                    updateApplicant();
                    break;
                case "14":
                    advancedSearch();
                    break;
                case "0":
                    isAppRunning = false;
                    break;
                default:
                    applicationProcessView.displayWrongInputMessage();
            }
        }
    }

    private void showFirstAndLastNameColumnsFromMentors() {

        List<String> results = mentorsDbHelper.getFirstNameAndLastNameColumn();
        applicationProcessView.displayResults(results);
    }

    private void showNickNamesOfMentorsAtMiskolc() {

        List<String> results = mentorsDbHelper.getNickNamesOfMentorsAtMiskolc();
        applicationProcessView.displayResults(results);
    }

    private void showFullNameAndPhoneNumberOfApplicantsWithNameCarol() {

        List<String> results = applicantsDbHelper.getFullNameAndPhoneNumberWithNameCarol();
        applicationProcessView.displayResults(results);
    }

    private void showFullNameAndPhoneNumberOfApplicantsWithDomain() {

        List<String> results = applicantsDbHelper.getFullNameAndPhoneNumberWithDomain();
        applicationProcessView.displayResults(results);
    }

    private void addApplicantAndShowHisData() {

        Applicant applicant = new Applicant("Markus", "Schaffarzyk", "003620/725-2666",
                "djnovus@groovecoverage.com", 54823);
        List<String> results = applicantsDbHelper.addApplicantAndGetHisData(applicant);
        applicationProcessView.displayResults(results);
    }

    private void updateApplicantAndShowHisPhoneNumber() {

        List<String> results = applicantsDbHelper.updateApplicantAndGetPhoneNumber();
        applicationProcessView.displayResults(results);
    }

    private void deleteApplicantsWithEmailEnding() {

        if (applicantsDbHelper.deleteApplicantWithEmailEnding())
            applicationProcessView.displaySuccessfullyDeleted();
    }

    private void listAllMentors() {
        applicationProcessView.displayEntries(mentorsDbHelper.getAllMentors());
    }

    private void listAllApplicants() {
        List<Entry> entries = new ArrayList<>(applicantsDbHelper.getAllApplicants());
        applicationProcessView.displayEntries(entries);
    }

    private void addMentor() {

        String firstName = inputGetter.getStringUserInput("Enter first name: ");
        String lastName = inputGetter.getStringUserInput("Enter last name: ");
        String nickName = inputGetter.getStringUserInput("Enter nick name: ");
        String phoneNumber = inputGetter.getStringUserInput("Enter phone number: ");
        String email = inputGetter.getStringUserInput("Enter email: ");
        String city = inputGetter.getStringUserInput("Enter city: ");
        int favouriteNumber = inputGetter.getIntUserInput("Enter favourite number: ");
        boolean isAdded = mentorsDbHelper.addMentor(new Mentor(firstName, lastName, nickName,
                phoneNumber, email, city, favouriteNumber));
        if (isAdded) {
            applicationProcessView.displaySuccessfullyAdded();
        }
    }

    private void addApplicant() {

        String firstName = inputGetter.getStringUserInput("Enter first name: ");
        String lastName = inputGetter.getStringUserInput("Enter last name: ");
        String phoneNumber = inputGetter.getStringUserInput("Enter phone number: ");
        String email = inputGetter.getStringUserInput("Enter email: ");
        int applicationCode = inputGetter.getIntUserInput("Enter application code: ");
        boolean isAdded = applicantsDbHelper.addApplicant(new Applicant(firstName, lastName,
                phoneNumber, email, applicationCode));
        if (isAdded) {
            applicationProcessView.displaySuccessfullyAdded();
        }
    }

    private void updateMentor() {

        int id = inputGetter.getIntUserInput("Enter mentor ID: ");
        if (mentorsDbHelper.getMentorById(id) != null) {
            String firstName = inputGetter.getStringUserInput("Enter first name (or 0 to skip): ");
            String lastName = inputGetter.getStringUserInput("Enter last name (or 0 to skip): ");
            String nickName = inputGetter.getStringUserInput("Enter nick name (or 0 to skip): ");
            String phoneNumber = inputGetter.getStringUserInput("Enter phone number (or 0 to skip): ");
            String email = inputGetter.getStringUserInput("Enter email (or 0 to skip): ");
            String city = inputGetter.getStringUserInput("Enter city (or 0 to skip): ");
            int favouriteNumber = inputGetter.getIntUserInput("Enter favourite number: ");
            boolean isUpdated = mentorsDbHelper.updateMentorById(new Mentor(id, firstName, lastName, nickName,
                                                                 phoneNumber, email, city, favouriteNumber));
            if (isUpdated) {
                applicationProcessView.displaySuccessfullyUpdated();
            }
        } else {
            applicationProcessView.displayEntryNotExistsMessage();
        }
    }

    private void updateApplicant() {

        int id = inputGetter.getIntUserInput("Enter applicant ID: ");
        if (applicantsDbHelper.getApplicantById(id) != null) {
            String firstName = inputGetter.getStringUserInput("Enter first name (or 0 to skip): ");
            String lastName = inputGetter.getStringUserInput("Enter last name (or 0 to skip): ");
            String phoneNumber = inputGetter.getStringUserInput("Enter phone number (or 0 to skip): ");
            String email = inputGetter.getStringUserInput("Enter email (or 0 to skip): ");
            int applicationCode = inputGetter.getIntUserInput("Enter application code: ");
            boolean isUpdated = applicantsDbHelper.updateApplicantById(new Applicant(id, firstName, lastName,
                    phoneNumber, email, applicationCode));
            if (isUpdated) {
                applicationProcessView.displaySuccessfullyUpdated();
            }
        } else {
            applicationProcessView.displayEntryNotExistsMessage();
        }
    }

    private void advancedSearch() {

        List<Entry> entries = new ArrayList<>();
        String searchPhrase = inputGetter.getStringUserInput("Enter search phrase to search through database: ");
        entries.addAll(mentorsDbHelper.getMentorsByPhrase(searchPhrase));
        entries.addAll(applicantsDbHelper.getApplicantsByPhrase(searchPhrase));
        applicationProcessView.displayEntries(entries);
    }
}


