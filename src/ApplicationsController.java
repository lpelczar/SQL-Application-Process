public class ApplicationsController {

    public void start() {
        boolean isAppRunning = true;

        while (isAppRunning) {
            rootView.displayMenu();
            String userInput = rootView.getUserInput();
            switch (userInput) {
                case "1":
                    break;
                case "2":
                    break;
                case "0":
                    isAppRunning = false;
                    break;
                default:
                    rootView.displayWrongInputMessage();
            }
        }
    }

}
