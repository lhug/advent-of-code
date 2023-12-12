package io.github.lhug.adventofcode.twentytwentythree.twelfth;

import io.github.lhug.adventofcode.twentytwentythree.twelfth.HotSprings.Spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HotSpringsTest {

    public static final String input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
            """;

    private HotSprings sut;

    @BeforeEach
    void setUp() {
        sut = new HotSprings(input);
    }

    @Test
    void parses_lines_correctly() {
        assertThat(sut.springs()).containsExactly(
                new Spring("???.###", new int[]{1, 1, 3}),
                new Spring(".??..??...?##.", new int[]{1, 1, 3}),
                new Spring("?#?#?#?#?#?#?#?", new int[]{1, 3, 1, 6}),
                new Spring("????.#...#...", new int[]{4, 1, 1}),
                new Spring("????.######..#####.", new int[]{1, 6, 5}),
                new Spring("?###????????", new int[]{3, 2, 1})
        );
    }

    @Test
    void calculates_sum_of_all_matches() {
        assertThat(sut.sumMatches()).isEqualTo(21L);
    }

    @Test
    void calculates_unfolded_sum_of_all_matches() {
        assertThat(sut.sumUnfolded()).isEqualTo(525152L);
    }
}