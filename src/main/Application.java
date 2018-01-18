package main;

import controllers.ApplicationProcessController;

public class Application {

    public static void main(String... args) {

        ApplicationProcessController controller = new ApplicationProcessController();
        controller.start();
    }
}
