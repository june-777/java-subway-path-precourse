package subway.service.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import subway.domain.subway.station.AdjacentStationRepository;
import subway.domain.subway.station.Station;
import subway.domain.subway.station.StationAndWeight;
import subway.domain.subway.station.StationRepository;
import subway.dto.response.SubwayResponse;

public class MinimumTimePathFinder {

    private static final Map<Station, List<StationAndWeight>> adjacentMap;
    private static final int INIT_MAX_TIME = 987654321;

    static {
        adjacentMap = AdjacentStationRepository.adjacentStations();
    }

    public SubwayResponse find(Station startStation, Station endStation) {
        validateExistStation(startStation, endStation);

        Map<Station, Station> path = new HashMap<>();
        List<Integer> results = dijkstra(startStation, endStation, path);
        List<String> pathResults = calculatePath(startStation, endStation, path);
        return new SubwayResponse(results, pathResults);
    }

    private void validateExistStation(Station startStation, Station endStation) {
        StationRepository.find(startStation);
        StationRepository.find(endStation);
    }

    private List<Integer> dijkstra(Station startStation, Station endStation, Map<Station, Station> path) {
        Map<Station, Integer> minTimes = initMinTimes(startStation);
        Map<Station, Integer> distances = initDistances();

        PriorityQueue<StationAndWeight> pq = new PriorityQueue<>((sw1, sw2) -> sw1.time() - sw2.time());
        pq.add(new StationAndWeight(startStation, 0, 0));

        while (!pq.isEmpty()) {
            StationAndWeight current = pq.poll();
            addPriorityQueueAboutMinimumTime(current, minTimes, distances, path, pq);
        }
        int minTime = minTimes.get(endStation);
        int distance = distances.get(endStation);
        validateConnective(minTime);
        return List.of(distance, minTime);
    }

    private static Map<Station, Integer> initMinTimes(Station startStation) {
        Map<Station, Integer> minCost = new HashMap<>();
        for (Station station : adjacentMap.keySet()) {
            minCost.put(station, INIT_MAX_TIME);
        }
        minCost.put(startStation, 0);
        return minCost;
    }

    private static Map<Station, Integer> initDistances() {
        Map<Station, Integer> minCost = new HashMap<>();
        for (Station station : adjacentMap.keySet()) {
            minCost.put(station, 0);
        }
        return minCost;
    }

    private static void addPriorityQueueAboutMinimumTime(StationAndWeight current,
                                                         Map<Station, Integer> minTimes,
                                                         Map<Station, Integer> distances,
                                                         Map<Station, Station> path,
                                                         PriorityQueue<StationAndWeight> pq
    ) {
        for (StationAndWeight next : adjacentMap.get(current.station())) {

            int currentTotalTime = current.time();
            int nextTime = next.time();
            Station nextStation = next.station();
            if (currentTotalTime + nextTime < minTimes.get(nextStation)) {
                updateMinCostAndDistance(current, minTimes, distances, path, next, nextStation);
                addPriorityQueue(current, minTimes, pq, next);
            }
        }
    }

    private static void updateMinCostAndDistance(StationAndWeight current, Map<Station, Integer> minTimes,
                                                 Map<Station, Integer> distances, Map<Station, Station> path,
                                                 StationAndWeight next, Station nextStation) {
        minTimes.put(nextStation, current.time() + next.time());
        distances.put(nextStation, current.distance() + next.distance());
        path.put(nextStation, current.station());
    }

    private static void addPriorityQueue(StationAndWeight current, Map<Station, Integer> minTimes,
                                         PriorityQueue<StationAndWeight> pq, StationAndWeight next) {
        pq.add(new StationAndWeight(next.station(),
                current.distance() + next.distance(),
                minTimes.get(next.station())));
    }

    private List<String> calculatePath(Station startStation, Station endStation, Map<Station, Station> path) {
        Stack<Station> pathRecord = new Stack<>();
        List<String> pathResult = new ArrayList<>();

        Station current = endStation;
        pathRecord.add(current);

        while (!current.equals(startStation)) {
            current = path.get(current);
            pathRecord.add(current);
        }
        while (!pathRecord.isEmpty()) {
            pathResult.add(pathRecord.pop().getName());
        }
        return pathResult;
    }

    private static void validateConnective(int minCostResult) {
        if (minCostResult == INIT_MAX_TIME) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 연결되어 있지 않습니다.");
        }
    }
}
