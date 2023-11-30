package subway.domain.subway.station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacentStationRepository {
    private static final Map<Station, List<StationAndWeight>> adjacentStationRepository = new HashMap<>();

    public static void initAdjacentStationRepository(final List<Station> stations) {
        for (Station station : stations) {
            adjacentStationRepository.put(station, new ArrayList<>());
        }
    }

    public static Map<Station, List<StationAndWeight>> adjacentStations() {
        return Collections.unmodifiableMap(adjacentStationRepository);
    }

    public static void addAdjacentStation(Station start, Station end, int distance, int time) {
        if (!adjacentStationRepository.containsKey(start)) {
            throw new IllegalArgumentException("[ERROR] 저장되지 않은 지하철 역 정보입니다.");
        }

        adjacentStationRepository.get(start)
                .add(new StationAndWeight(end, distance, time));

        adjacentStationRepository.get(end)
                .add(new StationAndWeight(start, distance, time));
    }

}
