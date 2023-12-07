package io.github.lhug.adventofcode.twentytwentythree.seventh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.github.lhug.adventofcode.twentytwentythree.seventh.CamelCards.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class CamelCardsTest {

    public static final String input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            """;

    private final CamelCards sut = new CamelCards();

    @BeforeEach
    void setUp() {
        sut.parse(input);
    }

    @Test
    void calculates_overall_winnings() {

        long result = sut.calculateWinnings();

        assertThat(result).isEqualTo(6440);
    }

    @Test
    void parses_input_to_games() {
        List<Game> results = sut.games();

        assertThat(results).containsOnly( // order not yet relevant
                new Game("32T3K", 765),
                new Game("T55J5", 684),
                new Game("KK677", 28),
                new Game("KTJJT", 220),
                new Game("QQQJA", 483)
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
    void finds_game_type_correctly(String input, GameType expected) {
        var game = new Game(input, 1L);

        assertThat(game.type()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            23456, 23456, 0
            23456, 22345, -1
            23456, 22245, -1
            23456, 22344, -1
            23456, 22233, -1
            23456, 22223, -1
            23456, 22222, -1
            22345, 23456, 1
            22345, 22345, 0
            22345, 22234, -1
            22345, 22344, -1
            22345, 22233, -1
            22345, 22223, -1
            22345, 22222, -1
            22344, 23456, 1
            22344, 22345, 1
            22344, 22344, 0
            22344, 22234, -1
            22344, 22233, -1
            22344, 22223, -1
            22344, 22222, -1
            22234, 23456, 1
            22234, 22345, 1
            22234, 22344, 1
            22234, 22234, 0
            22234, 22233, -1
            22234, 22223, -1
            22234, 22222, -1
            22233, 23456, 1
            22233, 22345, 1
            22233, 22344, 1
            22233, 22234, 1
            22233, 22233, 0
            22233, 22223, -1
            22233, 22222, -1
            22223, 23456, 1
            22223, 22345, 1
            22223, 22344, 1
            22223, 22234, 1
            22223, 22233, 1
            22223, 22223, 0
            22223, 22222, -1
            22222, 23456, 1
            22222, 22345, 1
            22222, 22344, 1
            22222, 22234, 1
            22222, 22233, 1
            22222, 22223, 1
            22222, 22222, 0
            """)
    void compares_game_types_by_type(String l, String r, int expected) {
        var left = new Game(l, 1);
        var right = new Game(r, 1);

        int result = left.compareTo(right);
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
    void compares_games_by_cards(String l, String r, int expected) {
        var left = new Game(l, 1);
        var right = new Game(r, 1);

        int result = left.compareTo(right);

        assertThat(result).isEqualTo(expected);
    }
}