package io.github.lhug.adventofcode.twentytwentythree.fourth;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Scratchcards {
    public int winningSum(String input) {
        return input.lines()
                .map(this::convertLine)
                .mapToInt(Game::score)
                .sum();
    }

    public Game convertLine(String line) {
        var gameParts = line.split(" \\| ");
        var cardInfo = gameParts[0].split(": ");
        int id = Integer.parseInt(cardInfo[0].substring(5).strip());
        return new Game(id, convert(cardInfo[1]), convert(gameParts[1]));
    }

    private static List<Integer> convert(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .filter(Predicate.not(String::isBlank))
                .map(Integer::parseInt)
                .toList();
    }

    public record Game(int id, List<Integer> winners, List<Integer> numbers) {
        public int score() {
            return numbers.stream()
                    .filter(winners::contains)
                    .reduce(0, (oldValue, newValue) -> {
                        return oldValue == 0 ?
                                1 : oldValue * 2;
                    });
        }
    }
}
