package io.github.lhug.adventofcode.twentytwentyfour.sixth;

import io.github.lhug.adventofcode.common.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GuardGallivantTest {

	private static final String INPUT = """
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
""";

	private final GuardGallivant sut = new GuardGallivant(INPUT);

	@Test
	void parses_input_into_grid() {
		assertThat(sut.grid).isEqualTo(
				new char[][]{
						"....#.....".toCharArray(),
						".........#".toCharArray(),
						"..........".toCharArray(),
						"..#.......".toCharArray(),
						".......#..".toCharArray(),
						"..........".toCharArray(),
						".#..^.....".toCharArray(),
						"........#.".toCharArray(),
						"#.........".toCharArray(),
						"......#...".toCharArray()
				}
		);
	}

	@Test
	void finds_coordinates_of_starting_point() {
		Coordinate result = sut.startingPoint();

		assertThat(result).isEqualTo(new Coordinate(4, 6));
	}

	@Test
	void counts_distinct_points_when_traversing() {
		long result = sut.phaseOne();

		assertThat(result).isEqualTo(41);
	}
}
