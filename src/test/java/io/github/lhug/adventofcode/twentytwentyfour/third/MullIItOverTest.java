package io.github.lhug.adventofcode.twentytwentyfour.third;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MullIItOverTest {

	private static final String input_1 = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
	private static final String input_2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
	
	private final MullItOver sut = new MullItOver();

	@Test
	void finds_all_mul_instructions() {
		var result = sut.instructions(input_1);

		assertThat(result).containsExactly(
				"mul(2,4)",
				"mul(5,5)",
				"mul(11,8)",
				"mul(8,5)"
		);
	}

	@Test
	void finds_mul_and_do_and_donot() {
		var result = sut.instructions(input_2);

		assertThat(result).containsExactly(
				"mul(2,4)",
				"don't()",
				"mul(5,5)",
				"mul(11,8)",
				"do()",
				"mul(8,5)"
		);
	}

	@Test
	void calculates_mul_instruction() {
		var result = sut.execute("mul(4,5)");

		assertThat(result).isEqualTo(20);
	}

	@Test
	void sums_all_calculations() {
		var result = sut.phaseOne(input_1);

		assertThat(result).isEqualTo(161);
	}

	@Test
	void filters_do_not_instructions() {
		var instructions = List.of(
				"mul(1,1)",
				"don't()",
				"mul(2,2)"
		);

		var result = sut.filter(instructions);

		assertThat(result).containsExactly(
				"mul(1,1)"
		);
	}

	@Test
	void enables_do_instructions() {
		var instructions = List.of(
				"mul(1,1)",
				"don't()",
				"mul(2,2)",
				"do()",
				"mul(3,3)"
		);

		var result = sut.filter(instructions);

		assertThat(result).containsExactly(
				"mul(1,1)",
				"mul(3,3)"
		);
	}

	@Test
	void sums_all_active_calculations() {
		var result = sut.phaseTwo(input_2);

		assertThat(result).isEqualTo(48);
	}
}
