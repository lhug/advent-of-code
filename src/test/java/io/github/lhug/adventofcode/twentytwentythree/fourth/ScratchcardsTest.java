package io.github.lhug.adventofcode.twentytwentythree.fourth;

import io.github.lhug.adventofcode.twentytwentythree.fourth.Scratchcards.Game;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScratchcardsTest {

    private static final String input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
            """;

    private final Scratchcards sut = new Scratchcards();

    @Test
    void converts_input_to_cards_and_numbers() {
        Game result = sut.convertLine("Card 1: 12 34 56 78 90 | 11 22 33 44 55 66 77 88");

        assertThat(result.id()).isEqualTo(1);
        assertThat(result.winners()).containsExactly(12, 34, 56, 78, 90);
        assertThat(result.numbers()).containsExactly(11, 22, 33, 44, 55, 66, 77, 88);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            1 2 3 4, 5 6 7 8, 0
            1 2 3 4, 4 5 6 7, 1
            1 2 3 4, 1 2 5 6, 2
            4 2 1 3, 1 2 3 5, 4
            2 4 3 1, 1 2 3 4, 8
            """)
    void returns_one_when_one_winner_is_found(
            String winners,
            String numbers,
            int expectedScore
    ) {
        Game offer = new Game(1, convert(winners), convert(numbers));

        int result = offer.score();

        assertThat(result).isEqualTo(expectedScore);
    }

    private static List<Integer> convert(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .map(Integer::parseInt)
                .toList();
    }




    @Test
    void returns_winning_sum() {
        int result = sut.winningSum(input);

        assertThat(result).isEqualTo(13);
    }
}