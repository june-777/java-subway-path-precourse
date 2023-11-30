package subway;

import java.util.Scanner;
import subway.configuration.ApplicationConfiguration;
import subway.controller.FrontController;
import subway.controller.InitController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        InitController initController = new InitController();
        initController.process();

        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(scanner);
        FrontController frontController = applicationConfiguration.controller();

        frontController.process();
    }
}
