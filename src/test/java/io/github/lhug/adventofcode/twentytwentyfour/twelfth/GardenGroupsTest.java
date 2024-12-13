package io.github.lhug.adventofcode.twentytwentyfour.twelfth;

import io.github.lhug.adventofcode.common.Transformer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.lhug.adventofcode.twentytwentyfour.twelfth.GardenGroups.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class GardenGroupsTest {

	private static final String INPUT = """
			AAAA
			BBCD
			BBCC
			EEEC
			""";

	private GardenGroups sut = new GardenGroups(INPUT);

	@Test
	void parses_input_into_matrix() {
		assertThat(sut.grid).isEqualTo(Transformer.toMatrix(INPUT));
	}

	@Test
	void finds_areas_in_matrix() {
		assertThat(sut.regions).containsOnly(
				entry('A', List.of(new Region(4L, 10L))),
				entry('B', List.of(new Region(4L, 8L))),
				entry('C', List.of(new Region(4L, 10L))),
				entry('D', List.of(new Region(1L, 4L))),
				entry('E', List.of(new Region(3L, 8L)))
		);
	}

	@Test
	void region_calculates_price() {
		var region = new Region(4L, 10L);

		assertThat(region.fenceCost()).isEqualTo(40L);
	}

	@Test
	void sums_price_of_all_regions() {
		var result = sut.phaseOne();

		assertThat(result).isEqualTo(140);
	}
}
