package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readApplicationStatus() {
        System.out.println(Message.APPLICATION_COMMAND.message);
        String input = scanner.nextLine().trim();
        validateBlank(input);
        return input;
    }

    public String readSubwayServiceCommand() {
        System.out.println();
        System.out.println(Message.SUBWAY_SERVICE_COMMAND.message);
        String input = scanner.nextLine().trim();
        validateBlank(input);
        return input;
    }

    public String readStartStation() {
        System.out.println();
        System.out.println(Message.START_STATION.message);
        String input = scanner.nextLine().trim();
        validateBlank(input);
        return input;
    }

    public String readDestinationStation() {
        System.out.println();
        System.out.println(Message.DESTINATION_STATION.message);
        String input = scanner.nextLine().trim();
        validateBlank(input);
        return input;
    }

    private void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            throw new IllegalArgumentException("공백 입력은 안됩니다.");
        }
    }

    private enum Message {
        APPLICATION_COMMAND("""
                ## 메인 화면
                1. 경로 조회
                Q. 종료
                                
                ## 원하는 기능을 선택하세요."""),
        SUBWAY_SERVICE_COMMAND("""
                ## 경로 기준
                1. 최단 거리
                2. 최소 시간
                B. 돌아가기
                                
                ## 원하는 기능을 선택하세요."""),
        START_STATION("## 출발역을 입력하세요."),
        DESTINATION_STATION("## 도착역을 입력하세요.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
