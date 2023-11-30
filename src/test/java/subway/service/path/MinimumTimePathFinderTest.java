package subway.service.path;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import subway.controller.InitController;
import subway.domain.subway.station.Station;
import subway.dto.response.SubwayResponse;

class MinimumTimePathFinderTest {

    MinimumTimePathFinder minimumTimePathFinder = new MinimumTimePathFinder();

    @BeforeEach
    void initRepository() {
        InitController initController = new InitController();
        initController.process();
    }

    @DisplayName("[Success] 서로 연결되어 있는 지하철역간의 최소 경로 테스트")
    @ParameterizedTest
    @MethodSource("createSubwayTestData")
    void findTest(List<Object> testData) {
        // given: 시작역, 도착역, 기대거리, 기대시간, 기대경로가 주어지고
        String start = String.valueOf(testData.get(0));
        String end = String.valueOf(testData.get(1));
        int expectedDistance = (int) testData.get(2);
        int expectedTime = (int) testData.get(3);
        List<String> expectedPath = getPath(testData);

        // when: 최단 거리로 경로를 찾았을 때
        SubwayResponse subwayResponse = minimumTimePathFinder.find(new Station(start), new Station(end));
        List<Integer> distanceAndTime = subwayResponse.distanceAndTime();

        // then: 거리, 시간 테스트
        int distance = distanceAndTime.get(0);
        int time = distanceAndTime.get(1);
        assertThat(distance).isEqualTo(expectedDistance);
        assertThat(time).isEqualTo(expectedTime);

        // then: 경로 테스트
        List<String> path = subwayResponse.subwayPath();
        assertThat(path).containsExactlyElementsOf(expectedPath);
    }

    public static List<String> getPath(List<Object> testData) {
        List<String> path = new ArrayList<>();
        for (int idx = 4; idx < testData.size(); idx++) {
            path.add(String.valueOf(testData.get(idx)));
        }
        return path;
    }

    public static Stream<List<Object>> createSubwayTestData() {

        List<List<Object>> testDataList = new ArrayList<>();

        List<Object> testData1 = List.of("교대역", "양재역", 9, 7, "교대역", "남부터미널역", "양재역");
        List<Object> testData2 = List.of("매봉역", "역삼역", 5, 12, "매봉역", "양재역", "강남역", "역삼역");
        List<Object> testData3 = List.of("양재시민의숲역", "교대역", 19, 10, "양재시민의숲역", "양재역", "남부터미널역", "교대역");
        List<Object> testData4 = List.of("남부터미널역", "역삼역", 7, 8, "남부터미널역", "교대역", "강남역", "역삼역");
        List<Object> testData5 = List.of("교대역", "양재시민의숲역", 19, 10, "교대역", "남부터미널역", "양재역", "양재시민의숲역");
        List<Object> testData6 = List.of("교대역", "양재시민의숲역", 19, 10, "교대역", "남부터미널역", "양재역", "양재시민의숲역");
        List<Object> testData7 = List.of("강남역", "강남역", 0, 0, "강남역");

        testDataList.add(testData1);
        testDataList.add(testData2);
        testDataList.add(testData3);
        testDataList.add(testData4);
        testDataList.add(testData5);
        testDataList.add(testData6);
        testDataList.add(testData7);

        return testDataList.stream();
    }
}