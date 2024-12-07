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
	void finds_valid_solutions_for_equation() {
		var offer = new BridgeRepair.EquationPair(
				190L,
				new long[]{10, 19}
		);

		var result = sut.findValidSolutionsFor(offer);

		assertThat(result).containsExactly(
			new BridgeRepair.Solution(
					offer,
					List.of(BridgeRepair.Operator.MULTIPLY)
			)
		);
	}

	@Test
	void sums_results_of_valid_solutions() {
		var result = sut.phaseOne();

		assertThat(result).isEqualTo(3749);
	}
}
