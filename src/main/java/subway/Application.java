package subway;

import java.util.Scanner;
import subway.configuration.ApplicationConfiguration;
import subway.controller.FrontController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(scanner);
        FrontController controller = applicationConfiguration.frontController();

        controller.process();
    }
}
