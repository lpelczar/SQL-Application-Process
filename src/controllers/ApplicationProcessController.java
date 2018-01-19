package controllers;

import data.ApplicantsDbHelper;
import data.MentorsDbHelper;
import models.Applicant;
import models.Mentor;
import services.IntegerChecker;
import views.ApplicationProcessView;

import java.util.List;

public class ApplicationProcessController {

    private ApplicationProcessView applicationProcessView;
    private MentorsDbHelper mentorsDbHelper;
    private ApplicantsDbHelper applicantsDbHelper;

    public ApplicationProcessController() {
        this.applicationProcessView = new ApplicationProcessView();
        this.mentorsDbHelper = new MentorsDbHelper();
        this.applicantsDbHelper = new ApplicantsDbHelper();
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
        List<String> results = applicantsDbHelper.addApplicant(applicant);
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
        applicationProcessView.displayMentors(mentorsDbHelper.getAllMentors());
    }

    private void listAllApplicants() {
        applicationProcessView.displayApplicants(applicantsDbHelper.getAllApplicants());
    }

    private void addMentor() {

        String firstName = getStringUserInput("Enter first name:");
        String lastName = getStringUserInput("Enter last name:");
        String nickName = getStringUserInput("Enter nick name:");
        String phoneNumber = getStringUserInput("Enter phone number:");
        String email = getStringUserInput("Enter email:");
        String city = getStringUserInput("Enter city:");
        int favouriteNumber = getIntUserInput("Enter favourite number:");
        boolean isAdded = mentorsDbHelper.addMentor(new Mentor(firstName, lastName, nickName,
                phoneNumber, email, city, favouriteNumber));
        if (isAdded) {
            applicationProcessView.displaySuccessfullyAdded();
        }
    }

    private String getStringUserInput(String message) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            applicationProcessView.displayMessage(message);
            input = applicationProcessView.getStringInput();
            if (input.length() > 1) {
                isCorrectInput = true;
            }
        }
        return input;
    }

    private int getIntUserInput(String message) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            System.out.print(message);
            input = applicationProcessView.getStringInput();
            if (input.length() > 0 && IntegerChecker.isInteger(input)) {
                isCorrectInput = true;
            }
        }
        return Integer.parseInt(input);
    }
}


