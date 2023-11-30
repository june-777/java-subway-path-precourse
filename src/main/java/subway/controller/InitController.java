package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.constant.LineInformation;
import subway.domain.constant.StationInformation;

public class InitController {

    public void process() {
        saveStations();
        saveLines();
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
}
