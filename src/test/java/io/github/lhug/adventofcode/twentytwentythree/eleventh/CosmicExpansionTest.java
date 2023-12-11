package io.github.lhug.adventofcode.twentytwentythree.eleventh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.lhug.adventofcode.twentytwentythree.eleventh.CosmicExpansion.*;
import static org.assertj.core.api.Assertions.*;

class CosmicExpansionTest {

    public static final String input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
            """;

    private CosmicExpansion sut;

    @BeforeEach
    void setUp() {
        sut = new CosmicExpansion(input);
    }

    @Test
    void correctly_expands_rows_and_columns() {

        assertThat(sut.expand()).isEqualTo("""
            ..>#.>..>.
            ..>..>.#>.
            #.>..>..>.
            VV>VV>VV>V
            ..>..>#.>.
            .#>..>..>.
            ..>..>..>#
            VV>VV>VV>V
            ..>..>.#>.
            #.>.#>..>.""");
    }

    @Test
    void finds_galaxy_coordinates_from_grid() {
        List<Coordinate> coordinates = sut.findCoordinates(input, 1);

        assertThat(coordinates).containsOnly(
                new Coordinate(0, 3),
                new Coordinate(1, 7),
                new Coordinate(2, 0),
                new Coordinate(4, 6),
                new Coordinate(5, 1),
                new Coordinate(6, 9),
                new Coordinate(8, 7),
                new Coordinate(9, 0),
                new Coordinate(9, 4)
        );
    }

    @Test
    void finds_shortest_path_between_coordinates() {
        long result = sut.pathLength(
                new Coordinate(5, 1),
                new Coordinate(9, 4));

        assertThat(result).isEqualTo(7L);
    }

    @Test
    void calculates_shortest_path_sums() {
        long result = sut.findPathsWithExpansion(2);

        assertThat(result).isEqualTo(374L);
    }
}