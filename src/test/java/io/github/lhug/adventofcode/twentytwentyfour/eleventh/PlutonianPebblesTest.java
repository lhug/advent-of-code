package io.github.lhug.adventofcode.twentytwentyfour.eleventh;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlutonianPebblesTest {
    private static final String INPUT = "125 17";
    
    private PlutonianPebbles sut = new PlutonianPebbles(INPUT);

    @Test
    void parses_input_into_list() {
        sut = new PlutonianPebbles("0 1 10 99 999");

        assertThat(sut.numbers).containsExactly("0", "1", "10", "99", "999");
    }

    @Test
    void applies_zero_rule_to_input() {
        var result = sut.applyRules("0");

        assertThat(result).containsExactly("1");
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 111, 222, 333} )
    void applies_multiplier_rule_ti_input(int input) {
        var expected = String.valueOf(input * 2024);

        var result = sut.applyRules(String.valueOf(input));

        assertThat(result).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("splitRule")
    void applies_split_rule(String input, String[] expected) {
        var result = sut.applyRules(input);

        assertThat(result).containsExactly(expected);
    }

    @Test
    void applies_rules_to_input() {
        sut = new PlutonianPebbles("0 1 10 99 999");

        var result = sut.blink(1);

        assertThat(result).containsExactly(
                "1",
                "2024",
                "1",
                "0",
                "9",
                "9",
                "2021976"
        );
    }

    @Test
    void applies_multiple_blinks() {
        var result = sut.blink(6);

        assertThat(result).containsExactly(
                "2097446912",
                "14168",
                "4048",
                "2",
                "0",
                "2",
                "4",
                "40",
                "48",
                "2024",
                "40",
                "48",
                "80",
                "96",
                "2",
                "8",
                "6",
                "7",
                "6",
                "0",
                "3",
                "2"
        );
    }

    @Test
    void counts_resulting_pebbles() {
        var result = sut.phaseOne();

        assertThat(result).isEqualTo(55312);

    }

    static Stream<Arguments> splitRule() {
        return Stream.of(
                Arguments.of(
                        "10",
                        new String[]{ "1", "0" }
                ),
                Arguments.of(
                        "99",
                        new String[]{"9", "9"}
                ),
                Arguments.of(
                        "2024",
                        new String[] {"20", "24"}
                ),
                Arguments.of(
                        "1000",
                        new String[] { "10", "0" }
                ),
                Arguments.of(
                        "100000",
                        new String[] {"100", "0"}
                )
        );
    }
}
