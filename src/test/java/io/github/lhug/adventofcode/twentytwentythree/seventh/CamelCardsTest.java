package io.github.lhug.adventofcode.twentytwentythree.seventh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;

import static io.github.lhug.adventofcode.twentytwentythree.seventh.CamelCards.*;
import static org.assertj.core.api.Assertions.assertThat;

class CamelCardsTest {

    public static final String input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            """;

    private final CamelCards sut = new CamelCards();

    @Test
    void calculates_overall_winnings_with_default_order() {
        sut.parse(input, CardOrder.DEFAULT);

        long result = sut.calculateWinnings(CardOrder.DEFAULT);

        assertThat(result).isEqualTo(6440);
    }

    @Test
    void calculates_overall_winnings_with_jokers_order() {
        sut.parse(input, CardOrder.JOKERS);

        long result = sut.calculateWinnings(CardOrder.JOKERS);

        assertThat(result).isEqualTo(5905);
    }

    @Test
    void parses_input_to_games_with_default_order() {
        sut.parse(input, CardOrder.DEFAULT);

        List<Game> results = sut.games();

        assertThat(results).containsOnly( // order not yet relevant
                new Game("32T3K", 765, GameType.PAIR),
                new Game("T55J5", 684, GameType.THREE),
                new Game("KK677", 28, GameType.TWO_PAIR),
                new Game("KTJJT", 220, GameType.TWO_PAIR),
                new Game("QQQJA", 483, GameType.THREE)
        );
    }

    @Test
    void parses_input_to_games_with_joker_order() {
        sut.parse(input, CardOrder.JOKERS);

        List<Game> results = sut.games();

        assertThat(results).containsOnly(
                new Game("32T3K", 765, GameType.PAIR),
                new Game("T55J5", 684, GameType.FOUR),
                new Game("KK677", 28, GameType.TWO_PAIR),
                new Game("KTJJT", 220, GameType.FOUR),
                new Game("QQQJA", 483, GameType.FOUR)
        );
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            23456, HIGH
            22345, PAIR,
            22334, TWO_PAIR,
            22234, THREE,
            22333, FULL_HOUSE,
            22223, FOUR,
            22222, FIVE
            """)
    void finds_game_type_correctly_with_default_order(String input, GameType expected) {
        var game = new Game(input, 1L, CardOrder.DEFAULT);

        assertThat(game.type()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            23456, HIGH
            2J456, PAIR,
            2JJ56, THREE,
            22J55, FULL_HOUSE
            2JJJ6, FOUR,
            2JJJJ, FIVE
            """)
    void finds_game_type_correctly_with_jokers_order(String input, GameType expected) {
        var game = new Game(input, 1L, CardOrder.JOKERS);

        assertThat(game.type()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            HIGH, HIGH, 0,
            HIGH, PAIR, -1
            HIGH, TWO_PAIR, -1
            HIGH, THREE, -1
            HIGH, FULL_HOUSE, -1
            HIGH, FOUR, -1
            HIGH, FIVE, -1
            PAIR, HIGH, 1
            PAIR, PAIR, 0
            PAIR, TWO_PAIR, -1
            PAIR, THREE, -1
            PAIR, FULL_HOUSE, -1
            PAIR, FOUR, -1
            PAIR, FIVE, -1
            TWO_PAIR, HIGH, 1
            TWO_PAIR, PAIR, 1
            TWO_PAIR, TWO_PAIR, 0
            TWO_PAIR, THREE, -1
            TWO_PAIR, FULL_HOUSE, -1
            TWO_PAIR, FOUR, -1
            TWO_PAIR, FIVE, -1
            THREE, HIGH, 1
            THREE, PAIR, 1
            THREE, TWO_PAIR, 1
            THREE, THREE, 0
            THREE, FULL_HOUSE, -1
            THREE, FOUR, -1
            THREE, FIVE, -1
            FULL_HOUSE, HIGH, 1
            FULL_HOUSE, PAIR, 1
            FULL_HOUSE, TWO_PAIR, 1
            FULL_HOUSE, THREE, 1
            FULL_HOUSE, FULL_HOUSE, 0
            FULL_HOUSE, FOUR, -1
            FULL_HOUSE, FIVE, -1
            FOUR, HIGH, 1
            FOUR, PAIR, 1
            FOUR, TWO_PAIR, 1
            FOUR, THREE, 1
            FOUR, FULL_HOUSE, 1
            FOUR, FOUR, 0
            FOUR, FIVE, -1
            FIVE, HIGH, 1
            FIVE, PAIR, 1
            FIVE, TWO_PAIR, 1
            FIVE, THREE, 1
            FIVE, FULL_HOUSE, 1
            FIVE, FOUR, 1
            FIVE, FIVE, 0
            """)
    void compares_game_types_by_type(GameType l, GameType r, int expected) {
        var left = new Game("23456", 1, l);
        var right = new Game("23456", 1, r);

        Comparator<Game> comp = new GameComparator(CardOrder.JOKERS);

        int result = comp.compare(left, right);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            22222, 33333, -1
            33333, 44444, -1
            44444, 55555, -1
            55555, 66666, -1
            66666, 77777, -1
            77777, 88888, -1
            88888, 99999, -1
            99999, TTTTT, -1
            TTTTT, JJJJJ, -1
            JJJJJ, QQQQQ, -1
            QQQQQ, KKKKK, -1
            KKKKK, AAAAA, -1
            AAAAA, AAAAA, 0
            
            A3456, 23456, 1
            2A456, 23456, 1
            23A56, 23456, 1
            234A6, 23456, 1
            2345A, 23456, 1
            """)
    void compares_games_by_cards_with_default_order(String l, String r, int expected) {
        var left = new Game(l, 1, GameType.HIGH);
        var right = new Game(r, 1, GameType.HIGH);

        Comparator<Game> comp = new CamelCards.GameComparator(CardOrder.DEFAULT);

        int result = comp.compare(left, right);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            JJJJJ, 22222, -1
            22222, 33333, -1
            33333, 44444, -1
            44444, 55555, -1
            55555, 66666, -1
            66666, 77777, -1
            77777, 88888, -1
            88888, 99999, -1
            99999, TTTTT, -1
            TTTTT, QQQQQ, -1
            QQQQQ, KKKKK, -1
            KKKKK, AAAAA, -1
            AAAAA, AAAAA, 0
            
            A3456, 23456, 1
            2A456, 23456, 1
            23A56, 23456, 1
            234A6, 23456, 1
            2345A, 23456, 1
            """)
    void compares_games_by_cards_with_jokers_order(String l, String r, int expected) {
        var left = new Game(l, 1, GameType.HIGH);
        var right = new Game(r, 1, GameType.HIGH);

        Comparator<Game> comp = new CamelCards.GameComparator(CardOrder.JOKERS);

        int result = comp.compare(left, right);

        assertThat(result).isEqualTo(expected);
    }
}