package subway.domain.constant;

import java.util.List;

public enum LineInformation {
    TWO("2호선",
            List.of(StationInformation.GYODAE,
                    StationInformation.GANGNAM,
                    StationInformation.YEOKSAM)
    ),
    THREE("3호선",
            List.of(StationInformation.GYODAE,
                    StationInformation.NAMBU_TERMINAL,
                    StationInformation.YANGJAE)
    ),
    NEW_BOONDANG("신분당선",
            List.of(StationInformation.GANGNAM,
                    StationInformation.YANGJAE,
                    StationInformation.YANGJAE_CITIZEN_FOREST)
    );

    private final String name;
    private final List<StationInformation> stations;

    LineInformation(String name, List<StationInformation> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public List<StationInformation> getStations() {
        return stations;
    }
}
