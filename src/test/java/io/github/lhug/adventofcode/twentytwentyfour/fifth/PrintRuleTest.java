package io.github.lhug.adventofcode.twentytwentyfour.fifth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PrintRuleTest {

    @ParameterizedTest
    @MethodSource("ruleValues")
    void passes_all_rules(String input, int[] values, PrintRule.Result expected) {
        var rule = PrintRule.from(input);

        var result = rule.matches(values);

        assertThat(result).isSameAs(expected);
    }

    static Stream<Arguments> ruleValues() {
        return Stream.of(
                Arguments.of(
                        "1|2",
                        new int[]{1, 2},
                        PrintRule.Result.PASS
                ),
                Arguments.of(
                        "17|4",
                        new int[] { 4, 22, 17 },
                        PrintRule.Result.FAIL
                ),
                Arguments.of(
                        "47|11",
                        new int[] { 47, 8, 15},
                        PrintRule.Result.NOT_APPLICABLE
                )
        );
    }
}
