package subway.controller;

import subway.controller.constants.ApplicationStatus;
import subway.controller.constants.SubwayServiceStatus;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.service.SubwayService;
import subway.view.InputView;

public class Controller {

    private final InputView inputView;
    private final SubwayService subwayService;

    public Controller(InputView inputView, SubwayService subwayService) {
        this.inputView = inputView;
        this.subwayService = subwayService;
    }

    public void process() {
        while (true) {
            try {
                ApplicationStatus applicationStatus = initApplicationStatus();
                if (applicationStatus == ApplicationStatus.QUIT) {
                    break;
                }

                /**
                 * 서비스 시작
                 * 1. B 입력
                 * 2. 모든 서비스 성공
                 * -> 이 외의 상황에선 이전 while(true) ~ try ~ 문으로 돌아갈 수 없다 !
                 * -> 예외 발생도 마찬가지로 돌아갈 수 없다.
                 * -> while(true) ~ try ~ catch 기능을 앞 단으로 뺄 수 있을 것임
                 */
                while (true) {
                    try {
                        SubwayServiceStatus subwayServiceStatus = initSubwayServiceStatus();
                        if (subwayServiceStatus == SubwayServiceStatus.BACK) {
                            break;
                        }

                        Station startStation = initStartStation();
                        Station destinationStation = initDestinationStation();
                        subwayService.service(startStation, destinationStation);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("ERROR] " + e.getMessage());
                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private ApplicationStatus initApplicationStatus() {
        String applicationStatus = inputView.readApplicationStatus();
        return ApplicationStatus.of(applicationStatus);
    }

    private SubwayServiceStatus initSubwayServiceStatus() {
        String subwayServiceCommand = inputView.readSubwayServiceCommand();
        return SubwayServiceStatus.of(subwayServiceCommand);
    }

    private Station initStartStation() {
        String startStation = inputView.readStartStation();
        return StationRepository.findBy(startStation);
    }

    private Station initDestinationStation() {
        String destinationStation = inputView.readDestinationStation();
        return StationRepository.findBy(destinationStation);
    }
}
