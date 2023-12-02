package io.github.lhug.adventofcode.twentytwentythree.second;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static io.github.lhug.adventofcode.twentytwentythree.second.ColoredBoxesGame.*;
import static org.assertj.core.api.Assertions.assertThat;

class ColoredBoxesGameTest {

    private static final String input_1 = """
    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """;

    private final ColoredBoxesGame sut = new ColoredBoxesGame();

    @Test
    void maps_to_game_with_id_and_three_colored_configurations() {
        Game result = sut.toData("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");

        assertThat(result.id()).isEqualTo(1);
        assertThat(result.configurations()).containsExactly(
                new Configuration(4, 0, 3),
                new Configuration(1, 2, 6),
                new Configuration(0, 2, 0)
        );
    }

    @Test
    void returns_true_when_configuration_matches_set_values() {
        sut.setRed(1);
        sut.setBlue(0);
        sut.setGreen(0);

        var config = new Configuration(1, 0, 0);

        boolean result = sut.isPossible(List.of(config));

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("gameConfigSets")
    void returns_false_when_at_least_one_entry_does_not_match(
            List<Configuration> offer,
            boolean expected,
            Configuration ignored) {
        sut.setRed(5);
        sut.setGreen(5);
        sut.setBlue(5);

        assertThat(sut.isPossible(offer)).isEqualTo(expected);
    }

    @Test
    void adds_game_ids_from_possible_games_phase_1() {
        int result = sut.calculatePossibleGames(input_1);

        assertThat(result).isEqualTo(8);
    }

    @ParameterizedTest
    @MethodSource("gameConfigSets")
    void calculates_minimum_number_of_cubes_per_game(
            List<Configuration> offer,
            boolean ignored,
            Configuration expected
    ) {
        Configuration result = sut.minimumConfig(offer);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculates_power_without_zeroes() {
        var offer = new Configuration(2, 2, 2);

        assertThat(offer.power()).isEqualTo(8);
    }

    @Test
    void calculates_power_with_zeroes() {
        var offer = new Configuration(2, 2, 0);

        assertThat(offer.power()).isZero();
    }

    @Test
    void adds_powers_from_all_games() {
        int result = sut.addPowers(input_1);

        assertThat(result).isEqualTo(2286);
    }

    private static Stream<Arguments> gameConfigSets() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Configuration(1, 0, 0),
                                new Configuration(0, 1, 0),
                                new Configuration(0, 0, 1)
                        ),
                        true,
                        new Configuration(1, 1, 1)
                ),
                Arguments.of(
                        List.of(
                                new Configuration(5, 5, 5)
                        ),
                        true,
                        new Configuration(5, 5, 5)
                ),
                Arguments.of(
                        List.of(
                                new Configuration(0, 0, 0),
                                new Configuration(1, 1, 1),
                                new Configuration(2, 2, 2),
                                new Configuration(3, 3, 3),
                                new Configuration(4, 4, 4),
                                new Configuration(5, 5, 5),
                                new Configuration(5, 5, 6)
                        ),
                        false,
                        new Configuration(5, 5, 6)

                ),
                Arguments.of(
                        List.of(new Configuration(6, 5, 5)),
                        false,
                        new Configuration(6, 5, 5)
                ),
                Arguments.of(
                        List.of(new Configuration(5, 6, 5)),
                        false,
                        new Configuration(5, 6, 5)
                ),
                Arguments.of(
                        List.of(new Configuration(5, 5, 6)),
                        false,
                        new Configuration(5, 5, 6)
                ),
                Arguments.of(
                        List.of(
                                new Configuration(0, 0, 0),
                                new Configuration(1, 1, 1),
                                new Configuration(0, 2, 20),
                                new Configuration(0, 3, 3),
                                new Configuration(0, 99, 4),
                                new Configuration(0, 5, 5),
                                new Configuration(0, 5, 6)
                        ),
                        false,
                        new Configuration(1, 99, 20)
                )
        );


    }
}