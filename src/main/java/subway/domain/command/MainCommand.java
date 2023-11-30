package subway.domain.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum MainCommand {
    SEARCH_PATH("1"),
    QUIT("Q");

    private static final Map<String, MainCommand> cachedMainCommand = new HashMap<>();
    private final String command;

    static {
        for (MainCommand mainCommand : values()) {
            cachedMainCommand.put(mainCommand.command, mainCommand);
        }
    }

    MainCommand(String command) {
        this.command = command;
    }

    public static MainCommand getCommand(String command) {
        return Optional.ofNullable(cachedMainCommand.get(command))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 커맨드입니다."));
    }

}
