package subway.dto.response;

import java.util.List;

public record SubwayResponse(List<Integer> distanceAndTime, List<String> subwayPath) {
}
