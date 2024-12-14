package io.github.lhug.adventofcode.twentytwentyfour.fourteenth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Vector;
import org.junit.jupiter.api.Test;

import static io.github.lhug.adventofcode.twentytwentyfour.fourteenth.RestroomRedoubt.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

public class RestroomRedoubtTest {

	private static final String INPUT = """
			p=0,4 v=3,-3
			p=6,3 v=-1,-3
			p=10,3 v=-1,2
			p=2,0 v=2,-1
			p=0,0 v=1,3
			p=3,0 v=-2,-2
			p=7,6 v=-1,-3
			p=3,0 v=-1,-2
			p=9,3 v=2,3
			p=7,3 v=-1,2
			p=2,4 v=2,-3
			p=9,5 v=-3,-3""";

	private final RestroomRedoubt sut = new RestroomRedoubt(INPUT);

	@Test
	void parses_input_into_data() {

		assertThat(sut.raw)
				.containsExactly(
						new Robot(
								new Coordinate(0, 4),
								new Vector(3, -3)),
						new Robot(
								new Coordinate(6, 3),
								new Vector(-1, -3)),
						new Robot(
								new Coordinate(10, 3),
								new Vector(-1, 2)),
						new Robot(
								new Coordinate(2, 0),
								new Vector(2, -1)),
						new Robot(
								new Coordinate(0, 0),
								new Vector(1, 3)),
						new Robot(
								new Coordinate(3, 0),
								new Vector(-2, -2)),
						new Robot(
								new Coordinate(7, 6),
								new Vector(-1, -3)),
						new Robot(
								new Coordinate(3, 0),
								new Vector(-1, -2)),
						new Robot(
								new Coordinate(9, 3),
								new Vector(2, 3)),
						new Robot(
								new Coordinate(7, 3),
								new Vector(-1, 2)),
						new Robot(
								new Coordinate(2, 4),
								new Vector(2, -3)),
						new Robot(
								new Coordinate(9, 5),
								new Vector(-3, -3))
				);
	}

	@Test
	void calculates_final_coordinate_in_smaller_grid() {
		var robot = new Robot(
				new Coordinate(2, 4),
				new Vector(2, -3)
		);

		var result = robot.move(5, new int[7][11]);

		assertThat(result).isEqualTo(new Coordinate(1, 3));
	}

	@Test
	void calculates_positions_in_quadrants_after_steps() {
		var result = sut.simulate(100, new int[7][11]);

		assertThat(result).containsOnly(
				entry("UL", 1),
				entry("UR", 3),
				entry("LL", 4),
				entry("LR", 1)
		);
	}

	@Test
	void calculates_safety_factor() {
		var result = sut.safetyFactor(100, new int[7][11]);

		assertThat(result).isEqualTo(12L);
	}
}
