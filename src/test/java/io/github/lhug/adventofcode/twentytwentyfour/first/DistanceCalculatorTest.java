package io.github.lhug.adventofcode.twentytwentyfour.first;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DistanceCalculatorTest {

	private final String input = """
			3   4
			4   3
			2   5
			1   3
			3   9
			3   3
			""";

	private DistanceCalculator sut;

	@Test
	void generates_two_lists_from_input() {
		sut = new DistanceCalculator(input);

		assertThat(sut.first()).containsExactly(3, 4, 2, 1, 3, 3);
		assertThat(sut.second()).containsExactly(4, 3, 5, 3, 9, 3);
	}

	@Test
	void calculates_distance_for_pairs_from_smallest() {
		sut = new DistanceCalculator(input);

		assertThat(sut.distance_smallest_pairs()).isEqualTo(11);
	}

	@Test
	void calculates_similarity_index() {
		sut = new DistanceCalculator(input);

		assertThat(sut.similarityIndex()).isEqualTo(31);
	}
}
