package io.github.lhug.adventofcode.twentytwentythree.fifteenth;

import io.github.lhug.adventofcode.common.StringHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

    public static final String north_tilt = """
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

    public static final String south_tilt = """
            .....#....
            ....#....#
            ...O.##...
            ...#......
            O.O....O#O
            O.#..O.#.#
            O....#....
            OO....OO..
            #OO..###..
            #OO.O#...O""";

    public static final String west_tilt = """
            O....#....
            OOO.#....#
            .....##...
            OO.#OO....
            OO......#.
            O.#O...#.#
            O....#OO..
            O.........
            #....###..
            #OO..#....""";

    public static final String east_tilt = """
            ....O#....
            .OOO#....#
            .....##...
            .OO#....OO
            ......OO#.
            .O#...O#.#
            ....O#..OO
            .........O
            #....###..
            #..OO#....""";

    private final ReflectorDish sut = new ReflectorDish(input);


    @Test
    void counts_load_when_facing_north() {
        assertThat(sut.tilt()).isEqualTo(136);
    }

    @Test
    void tilts_north_correctly() {
        var in = StringHelper.toMatrix(input);
        ReflectorDish.tiltNorth(in);

        assertThat(StringHelper.toString(in))
                .isEqualTo(north_tilt);
    }

    @Test
    void tilts_south_correctly() {
        var in = StringHelper.toMatrix(input);
        ReflectorDish.tiltSouth(in);

        assertThat(StringHelper.toString(in))
                .isEqualTo(south_tilt);
    }

    @Test
    void tilts_west_correctly() {
        var in = StringHelper.toMatrix(input);
        ReflectorDish.tiltWest(in);

        assertThat(StringHelper.toString(in))
                .isEqualTo(west_tilt);
    }

    @Test
    void tilts_east_correctly() {
        var in = StringHelper.toMatrix(input);
        ReflectorDish.tiltEast(in);

        assertThat(StringHelper.toString(in))
                .isEqualTo(east_tilt);
    }

    @Test
    void calculates_correct_load_after_one_milliard_cycles() {
        long result = sut.loadAfterCycling();

        assertThat(result).isEqualTo(64L);
    }
}