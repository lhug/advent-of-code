package io.github.lhug.adventofcode.twentytwentyfour.second;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ReactorParametersTest {

    private static final String input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;

    private ReactorParameters sut = new ReactorParameters(input);

    @ParameterizedTest
    @MethodSource("inputData")
    void parses_input_into_lines(String data, List<int[]> expected) {
        sut = new ReactorParameters(data);

        assertThat(sut).extracting("data")
                .asList()
                .containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> inputData() {
        return Stream.of(
                Arguments.of(
                        "7 6 4 2 1",
                        List.of(new int[] {7, 6, 4, 2, 1})),
                Arguments.of(
                        input,
                        List.of(
                                new int[]{7, 6, 4, 2, 1},
                                new int[]{1, 2, 7, 8, 9},
                                new int[]{9, 7, 6, 2, 1},
                                new int[]{1, 3, 2, 4, 5},
                                new int[]{8, 6, 4, 4, 1},
                                new int[]{1, 3, 6, 7, 9}
                        )
                )
        );
    }

    @Test
    void marks_sequence_as_unsafe_when_difference_is_zero() {
        var data = new int []{1, 1};

        var result = sut.isSafe(data);

        assertThat(result).isFalse();
    }

    @Test
    void marks_sequence_as_safe_when_difference_is_one() {
        var data = new int []{1, 2};

        var result = sut.isSafe(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_safe_when_difference_is_two() {
        var data = new int []{1, 3};

        var result = sut.isSafe(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_safe_when_difference_is_three() {
        var data = new int []{1, 4};

        var result = sut.isSafe(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_unsafe_when_difference_is_four() {
        var data = new int []{1, 5};

        var result = sut.isSafe(data);

        assertThat(result).isFalse();
    }


    @Test
    void marks_sequence_as_safe_when_all_differences_are_at_most_three() {
        var data = new int[]{1, 2, 4, 7};

        var result = sut.isSafe(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_unsafe_when_at_least_one_difference_is_more_than_three() {
        var data = new int[]{1, 2, 4, 7, 11};

        var result = sut.isSafe(data);

        assertThat(result).isFalse();
    }

    @Test
    void marks_sequence_as_safe_when_all_differences_are_increasing() {
        var data = new int[]{1, 2, 3, 4};

        var result = sut.isSafe(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_safe_when_all_differences_are_decreasing() {
        var data = new int[]{4, 3, 2, 1};

        var result = sut.isSafe(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_unsafe_when_differences_switch() {
        var data = new int[]{1, 2, 3, 2};

        var result = sut.isSafe(data);

        assertThat(result).isFalse();
    }

    @Test
    void counts_safe_sequences() {
        var result = sut.safeSequences();

        assertThat(result).isEqualTo(2L);
    }

    @Test
    void marks_sequence_as_safe_when_sequence_contains_single_error() {
        var data = new int[]{1, 2, 3, 3, 4};

        var result = sut.isSafeWithBuffer(data);

        assertThat(result).isTrue();
    }

    @Test
    void marks_sequence_as_unsafe_when_sequence_contains_multiple_errors() {
        var data = new int[]{1, 2, 3, 3, 4, 3, 5};

        var result = sut.isSafeWithBuffer(data);

        assertThat(result).isFalse();
    }

    @Test
    void counts_safe_sequences_with_buffer() {
        var result = sut.bufferedSafeSequences();

        assertThat(result).isEqualTo(4);
    }
}
