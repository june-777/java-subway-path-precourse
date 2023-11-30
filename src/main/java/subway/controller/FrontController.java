package subway.controller;

import subway.controller.constants.ApplicationStatus;
import subway.controller.handler.ExceptionHandler;
import subway.view.input.InputView;

public class FrontController {
    private final InputView inputView;
    private final SubwayController subwayController;

    public FrontController(InputView inputView, SubwayController subwayController) {
        this.inputView = inputView;
        this.subwayController = subwayController;
    }

    public void process() {
        while (ExceptionHandler.handle(this::initMainCommand) == ApplicationStatus.START) {
            startSubway();
        }
    }

    private void startSubway() {
        while (true) {
            try {
                subwayController.process();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private ApplicationStatus initMainCommand() {
        String mainCommandInput = inputView.readMainCommand();
        return ApplicationStatus.getCommand(mainCommandInput);
    }
}
