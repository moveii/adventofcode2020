package at.moveii.days;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02 extends Day<List<Day02.Data>> {

    public Day02(Stream<String> input) {
        super(input);

        long countOne = getHandledInput()
                .stream()
                .filter(Data::isCorrectOne)
                .count();

        long countTwo = getHandledInput()
                .stream()
                .filter(Data::isCorrectTwo)
                .count();

        System.out.printf("%s password(s) are correct according to Part I \n", countOne);
        System.out.printf("%s password(s) are correct according to Part II \n", countTwo);
    }

    @Override
    protected List<Data> handleInput(Stream<String> input) {
        return input
                .map(Data::new)
                .collect(Collectors.toList());
    }

    protected static class Data {

        private final int min, max;
        private final String character, password;

        Data(String input) {
            String[] split = input.split(": ");
            this.password = "-".concat(split[1].concat("-"));

            split = split[0].split(" ");
            this.character = split[1];

            split = split[0].split("-");
            this.min = Integer.parseInt(split[0]);
            this.max = Integer.parseInt(split[1]);
        }

        protected boolean isCorrectOne() {
            int count = password.split(character).length - 1;
            return count >= min && count <= max;
        }

        protected boolean isCorrectTwo() {
            final boolean matchesFirstChar = password.charAt(min) == character.charAt(0);
            final boolean matchesSecondChar = password.charAt(max) == character.charAt(0);

            return (matchesFirstChar && !matchesSecondChar) || (!matchesFirstChar && matchesSecondChar);
        }
    }
}
