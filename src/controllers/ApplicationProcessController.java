public class ApplicationsController {

    private ApplicationsView applicationsView;

    ApplicationsController() {
        this.applicationsView = new ApplicationsView();
    }

    public void start() {
        boolean isAppRunning = true;

        while (isAppRunning) {
            applicationsView.displayMenu();
            String userInput = applicationsView.getUserInput();
            switch (userInput) {
                case "1":
                    break;
                case "2":
                    break;
                case "0":
                    isAppRunning = false;
                    break;
                default:
                    applicationsView.displayWrongInputMessage();
            }
        }
    }

}
