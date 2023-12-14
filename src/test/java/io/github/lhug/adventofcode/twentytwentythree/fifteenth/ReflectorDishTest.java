package io.github.lhug.adventofcode.twentytwentythree.fifteenth;

import io.github.lhug.adventofcode.common.StringHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReflectorDishTest {

    public static final String input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....""";

    public static final String initial_tilt = """
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....""";

    private final ReflectorDish sut = new ReflectorDish(input);


    @Test
    void counts_load_when_facing_north() {
        assertThat(sut.tilt()).isEqualTo(136);
    }

    @Test
    void tilts_correctly() {
        var in = StringHelper.toMatrix(input);
        ReflectorDish.tiltNorth(in);

        assertThat(StringHelper.toString(in))
                .isEqualTo(initial_tilt);
    }
}