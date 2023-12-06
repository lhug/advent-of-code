package io.github.lhug.adventofcode.twentytwentythree.sixth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

class BoatRaceTest {

    public static final String input = """
            Time:      7  15   30
            Distance:  9  40  200
            """;

    private final BoatRace sut = new BoatRace();

    @Test
    void converts_input_to_single_races() {
        sut.parse(input);

        List<SingleRace> results = sut.races();

        assertThat(results).containsExactly(
                new SingleRace(7, 9),
                new SingleRace(15, 40),
                new SingleRace(30, 200)
        );
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            7, 9, 4
            15, 40, 8,
            30, 200, 9
            """)
    void calculates_number_ob_winning_scenarios_for_passed_time_and_distance(int time, int dist, int wins) {
        SingleRace race = new SingleRace(time, dist);

        int result = sut.numberOfWinningScenarios(race);

        assertThat(result).isEqualTo(wins);
    }

    @Test
    void calculates_error_margin() {
        sut.parse(input);

        int result = sut.errorMargin(sut.races());

        assertThat(result).isEqualTo(288);
    }
}