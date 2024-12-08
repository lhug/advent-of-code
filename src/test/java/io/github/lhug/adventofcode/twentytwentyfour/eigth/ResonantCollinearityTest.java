package io.github.lhug.adventofcode.twentytwentyfour.eigth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Transformer;
import io.github.lhug.adventofcode.twentytwentyfour.eigth.ResonantCollinearity.Antenna;
import net.bytebuddy.description.modifier.MethodArguments;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ResonantCollinearityTest {

	private static final String INPUT = """
............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............
""";
	
	private final ResonantCollinearity sut = new ResonantCollinearity(INPUT);

	@Nested
	class Antennae {
		@ParameterizedTest
		@MethodSource("distanceCalculations")
		void finds_distance_between_antennae(Coordinate from, Coordinate to, List<Direction> expected) {
			var first = new Antenna('0', from);
			var second = new Antenna('0', to);

			assertThat(first.distanceFrom(second)).isEqualTo(expected);
		}

		static Stream<Arguments> distanceCalculations() {
			return Stream.of(
					Arguments.of(
							new Coordinate(0, 1),
							new Coordinate( 1, 1),
							List.of(Direction.EAST)
					),
					Arguments.of(
							new Coordinate(1, 1),
							new Coordinate(0, 1),
							List.of(Direction.WEST)
					),
					Arguments.of(
							new Coordinate(1, 0),
							new Coordinate(1, 1),
							List.of(Direction.SOUTH)
					),
					Arguments.of(
							new Coordinate(1, 1),
							new Coordinate(1, 0),
							List.of(Direction.NORTH)
					),
					Arguments.of(
							new Coordinate(1, 4),
							new Coordinate(4, 2),
							List.of(Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTH, Direction.NORTH)
					)
			);
		}

		@Test
		void finds_no_antinode_when_antenna_is_single() {
			var grid = new char[3][3];

			var sut = new Antenna('0', new Coordinate(1, 1));

			var result = sut.antinodes(List.of(sut), grid);

			assertThat(result).isEmpty();
		}

		@Test
		void finds_no_antinode_when_antenna_with_different_frequency_is_found() {
			var grid = new char[3][3];

			var sut = new Antenna('0', new Coordinate(1, 1));
			var second = new Antenna('A', new Coordinate(2, 2));

			var result = sut.antinodes(List.of(sut, second), grid);

			assertThat(result).isEmpty();
		}

		@Test
		void finds_single_antinode_when_antenna_with_fitting_frequency_is_found() {
			var grid = new char[3][3];

			var sut = new Antenna('0', new Coordinate(1, 1));
			var second = new Antenna('0', new Coordinate(2, 2));

			var result = sut.antinodes(List.of(sut, second), grid);

			assertThat(result).containsExactly(
					new Coordinate(0, 0)
			);
		}

		@Test
		void finds_multiple_antinodes_when_antenna_with_fitting_frequencies_are_found() {
			var grid = new char[3][3];

			var sut = new Antenna('0', new Coordinate(1, 1));
			var second = new Antenna('0', new Coordinate(2, 2));
			var third = new Antenna('0', new Coordinate(0, 2));

			var result = sut.antinodes(List.of(sut, second, third), grid);

			assertThat(result).containsExactly(
					new Coordinate(0, 0),
					new Coordinate(2, 0)
			);
		}

		@Test
		void finds_no_antinodes_when_antinode_is_off_grid() {
			var grid = new char[3][3];

			var sut = new Antenna('0', new Coordinate(0, 0));
			var second = new Antenna('0', new Coordinate(2, 2));

			var result = sut.antinodes(List.of(sut, second), grid);

			assertThat(result).isEmpty();
		}
	}

	@Test
	void parses_input_to_grid() {
		assertThat(sut.grid)
				.isEqualTo(Transformer.toMatrix(INPUT));
	}

	@Test
	void finds_all_antennas() {
		assertThat(sut.antennae).containsExactly(
				new Antenna('0', new Coordinate(8, 1)),
				new Antenna('0', new Coordinate(5, 2)),
				new Antenna('0', new Coordinate(7, 3)),
				new Antenna('0', new Coordinate(4, 4)),
				new Antenna('A', new Coordinate(6, 5)),
				new Antenna('A', new Coordinate(8, 8)),
				new Antenna('A', new Coordinate(9, 9))
		);
	}

	@Test
	void counts_unique_locations_for_antinodes() {
		var result = sut.phaseOne();

		assertThat(result).isEqualTo(14L);
	}
}
