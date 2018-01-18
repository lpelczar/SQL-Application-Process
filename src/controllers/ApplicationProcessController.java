package controllers;

import data.ApplicantsDbHelper;
import data.MentorsDbHelper;
import views.ApplicationProcessView;

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
                    createDatabase();
                    break;
                case "2":
                    break;
                case "0":
                    isAppRunning = false;
                    break;
                default:
                    applicationProcessView.displayWrongInputMessage();
            }
        }
    }

    private void createDatabase() {
        mentorsDbHelper.create();
    }

}
