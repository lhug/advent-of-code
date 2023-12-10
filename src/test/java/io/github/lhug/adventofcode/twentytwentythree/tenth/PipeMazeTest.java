package io.github.lhug.adventofcode.twentytwentythree.tenth;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PipeMazeTest {
    public static final String input_1 = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
            """;

    public static final String input_2 = """
            -L|F7
            7S-7|
            L|7||
            -L-J|
            L|-JF
            """;

    public static final String input_3 = """
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ
            """;

    private final PipeMaze sut = new PipeMaze();

    @Test
    void finds_furthest_point_in_simple_loop() {
        long result = sut.farthest(input_1);

        assertThat(result).isEqualTo(4);
    }

    @Test
    void finds_furthest_point_in_masked_loop() {
        long result = sut.farthest(input_2);

        assertThat(result).isEqualTo(4);
    }

    @Test
    void finds_furthest_point_in_complicated_loop() {
        long result = sut.farthest(input_3);

        assertThat(result).isEqualTo(8);
    }

    @Test
    void finds_points_in_loop() {
        String input = """
                ...........
                .S-------7.
                .|F-----7|.
                .||.....||.
                .||.....||.
                .|L-7.F-J|.
                .|..|.|..|.
                .L--J.L--J.
                ...........
                """;

        long result = sut.pointsInArea(input);

        assertThat(result).isEqualTo(4L);
    }
}