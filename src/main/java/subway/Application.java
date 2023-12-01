package subway;

import java.util.Scanner;
import subway.configuration.ApplicationConfiguration;
import subway.controller.Controller;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // TODO: 프로그램 구현
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(scanner);
        Controller controller = applicationConfiguration.controller();

        controller.process();
    }
}
