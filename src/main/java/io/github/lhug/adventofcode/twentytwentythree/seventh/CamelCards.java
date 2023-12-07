package io.github.lhug.adventofcode.twentytwentythree.seventh;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelCards {

    private static final List<Character> CARD_VALUES = List.of(
            'A',
            'K',
            'Q',
            'J',
            'T',
            '9',
            '8',
            '7',
            '6',
            '5',
            '4',
            '3',
            '2'
    );
    private final List<Game> games = new ArrayList<>();

    public void parse(String input) {
        input.lines()
                .map(line -> line.split(" "))
                .map(CamelCards::toGame)
                .forEach(games::add);
    }

    private static Game toGame(String[] input) {
        String bid = input[1].strip();
        return new Game(
                input[0],
                Long.parseLong(input[1])
        );
    }

    public long calculateWinnings() {
        var sorted = new ArrayList<>(games);
        sorted.sort(null);
        long value = 0;
        for (int i = 0; i < sorted.size(); i++) {
            value += sorted.get(i).bid() * (i + 1);
        }
        return value;
    }

    public List<Game> games() {
        return games;
    }

    public record Game(String cards, long bid, GameType type) implements Comparable<Game>{
        public Game(String cards, long bid) {
            this(cards, bid, determineType(cards));
        }

        private static GameType determineType(String cards) {
            Map<Character, Long> groupCounts = cards.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));
            return switch (groupCounts.size()) {
                case 1 -> GameType.FIVE;
                case 2 -> groupCounts.containsValue(4L)
                        ? GameType.FOUR
                        : GameType.FULL_HOUSE;
                case 3 -> groupCounts.containsValue(3L)
                        ? GameType.THREE
                        : GameType.TWO_PAIR;
                case 4 -> GameType.PAIR;
                case 5 -> GameType.HIGH;
                default -> throw new IllegalArgumentException("Invalid value!");
            };
        }

        @Override
        public int compareTo(Game o) {
            int result = type.compareTo(o.type);
            if (result != 0) {
                return result < 0
                        ? 1
                        : -1;
            }
            for (int i = 0; i < 5; i++) {
                int left = valueFor(cards.charAt(i));
                int right = valueFor(o.cards.charAt(i));
                if (left < right) {
                    return 1;
                } else if (left > right) {
                    return -1;
                }
            }
            return 0;
        }

        private int valueFor(char offer) {
            return CARD_VALUES.indexOf(offer);
        }
    }



    public enum GameType {
        FIVE,
        FOUR,
        FULL_HOUSE,
        THREE,
        TWO_PAIR,
        PAIR,
        HIGH
    }
}
