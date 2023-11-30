package subway.controller;

import subway.domain.command.SearchCommand;
import subway.domain.subway.station.Station;
import subway.domain.subway.station.StationRepository;
import subway.dto.response.SubwayResponse;
import subway.service.SubwayService;
import subway.view.input.InputView;
import subway.view.output.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private final SubwayService subwayService;

    public SubwayController(InputView inputView, OutputView outputView, SubwayService subwayService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.subwayService = subwayService;
    }

    public void process() {
        SearchCommand searchCommand = initSearchCommand();
        if (searchCommand == SearchCommand.BACK) {
            return;
        }
        Station startStation = initStartStation();
        Station endStation = initEndStation();
        SubwayResponse subwayResponse = subwayService.service(searchCommand, startStation, endStation);
        outputView.printSearchResult(subwayResponse);
    }

    private SearchCommand initSearchCommand() {
        String searchCommandInput = inputView.readSearchCommand();
        return SearchCommand.getCommand(searchCommandInput);
    }

    private Station initStartStation() {
        String startStationInput = inputView.readStartStation();
        return StationRepository.findByName(startStationInput);
    }

    private Station initEndStation() {
        String endStationInput = inputView.readEndStation();
        return StationRepository.findByName(endStationInput);
    }
}
