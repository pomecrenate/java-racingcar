package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import racingcar.model.Car;

public final class InputView {

    public static List<Car> getCarNames() {
        OutputView.displayInitCarNames();
        String input = Console.readLine();
        validateInputCharacters(input);
        List<Car> cars = parseCarNames(input);
        validateCarNameNotEmpty(cars);
        validateCarNameLengths(cars);
        validateCarListSize(cars);
        return cars;
    }

    public static int getNumberOfAttempts() {
        OutputView.displayInitNumberOfAttempts();
        String input = Console.readLine();
        validateInputCharacters(input);
        int numberOfAttempts = parseNumberOfAttempts(input);
        validateNumberOfAttempts(numberOfAttempts);
        return numberOfAttempts;
    }

    /**
     * @param input 사용자가 입력한 자동차 이름 목록
     * @return 쉽표(, )를 기준으로 구분하여 앞뒤 공백 제거 후 자동차 이름 리스트 반환
     */
    private static List<Car> parseCarNames(String input) {
        return Stream.of(input.split(","))
                .map(name -> new Car(name.strip(), 0))
                .collect(Collectors.toList());
    }

    /**
     * @param cars 자동차 이름을 입력하지 않거나 공백만 입력하면 예외 발생
     */
    private static void validateCarNameNotEmpty(List<Car> cars) {
        for (Car car : cars) {
            if (car.getName().isBlank()) {
                throw new IllegalArgumentException("자동차 이름을 입력하세요.");
            }
        }
    }

    private static void validateCarNameLengths(List<Car> cars) {
        for (Car car : cars) {
            if (car.getName().length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하만 가능합니다.");
            }
        }
    }

    private static void validateCarListSize(List<Car> cars) {
        if (cars.size() < 2) {
            throw new IllegalArgumentException("자동차가 2대 이상이어야 합니다.");
        }
    }

    private static int parseNumberOfAttempts(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자를 입력하세요.");
        }
    }

    private static void validateNumberOfAttempts(int numberOfAttempts) {
        if (numberOfAttempts <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
    }

    /**
     * @param input 영문 대소문자, 숫자, 쉼표, 공백, 한글 허용
     */
    private static void validateInputCharacters(String input) {
        if (!input.matches("^[a-zA-Z0-9,\\s가-힣ㄱ-ㅎㅏ-ㅣ]*$")) {
            throw new IllegalArgumentException("입력은 영문자, 한글, 숫자, 빈 칸, 또는 쉼표(,)만 가능합니다.");
        }
    }
}
