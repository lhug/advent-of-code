package io.github.lhug.adventofcode.twentytwentythree.fourth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public int collectedCards(String input) {
        var baseGames = input.lines().map(this::convertLine).toList();
        Map<Integer, Integer> count = new HashMap<>();
        for (Game game: baseGames) {
            int currentValue = count.computeIfAbsent(game.id, i -> 0);
            int newValue = ++currentValue;
            count.put(game.id, newValue);
            int matches = game.matchCount();
            IntStream.rangeClosed(1, matches)
                    .forEach(id -> {
                        int gameId = game.id + id;
                        int cnt = count.computeIfAbsent(gameId, i -> 0);
                        count.put(gameId, cnt + newValue);
                    });
        }
        return count.values().stream().reduce(0, Integer::sum);
    }

    public record Game(int id, List<Integer> winners, List<Integer> numbers) {
        public int score() {
            return winMatches()
                    .reduce(0, (oldValue, newValue) -> oldValue == 0
                            ? 1
                            : oldValue * 2);
        }

        public int matchCount() {
            return (int) winMatches()
                    .count();
        }

        private Stream<Integer> winMatches() {
            return numbers.stream()
                    .filter(winners::contains);
        }
    }
}
