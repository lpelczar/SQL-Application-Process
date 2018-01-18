package controllers;

import views.ApplicationProcessView;

public class ApplicationProcessController {

    private ApplicationProcessView applicationProcessView;

    public ApplicationProcessController() {
        this.applicationProcessView = new ApplicationProcessView();
    }

    public void start() {
        boolean isAppRunning = true;

        while (isAppRunning) {
            applicationProcessView.displayMenu();
            String userInput = applicationProcessView.getUserInput();
            switch (userInput) {
                case "1":
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

}
