package subway.domain.subway.station;

public record StationAndWeight(Station station, int distance, int time) {

    @Override
    public String toString() {
        return "{" + station + ", dist=" + distance + ", time=" + time + '}';
    }

}
