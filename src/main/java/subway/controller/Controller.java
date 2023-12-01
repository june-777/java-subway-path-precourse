package subway.controller;

import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.SubwayServiceStatus;
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

        SubwayServiceStatus subwayServiceStatus = initSubwayServiceStatus();
        if (subwayServiceStatus == SubwayServiceStatus.BACK) {
            return;
        }

        Station startStation = initStartStation();
        Station destinationStation = initDestinationStation();

        subwayService.service(startStation, destinationStation);
        // TODO: 서비스 결과를 출력
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
