package io.github.lhug.adventofcode.twentytwentyfour.tenth;

import io.github.lhug.adventofcode.common.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HoofItTest {

	private static final String INPUT = """
89010123
78121874
87430965
96549874
45678903
32019012
01329801
10456732
""";

	private HoofIt sut = new HoofIt(INPUT);

	@Test
	void parses_input_into_grid() {
		sut = new HoofIt("""
				123
				456
				789
				""");

		assertThat(sut.grid).isEqualTo(
				new char[][] {
						new char[]{'1', '2', '3'},
						new char[]{'4', '5', '6'},
						new char[]{'7', '8', '9'}
				}
		);
	}

	@Test
	void finds_all_starting_points_in_grid() {
		sut.startingPoints();

		assertThat(sut.startingPoints).containsOnly(
				new Coordinate(0, 6),
				new Coordinate(1, 7),
				new Coordinate(2, 0),
				new Coordinate(2, 5),
				new Coordinate(4, 0),
				new Coordinate(4, 2),
				new Coordinate(5, 5),
				new Coordinate(6, 4),
				new Coordinate(6, 6)
		);
	}

	@Test
	void finds_single_traversing_path() {
		sut = new HoofIt("""
				0123
				1234
				8765
				9876
				""");

		var result = sut.scoreFor(new Coordinate(0, 0));

		assertThat(result).isEqualTo(1);
	}

	@Test
	void finds_two_traversing_paths() {
		sut = new HoofIt("""
				...0...
				...1...
				...2...
				6543456
				7.....7
				8.....8
				9.....9
				""");

		var result = sut.scoreFor(new Coordinate(3, 0));

		assertThat(result).isEqualTo(2);
	}

	@Test
	void finds_four_traversing_paths() {
		sut = new HoofIt("""
				..90..9
				...1.98
				...2..7
				6543456
				765.987
				876....
				987....
				""");

		var result = sut.scoreFor(new Coordinate(3, 0));

		assertThat(result).isEqualTo(4);
	}

	@Test
	void finds_single_scored_trail() {
		sut = new HoofIt("""
				10..9..
				2...8..
				3...7..
				4567654
				...8..3
				...9..2
				.....01
				""");

		var result = sut.scoreFor(new Coordinate(1, 0));

		assertThat(result).isOne();
	}

	@Test
	void finds_double_scored_trail() {
		sut = new HoofIt("""
				10..9..
				2...8..
				3...7..
				4567654
				...8..3
				...9..2
				.....01
				""");

		var result = sut.scoreFor(new Coordinate(5, 6));

		assertThat(result).isEqualTo(2L);
	}

	@Test
	void sums_all_found_path_scores() {
		var result = sut.phaseOne();

		assertThat(result).isEqualTo(36);
	}

	@Test
	void finds_distinct_hiking_trails() {
		sut = new HoofIt("""
				.....0.
				..4321.
				..5..2.
				..6543.
				..7..4.
				..8765.
				..9....
				""");

		var result = sut.distinctTrails(new Coordinate(5, 0));

		assertThat(result).isEqualTo(3);
	}

	@Test
	void finds_many_distinct_hiking_trails() {
		sut = new HoofIt("""
				..90..9
				...1.98
				...2..7
				6543456
				765.987
				876....
				987....
				""");

		var result = sut.distinctTrails(new Coordinate(3, 0));

		assertThat(result).isEqualTo(13);
	}

	@Test
	void finds_a_huge_anoubt_of_trails() {
		sut = new HoofIt("""
				012345
				123456
				234567
				345678
				4.6789
				56789.
				""");

		var result = sut.distinctTrails(new Coordinate(0, 0));

		assertThat(result).isEqualTo(227);
	}

	@Test
	void counts_all_distinct_paths() {
		var result = sut.phaseTwo();

		assertThat(result).isEqualTo(81);
	}
}
