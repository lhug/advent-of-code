package io.github.lhug.adventofcode.twentytwentythree.seventh;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelCards {
    private final List<Game> games = new ArrayList<>();

    public void parse(String input, CardOrder order) {
        games.clear();
        input.lines()
                .map(line -> line.split(" "))
                .map(array -> toGame(array, order))
                .forEach(games::add);
    }

    private static Game toGame(String[] input, CardOrder order) {
        String bid = input[1].strip();
        return new Game(
                input[0],
                Long.parseLong(bid),
                order
        );
    }

    public long calculateWinnings(CardOrder order) {
        var sorted = new ArrayList<>(games);
        sorted.sort(new GameComparator(order));
        long value = 0;
        for (int i = 0; i < sorted.size(); i++) {
            value += sorted.get(i).bid() * (i + 1);
        }
        return value;
    }

    public List<Game> games() {
        return games;
    }

    public record Game(String cards, long bid, GameType type) {
        public Game(String cards, long bid, CardOrder order) {
            this(cards, bid, determineType(cards, order));
        }

        private static GameType determineType(String cards, CardOrder order) {
            Map<Character, Long> groupCounts = cards.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));
            if(order == CardOrder.JOKERS) {
                Long jokers = Objects.requireNonNullElse(groupCounts.remove('J'), 0L);
                groupCounts.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .ifPresent(highestGroup ->
                                groupCounts.put(
                                        highestGroup.getKey(),
                                        highestGroup.getValue() + jokers)
                        );
            }
            return switch (groupCounts.size()) {
                case 0, 1 -> GameType.FIVE; // 0 is all jokers
                case 2 -> groupCounts.containsValue(4L)
                        ? GameType.FOUR
                        : GameType.FULL_HOUSE;
                case 3 -> groupCounts.containsValue(3L)
                        ? GameType.THREE
                        : GameType.TWO_PAIR;
                case 4 -> GameType.PAIR;
                case 5 -> GameType.HIGH;
                default -> throw new IllegalArgumentException("Invalid value! " + groupCounts.size());
            };
        }
    }

    public enum CardOrder {

        DEFAULT(List.of(
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
        )),
        JOKERS(List.of(
                'A',
                'K',
                'Q',
                'T',
                '9',
                '8',
                '7',
                '6',
                '5',
                '4',
                '3',
                '2',
                'J'
        ));
        private final List<Character> cardValues;

        CardOrder(List<Character> cardValues) {
            this.cardValues = cardValues;
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

    public static class GameComparator implements Comparator<Game> {
        private final CardOrder cardOrder;
        public GameComparator(CardOrder cardOrder) {
            this.cardOrder = cardOrder;
        }

        @Override
        public int compare(Game left, Game right) {
            int result = left.type.compareTo(right.type);
            if (result != 0) {
                return result < 0
                        ? 1
                        : -1;
            }
            for (int i = 0; i < 5; i++) {
                int leftChar = valueFor(left.cards.charAt(i));
                int rightChar = valueFor(right.cards.charAt(i));
                if (leftChar < rightChar) {
                    return 1;
                } else if (leftChar > rightChar) {
                    return -1;
                }
            }
            return 0;
        }

        private int valueFor(char offer) {
            return cardOrder.cardValues.indexOf(offer);
        }
    }
}
