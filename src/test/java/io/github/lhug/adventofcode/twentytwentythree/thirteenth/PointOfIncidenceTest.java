package io.github.lhug.adventofcode.twentytwentythree.thirteenth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.github.lhug.adventofcode.twentytwentythree.thirteenth.PointOfIncidence.*;
import static org.assertj.core.api.Assertions.*;

class PointOfIncidenceTest {

    public static final String full_input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
                        
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
            """;

    public static final String input_1 = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
            """;

    public static final String input_2 = """
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
            """;

    private PointOfIncidence sut;

    @BeforeEach
    void setUp() {
        sut = new PointOfIncidence(full_input);
    }

    @Test
    void parses_input() {
        assertThat(sut.mirrors()).containsExactly(
                new MirrorMaze(
                        input_1.lines().toArray(String[]::new),
                        new String[]{
                                "#.##..#",
                                "..##...",
                                "##..###",
                                "#....#.",
                                ".#..#.#",
                                ".#..#.#",
                                "#....#.",
                                "##..###",
                                "..##..."
                        }
                ),
                new MirrorMaze(
                        input_2.lines().toArray(String[]::new),
                        new String[]{
                                "##.##.#",
                                "...##..",
                                "..####.",
                                "..####.",
                                "#..##..",
                                "##....#",
                                "..####.",
                                "..####.",
                                "###..##"
                        }
                )

        );
    }

    @Test
    void finds_line_count_before_matching_line() {
        var one = sut.mirrors().get(0);

        long result = sut.countLinesBeforeMirror(one.columns(), 0);

        assertThat(result).isEqualTo(5L);
    }

    @Test
    void finds_line_count_before_matching_column() {
        var one = sut.mirrors().get(0);

        long result = sut.countLinesBeforeMirror(one.lines(), 0);

        assertThat(result).isZero();
    }

    @Test
    void calculates_matching_lines() {
        long result = sut.calculateMirrorLines();

        assertThat(result).isEqualTo(405L);
    }

    @Test
    void calculates_smudged_lines() {
        long result = sut.calculateSmudgedLines();

        assertThat(result).isEqualTo(400L);
    }
}