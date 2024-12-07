package io.github.lhug.adventofcode.twentytwentyfour.seventh;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BridgeRepairTest {

	private static final String INPUT = """
190: 10 19
3267: 81 40 27
83: 17 5
156: 15 6
7290: 6 8 6 15
161011: 16 10 13
192: 17 8 14
21037: 9 7 18 13
292: 11 6 16 20
""";

	private BridgeRepair sut = new BridgeRepair(INPUT);

	@Test
	void parses_input_into_equation_pairs() {
		sut = new BridgeRepair("190: 10 19");

		assertThat(sut.equations).singleElement()
				.satisfies(result -> {
					assertThat(result.result()).isEqualTo(190L);
					assertThat(result.operands()).containsExactly(10L, 19L);
				});
	}

	@Test
	void sums_results_of_valid_solutions() {
		var result = sut.phaseOne();

		assertThat(result).isEqualTo(3749);
	}

	@Test
	void sums_result_iof_valid_solutions_with_three_operators() {
		var result = sut.phaseTwo();

		assertThat(result).isEqualTo(11387);
	}
}
