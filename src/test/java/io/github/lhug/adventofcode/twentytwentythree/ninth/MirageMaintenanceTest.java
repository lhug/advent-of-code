package io.github.lhug.adventofcode.twentytwentythree.ninth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MirageMaintenanceTest {

    public static final String input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
            """;

    private MirageMaintenance sut;

    @BeforeEach
    void setUp() {
        sut = new MirageMaintenance(input);
    }

    @Test
    void parses_passed_input() {
        List<List<Long>> results = sut.values();

        assertThat(results).containsExactly(
                List.of(0L, 3L, 6L, 9L, 12L, 15L),
                List.of(1L, 3L, 6L, 10L, 15L, 21L),
                List.of(10L, 13L, 16L, 21L, 30L, 45L)
        );
    }

    @Test
    void returns_sum_of_all_new_steps() {
        long result = sut.sumNextSteps();

        assertThat(result).isEqualTo(114L);
    }

    @Test
    void returns_next_set_of_values() {
        var input = List.of(0L, 3L, 6L, 9L, 12L, 15L);

        assertThat(sut.getNext(input))
                .containsExactly(3L, 3L, 3L, 3L, 3L);
    }

    @Test
    void returns_next_prediction() {
        var input = List.of(0L, 3L, 6L, 9L, 12L, 15L);

        assertThat(sut.recurseToLastValue(input)).isEqualTo(18L);
    }
}