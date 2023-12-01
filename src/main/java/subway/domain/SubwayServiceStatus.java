package subway.domain;

import java.util.Arrays;

public enum SubwayServiceStatus {
    SHORTEST_DISTANCE("1"),
    MINIMUM_TIME("2"),
    BACK("B");

    private final String command;

    SubwayServiceStatus(String command) {
        this.command = command;
    }

    public static SubwayServiceStatus of(String commandInput) {
        return Arrays.stream(values())
                .filter(status -> status.command.equals(commandInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드 입니다."));
    }

}
