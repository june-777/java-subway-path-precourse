package subway.domain.subway.line;

import java.util.List;
import subway.domain.subway.station.AdjacentStation;
import subway.domain.subway.station.StationInformation;

public enum LineInformation {
    LINE_2("2호선", List.of(
            new AdjacentStation(StationInformation.GYODAE, StationInformation.GANGNAM, 2, 3),
            new AdjacentStation(StationInformation.GANGNAM, StationInformation.YEOKSAM, 2, 3)
    )),
    LINE_3("3호선", List.of(
            new AdjacentStation(StationInformation.GYODAE, StationInformation.NAMBU_TERMINAL, 3, 2),
            new AdjacentStation(StationInformation.NAMBU_TERMINAL, StationInformation.YANGJAE, 6, 5),
            new AdjacentStation(StationInformation.YANGJAE, StationInformation.MAEBONG, 1, 1)
    )),
    NEW_BUNDANG("신분당선", List.of(
            new AdjacentStation(StationInformation.GANGNAM, StationInformation.YANGJAE, 2, 8),
            new AdjacentStation(StationInformation.YANGJAE, StationInformation.YANGJAE_CITIZEN_FOREST, 10, 3)
    ));

    private final String name;
    private final List<AdjacentStation> stations;

    LineInformation(String name, List<AdjacentStation> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public List<AdjacentStation> getStations() {
        return stations;
    }
}
