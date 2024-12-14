package io.github.lhug.adventofcode.twentytwentyfour.thirteenth;

import io.github.lhug.adventofcode.common.Vector;
import org.junit.jupiter.api.Test;

import static io.github.lhug.adventofcode.twentytwentyfour.thirteenth.ClawContraption.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ClawContraptionTest {

	private static String INPUT = """
			Button A: X+94, Y+34
			Button B: X+22, Y+67
			Prize: X=8400, Y=5400
			
			Button A: X+26, Y+66
			Button B: X+67, Y+21
			Prize: X=12748, Y=12176
			
			Button A: X+17, Y+86
			Button B: X+84, Y+37
			Prize: X=7870, Y=6450
			
			Button A: X+69, Y+23
			Button B: X+27, Y+71
			Prize: X=18641, Y=10279
			""";

	private final ClawContraption sut = new ClawContraption(INPUT);

	@Test
	void parses_input_into_machines() {
		assertThat(sut.raw)
				.containsExactly(
						new Machine(
								new Vector(94, 34),
								new Vector(22, 67),
								new Vector(8400, 5400)
						),
						new Machine(
								new Vector(26, 66),
								new Vector(67, 21),
								new Vector(12748, 12176)
						),
						new Machine(
								new Vector(17, 86),
								new Vector(84, 37),
								new Vector(7870, 6450)
						),
						new Machine(
								new Vector(69, 23),
								new Vector(27, 71),
								new Vector(18641, 10279)
						)
				);
	}

	@Test
	void calculates_empty_result_for_single_machine() {
		var machine = new Machine(
				new Vector(26, 66),
				new Vector(67, 21),
				new Vector(12748, 12176)
		);

		var result = sut.findCombination(machine);

		assertThat(result)
				.isEmpty();
	}

	@Test
	void calculates_valid_result_for_first_example_machine() {
		var machine = new Machine(
				new Vector(94, 34),
				new Vector(22, 67),
				new Vector(8400, 5400)
		);

		var result = sut.findCombination(machine);

		assertThat(result.get()).isEqualTo(new Vector(80, 40));
	}

	@Test
	void calculates_valid_result_for_second_example_machine() {
		var machine = new Machine(
				new Vector(17, 86),
				new Vector(84, 37),
				new Vector(7870, 6450)
		);

		var result = sut.findCombination(machine);

		assertThat(result.get()).isEqualTo(new Vector(38, 86));
	}

	@Test
	void calculates_price_for_results() {
		var result = sut.phaseOne();

		assertThat(result).isEqualTo(480L);
	}
}
