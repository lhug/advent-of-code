package io.github.lhug.adventofcode.twentytwentyfour.fifth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PrintQueueTest {

    private static final String INPUT = """
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
""";

    private PrintQueue sut = new PrintQueue(INPUT);

    @Test
    void parses_rules() {
        List<PrintRule> result = sut.rules;

        assertThat(result).containsExactly(
                new PrintRule(47, 53),
                new PrintRule(97, 13),
                new PrintRule(97, 61),
                new PrintRule(97, 47),
                new PrintRule(75, 29),
                new PrintRule(61, 13),
                new PrintRule(75, 53),
                new PrintRule(29, 13),
                new PrintRule(97, 29),
                new PrintRule(53, 29),
                new PrintRule(61, 53),
                new PrintRule(97, 53),
                new PrintRule(61, 29),
                new PrintRule(47, 13),
                new PrintRule(75, 47),
                new PrintRule(97, 75),
                new PrintRule(47, 61),
                new PrintRule(75, 61),
                new PrintRule(47, 29),
                new PrintRule(75, 13),
                new PrintRule(53, 13)
        );
    }

    @Test
    void parses_rows() {
        List<int[]> rows = sut.rows;

        assertThat(rows).containsExactly(
                new int[]{75, 47, 61, 53, 29},
                new int[]{97, 61, 53, 29, 13},
                new int[]{75, 29, 13},
                new int[]{75, 97, 47, 61, 53},
                new int[]{61, 13, 29},
                new int[]{97, 13, 75, 29, 47}
        );
    }

    @Test
    void finds_valid_rows() {
        List<int[]> result = sut.validRows();

        assertThat(result).containsExactly(
                new int[]{ 75, 47, 61, 53, 29 },
                new int[]{ 97, 61, 53, 29, 13 },
                new int[]{ 75, 29, 13 }
        );
    }

    @ParameterizedTest
    @MethodSource("middleValueData")
    void finds_middle_value_in_row(int[] row, int expected) {
        var result = PrintQueue.middleValue(row);

        assertThat(result).isEqualTo(expected);

    }

    static Stream<Arguments> middleValueData() {
        return Stream.of(
            Arguments.of(new int[]{ 75, 47, 61, 53, 29 }, 61),
            Arguments.of(new int[]{ 97, 61, 53, 29, 13 }, 53),
            Arguments.of(new int[]{ 75, 29, 13 }, 29),
            Arguments.of(new int[0], 0)
        );
    }

    @Test
    void adds_middle_value_of_valid_rows() {
        var result = sut.phaseOne();

        assertThat(result).isEqualTo(143);
    }

    @Test
    void finds_bad_rows() {
        var result = sut.badRows();

        assertThat(result).containsExactly(
                new int[]{75,97,47,61,53},
                new int[]{61,13,29},
                new int[]{97,13,75,29,47}
        );
    }

    @ParameterizedTest
    @MethodSource("phase2Input")
    void sorts_array_following_rules(int[] in, int[] out) {
        var rules = sut.rules;
        /*rules = rules.stream()
                .filter(r -> r.matches(in) != PrintRule.Result.NOT_APPLICABLE)
                .toList();

         */

        var result = sut.khan(in, rules);

        assertThat(result).isEqualTo(out);
    }

    @Test
    void counts_middle_values_of_sorted_bad_rows() {
        var result = sut.phaseTwo();

        assertThat(result).isEqualTo(123L);
    }

    static Stream<Arguments> phase2Input() {
        return Stream.of(
                Arguments.of(
                        new int[]{75,97,47,61,53},
                        new int[]{97,75,47,61,53}
                ),
                Arguments.of(
                        new int[]{61,13,29},
                        new int[]{61,29,13}
                ),
                Arguments.of(
                        new int[]{97,13,75,29,47},
                        new int[]{97,75,47,29,13}
                )
        );
    }
}
