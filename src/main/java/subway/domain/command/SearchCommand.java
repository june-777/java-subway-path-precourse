package subway.domain.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum SearchCommand {
    SHORTEST_PATH("1"),
    MINIMUM_TIME("2"),
    BACK("B");

    private static final Map<String, SearchCommand> cachedSearchCommand = new HashMap<>();
    private final String command;

    static {
        for (SearchCommand searchCommand : values()) {
            cachedSearchCommand.put(searchCommand.command, searchCommand);
        }
    }

    SearchCommand(String command) {
        this.command = command;
    }

    public static SearchCommand getCommand(String command) {
        return Optional.ofNullable(cachedSearchCommand.get(command))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 커맨드입니다."));
    }
}
