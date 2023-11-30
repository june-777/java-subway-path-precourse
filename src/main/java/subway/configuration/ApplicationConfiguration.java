package subway.configuration;

import java.util.Scanner;
import subway.controller.FrontController;
import subway.controller.SubwayController;
import subway.service.SubwayService;
import subway.service.path.MinimumTimePathFinder;
import subway.service.path.ShortestDistancePathFinder;
import subway.view.input.InputView;
import subway.view.output.OutputView;

public class ApplicationConfiguration {

    private final Scanner scanner;
    private final InputView inputView;
    private final OutputView outputView;

    public ApplicationConfiguration(Scanner scanner) {
        this.scanner = scanner;
        this.inputView = inputView();
        this.outputView = outputView();
    }

    public FrontController controller() {
        return new FrontController(inputView, subwayController());
    }

    private SubwayController subwayController() {
        return new SubwayController(inputView, outputView, subwayService());
    }

    private SubwayService subwayService() {
        return new SubwayService(shortestDistancePathFinder(), minimumTimePathFinder());
    }

    private ShortestDistancePathFinder shortestDistancePathFinder() {
        return new ShortestDistancePathFinder();
    }

    private MinimumTimePathFinder minimumTimePathFinder() {
        return new MinimumTimePathFinder();
    }

    private InputView inputView() {
        return new InputView(scanner);
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
