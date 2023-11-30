package subway.domain.subway.station;

public class AdjacentStation {

    private final StationInformation start;
    private final StationInformation end;
    private final int distance;
    private final int time;

    public AdjacentStation(StationInformation start, StationInformation end, int distance, int time) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

    public Station getStartStation() {
        String startStationKoreanName = start.getKoreanName();
        return StationRepository.findByName(startStationKoreanName);
    }

    public Station getEndStation() {
        String startStationKoreanName = end.getKoreanName();
        return StationRepository.findByName(startStationKoreanName);
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
