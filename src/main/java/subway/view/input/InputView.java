package subway.view.input;

import java.util.Scanner;
import subway.view.validator.InputValidator;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readMainCommand() {
        System.out.println(Message.MAIN_COMMAND.message);
        System.out.println(Message.WANT_FEATURES.message);
        String input = scanner.nextLine();
        InputValidator.validate(input);
        return input;
    }

    public String readSearchCommand() {
        System.out.println();
        System.out.println(Message.SEARCH_COMMAND.message);
        System.out.println(Message.WANT_FEATURES.message);
        String input = scanner.nextLine();
        InputValidator.validate(input);
        return input;
    }

    public String readStartStation() {
        System.out.println();
        System.out.println(Message.START_STATION.message);
        String input = scanner.nextLine();
        InputValidator.validate(input);
        return input;
    }

    public String readEndStation() {
        System.out.println();
        System.out.println(Message.END_STATION.message);
        String input = scanner.nextLine();
        InputValidator.validate(input);
        return input;
    }

    private enum Message {
        MAIN_COMMAND("""
                ## 메인 화면
                1. 경로 조회
                Q. 종료
                """),
        SEARCH_COMMAND("""
                ## 경로 기준
                1. 최단 거리
                2. 최소 시간\s
                B. 돌아가기     
                """),
        WANT_FEATURES("## 원하는 기능을 선택하세요."),
        START_STATION("## 출발역을 입력하세요."),
        END_STATION("## 도착역을 입력하세요.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
