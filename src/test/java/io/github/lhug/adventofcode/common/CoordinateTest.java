package io.github.lhug.adventofcode.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateTest {

	@Test
	void shows_in_bounds_when_coordinate_is_origin() {
		var sut = new Coordinate(0, 0);

		var result = sut.isInBounds(grid(1, 1));

		assertThat(result).isTrue();
	}

	@Test
	void shows_in_bounds_when_coordinate_is_max() {
		var sut = new Coordinate(2, 2);

		var result = sut.isInBounds(grid(3, 3));

		assertThat(result).isTrue();
	}

	@Test
	void shows_out_of_bounds_when_x_is_less_than_zero() {
		var sut = new Coordinate(-1, 0);

		var result = sut.isInBounds(grid(1, 1));

		assertThat(result).isFalse();
	}

	@Test
	void shows_out_of_bounds_when_y_is_less_than_zero() {
		var sut = new Coordinate(0, -1);

		var result = sut.isInBounds(grid(1, 1));

		assertThat(result).isFalse();
	}

	@Test
	void shows_out_of_bounds_when_x_is_more_than_highest_index() {
		var sut = new Coordinate(2, 0);

		var result = sut.isInBounds(grid(2, 1));

		assertThat(result).isFalse();
	}

	@Test
	void shows_out_of_bounds_when_y_is_more_than_highest_index() {
		var sut = new Coordinate(0, 2);

		var result = sut.isInBounds(grid(1, 2));

		assertThat(result).isFalse();
	}

	@Test
	void stepping_forward_returns_new_coordinate_with_added_step_value() {
		var sut = new Coordinate(2, 2);

		var result = sut.forward(Direction.NORTH);

		assertThat(result).isEqualTo(new Coordinate(
				2 + Direction.NORTH.diffX(),
				2 + Direction.NORTH.diffY()));
	}

	@Test
	void stepping_backward_returns_new_coordinate_with_subtracted_step_value() {
		var sut = new Coordinate(2, 2);

		var result = sut.backward(Direction.NORTH);

		assertThat(result).isEqualTo(new Coordinate(
				2 - Direction.NORTH.diffX(),
				2 - Direction.NORTH.diffY()));
	}

	private char[][] grid(int width, int height) {
		return new char[height][width];
	}
}