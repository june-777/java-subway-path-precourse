package subway.configuration;

import java.util.Scanner;
import subway.controller.Controller;
import subway.controller.FrontController;
import subway.service.SubwayService;
import subway.view.InputView;

public class ApplicationConfiguration {

    private final InputView inputView;
    private final Scanner scanner;

    public ApplicationConfiguration(Scanner scanner) {
        this.scanner = scanner;
        this.inputView = inputView();
    }

    public FrontController frontController() {
        return new FrontController(inputView, controller());
    }

    private Controller controller() {
        return new Controller(inputView, subwayService());
    }

    private InputView inputView() {
        return new InputView(scanner);
    }

    private SubwayService subwayService() {
        return new SubwayService();
    }
}
