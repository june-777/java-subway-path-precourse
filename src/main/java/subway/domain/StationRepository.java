package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    // 스켈레톤 예제 코드를 위해, 강남역, 교대역만 레포지토리에 저장
    static {
        stations.add(new Station("강남역"));
        stations.add(new Station("교대역"));
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }

    public static Station findBy(String stationName) {
        Station targetStation = new Station(stationName);
        return searchTargetStation(targetStation);
    }

    private static Station searchTargetStation(Station wantStation) {
        return stations.stream()
                .filter(station -> station.equals(wantStation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 지하철입니다."));
    }
}
