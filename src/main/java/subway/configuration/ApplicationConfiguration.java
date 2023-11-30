package subway.configuration;

import java.util.Scanner;
import subway.controller.Controller;
import subway.service.SubwayService;
import subway.service.path.MinimumTimePathFinder;
import subway.service.path.ShortestDistancePathFinder;
import subway.view.input.InputView;
import subway.view.output.OutputView;

public class ApplicationConfiguration {

    public Controller controller(Scanner scanner) {
        return new Controller(inputView(scanner), outputView(), subwayService());
    }

    private InputView inputView(Scanner scanner) {
        return new InputView(scanner);
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private SubwayService subwayService() {
        return new SubwayService(shortestDistanceService(), minimumTimeService());
    }

    private ShortestDistancePathFinder shortestDistanceService() {
        return new ShortestDistancePathFinder();
    }

    private MinimumTimePathFinder minimumTimeService() {
        return new MinimumTimePathFinder();
    }
}
