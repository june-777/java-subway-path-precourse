package subway.service;

import subway.domain.command.SearchCommand;
import subway.domain.subway.station.Station;
import subway.dto.response.SubwayResponse;
import subway.service.path.MinimumTimePathFinder;
import subway.service.path.ShortestDistancePathFinder;

public class SubwayService {

    private final ShortestDistancePathFinder shortestDistancePathFinder;
    private final MinimumTimePathFinder minimumTimePathFinder;

    public SubwayService(ShortestDistancePathFinder shortestDistancePathFinder,
                         MinimumTimePathFinder minimumTimePathFinder
    ) {
        this.shortestDistancePathFinder = shortestDistancePathFinder;
        this.minimumTimePathFinder = minimumTimePathFinder;
    }

    /**
     * @throws: 등록되지 않은 지하철 역
     * @throws: 시작역과 종료역이 같은 경우
     */
    public SubwayResponse service(final SearchCommand searchCommand, final Station start, final Station end) {
        validateDifferentStation(start, end);
        if (searchCommand == SearchCommand.SHORTEST_PATH) {
            return shortestDistancePathFinder.find(start, end);
        }
        return minimumTimePathFinder.find(start, end);
    }

    private void validateDifferentStation(Station start, Station end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다.");
        }
    }

    // TODO: BFS / DIJKSTRA에서 예외 던져야할듯
    private void validateSameLine(Station start, Station end) {

    }
}
