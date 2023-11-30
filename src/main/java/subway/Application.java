package subway;

import java.util.Scanner;
import subway.configuration.ApplicationConfiguration;
import subway.controller.Controller;
import subway.controller.InitController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        InitController initController = new InitController();
        initController.process();

        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        Controller controller = applicationConfiguration.controller(scanner);

        controller.process();
    }
}
