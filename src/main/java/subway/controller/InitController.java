package subway.controller;

import java.util.List;
import subway.domain.subway.line.Line;
import subway.domain.subway.line.LineInformation;
import subway.domain.subway.line.LineRepository;
import subway.domain.subway.station.AdjacentStation;
import subway.domain.subway.station.AdjacentStationRepository;
import subway.domain.subway.station.Station;
import subway.domain.subway.station.StationInformation;
import subway.domain.subway.station.StationRepository;

public class InitController {

    public void process() {
        saveStations();
        saveLines();
        saveAdjacentStation();
    }

    private void saveStations() {
        for (StationInformation station : StationInformation.values()) {
            String name = station.getKoreanName();
            StationRepository.addStation(new Station(name));
        }
    }

    private void saveLines() {
        for (LineInformation line : LineInformation.values()) {
            String name = line.getName();
            LineRepository.addLine(new Line(name));
        }
    }

    private void saveAdjacentStation() {
        List<Station> allStations = StationRepository.stations();
        AdjacentStationRepository.initAdjacentStationRepository(allStations);
        for (LineInformation lineInformation : LineInformation.values()) {
            saveEachAdjacentStation(lineInformation);
        }
    }

    private static void saveEachAdjacentStation(LineInformation lineInformation) {
        for (AdjacentStation adjacentStation : lineInformation.getStations()) {
            Station startStation = adjacentStation.getStartStation();
            Station endStation = adjacentStation.getEndStation();

            int distance = adjacentStation.getDistance();
            int time = adjacentStation.getTime();

            AdjacentStationRepository.addAdjacentStation(startStation, endStation, distance, time);
        }
    }
}
