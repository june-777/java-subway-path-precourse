package subway.controller;

import subway.controller.constants.ApplicationStatus;
import subway.view.InputView;

public class FrontController {

    private final InputView inputView;
    private final Controller controller;

    public FrontController(InputView inputView, Controller controller) {
        this.inputView = inputView;
        this.controller = controller;
    }

    public void process() {
        while (true) {
            try {
                ApplicationStatus applicationStatus = initApplicationStatus();
                if (applicationStatus == ApplicationStatus.QUIT) {
                    break;
                }

                controller.process();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private ApplicationStatus initApplicationStatus() {
        String applicationStatus = inputView.readApplicationStatus();
        return ApplicationStatus.of(applicationStatus);
    }
}
