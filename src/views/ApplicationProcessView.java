package views;

import models.Entry;

import java.util.*;

public class ApplicationProcessView {

    public void displayMenu() {

        clearConsole();
        System.out.println("Welcome to application process app!" +
                "\nWhat do you want to do?\n" +
                " 1. Get first_name, last_name columns from mentors table\n" +
                " 2. Get the nick_name-s of all mentors working at Miskolc\n" +
                " 3. Show full name and phone number of all applicants with name Carol\n" +
                " 4. Show full name and phone number of all applicants with domain adipiscingenimmi.edu\n" +
                " 5. Add Markus Schaffarzyk to applicants and show his data\n" +
                " 6. Change applicant with name Jemima and last name Foreman phone number to 003670/223-7459\n" +
                " 7. Delete all applicants with email ending %@mauriseu.net\n" +
                " 8. List all mentors\n" +
                " 9. List all applicants\n" +
                " 10. Add mentor\n" +
                " 11. Update mentor\n" +
                " 12. Add applicant\n" +
                " 13. Update applicant\n" +
                " 14. Advanced search\n" +
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

    public String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displayWrongInputMessage() {
        System.out.println("Wrong input!");
    }

    public void displayResults(List<String> results) {
        System.out.println("\nResults:");
        if (results.isEmpty()) {
            System.out.println("Empty.");
        } else {
            for (String s : results) {
                System.out.println(s);
            }
        }
        displayPressAnyKeyToContinueMessage();
    }

    private void displayPressAnyKeyToContinueMessage() {
        System.out.print("\nPress any key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void displaySuccessfullyDeleted() {
        System.out.println("Entry successfully deleted!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displaySuccessfullyAdded() {
        System.out.println("Entry successfully added!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displaySuccessfullyUpdated() {
        System.out.println("Entry successfully updated!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayEntryNotExistsMessage() {
        System.out.println("Entry with given ID not exists!");
        displayPressAnyKeyToContinueMessage();
    }

    public void displayEntries(List<Entry> entries) {
        System.out.println("\nResults:");
        if (entries.isEmpty()) {
            System.out.println("Not found!");
        } else {
            for (Entry e : entries) {
                System.out.println(e);
            }
        }
        displayPressAnyKeyToContinueMessage();
    }

}
