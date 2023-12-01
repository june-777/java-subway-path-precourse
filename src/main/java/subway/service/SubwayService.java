package subway.service;

import subway.domain.Station;

public class SubwayService {

    public void service(final Station start, final Station destination) {
        validateSameStation(start, destination);
    }

    private void validateSameStation(Station start, Station destination) {
        if (start.equals(destination)) {
            throw new IllegalArgumentException("출발역과 목적지역이 같습니다.");
        }
    }
}
