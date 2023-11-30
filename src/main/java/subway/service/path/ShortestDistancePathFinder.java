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

public class ShortestDistancePathFinder {

    private static final Map<Station, List<StationAndWeight>> adjacentMap;
    private static final int INIT_MAX_DISTANCE = 987654321;

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
        Map<Station, Integer> minDistances = initMinDistances(startStation);
        Map<Station, Integer> times = initTimes();

        PriorityQueue<StationAndWeight> pq = new PriorityQueue<>((sw1, sw2) -> sw1.distance() - sw2.distance());
        pq.add(new StationAndWeight(startStation, 0, 0));

        while (!pq.isEmpty()) {
            StationAndWeight current = pq.poll();
            addPriorityQueueAboutShortestPath(current, minDistances, times, path, pq);
        }
        int minDistance = minDistances.get(endStation);
        int time = times.get(endStation);
        validateConnective(minDistance);
        return List.of(minDistance, time);
    }

    private static Map<Station, Integer> initMinDistances(Station startStation) {
        Map<Station, Integer> minCost = new HashMap<>();
        for (Station station : adjacentMap.keySet()) {
            minCost.put(station, INIT_MAX_DISTANCE);
        }
        minCost.put(startStation, 0);
        return minCost;
    }

    private static Map<Station, Integer> initTimes() {
        Map<Station, Integer> minCost = new HashMap<>();
        for (Station station : adjacentMap.keySet()) {
            minCost.put(station, 0);
        }
        return minCost;
    }

    private static void addPriorityQueueAboutShortestPath(StationAndWeight current,
                                                          Map<Station, Integer> minCost,
                                                          Map<Station, Integer> times,
                                                          Map<Station, Station> path,
                                                          PriorityQueue<StationAndWeight> pq
    ) {
        for (StationAndWeight next : adjacentMap.get(current.station())) {

            int currentTotalDistance = current.distance();
            int nextDistance = next.distance();
            Station nextStation = next.station();
            if (currentTotalDistance + nextDistance < minCost.get(nextStation)) {
                updateMinCostAndDistance(current, minCost, times, path, next, nextStation);
                addPriorityQueue(current, minCost, pq, next);
            }
        }
    }

    private static void updateMinCostAndDistance(StationAndWeight current, Map<Station, Integer> minCost,
                                                 Map<Station, Integer> times, Map<Station, Station> path,
                                                 StationAndWeight next, Station nextStation) {
        minCost.put(nextStation, current.distance() + next.distance());
        times.put(nextStation, current.time() + next.time());
        path.put(nextStation, current.station());
    }

    private static void addPriorityQueue(StationAndWeight current, Map<Station, Integer> minCost,
                                         PriorityQueue<StationAndWeight> pq, StationAndWeight next) {
        pq.add(new StationAndWeight(next.station(),
                minCost.get(next.station()),
                current.time() + next.time()));
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
        if (minCostResult == INIT_MAX_DISTANCE) {
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 연결되어 있지 않습니다.");
        }
    }
}
