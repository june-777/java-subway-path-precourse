package subway.controller.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ApplicationStatus {
    START("1"),
    EXIT("Q");

    private static final Map<String, ApplicationStatus> cachedMainCommand = new HashMap<>();
    private final String command;

    static {
        for (ApplicationStatus applicationStatus : values()) {
            cachedMainCommand.put(applicationStatus.command, applicationStatus);
        }
    }

    ApplicationStatus(String command) {
        this.command = command;
    }

    public static ApplicationStatus getCommand(String command) {
        return Optional.ofNullable(cachedMainCommand.get(command))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 커맨드입니다."));
    }

}
