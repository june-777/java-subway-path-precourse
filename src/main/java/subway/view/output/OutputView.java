package subway.view.output;

import java.util.List;
import subway.dto.response.SubwayResponse;

public class OutputView {

    public void printSearchResult(SubwayResponse subwayResponse) {
        List<Integer> distanceAndTime = subwayResponse.distanceAndTime();
        Integer distance = distanceAndTime.get(0);
        Integer time = distanceAndTime.get(1);

        System.out.println();
        System.out.printf(Message.SEARCH_RESULT.message, distance, time);

        List<String> path = subwayResponse.subwayPath();
        for (String stationName : path) {
            System.out.printf(Message.PATH.message, stationName);
            System.out.println();
        }
        System.out.println();
    }

    private enum Message {
        SEARCH_RESULT("""
                ## 조회 결과
                [INFO] ---
                [INFO] 총 거리: %dkm
                [INFO] 총 소요 시간: %d분
                [INFO] ---
                """),
        PATH("[INFO] %s");
        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
