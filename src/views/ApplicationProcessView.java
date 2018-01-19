package views;

import java.util.*;

public class ApplicationProcessView {

    public void displayMenu() {

        clearConsole();
        System.out.println("Welcome to application process app!" +
                "\nWhat do you want to do?\n" +
                " 1. Get first_name, last_name columns from mentors table\n" +
                " 2. Get the nick_name-s of all mentors working at Miskolc\n" +
                " 3. Show full name and phone number of all applicants with name Carol\n" +
                " 4. Show full name and phone number of all applicants with name Carol\n" +
                " 0. Exit");
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String getUserInput() {
        System.out.print("Choose option: ");
        return getStringInput();
    }

    private String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayWrongInputMessage() {
        System.out.println("Wrong input!");
    }

    public void displayResults(List<String> results) {
        System.out.println("\nResults:");
        for (String s : results) {
            System.out.println(s);
        }
        displayPressAnyKeyToContinueMessage();
    }

    private void displayPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
