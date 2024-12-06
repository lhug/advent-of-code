package io.github.lhug.adventofcode.common;

public record Coordinate(int x, int y) {

	public boolean isInBounds(char[][] matrix) {
		return y < matrix.length && x < matrix[0].length
				&& y >= 0 && x >= 0;
	}

	public Coordinate forward(Direction direction) {
		return new Coordinate(
				x + direction.diffX(),
				y + direction.diffY()
		);
	}

	public Coordinate backward(Direction direction) {
		return new Coordinate(
				x - direction.diffX(),
				y - direction.diffY()
		);
	}
}
