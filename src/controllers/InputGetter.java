package controllers;

import services.IntegerChecker;
import views.ApplicationProcessView;

class InputGetter {

    private ApplicationProcessView applicationProcessView = new ApplicationProcessView();

    String getStringUserInput(String message) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            applicationProcessView.displayMessage(message);
            input = applicationProcessView.getStringInput();
            if (input.trim().length() > 0) {
                isCorrectInput = true;
            }
        }
        return input;
    }

    int getIntUserInput(String message) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            System.out.print(message);
            input = applicationProcessView.getStringInput();
            if (input.trim().length() > 0 && IntegerChecker.isInteger(input)) {
                isCorrectInput = true;
            }
        }
        return Integer.parseInt(input);
    }
}
