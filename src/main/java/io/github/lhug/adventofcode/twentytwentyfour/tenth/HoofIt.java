package io.github.lhug.adventofcode.twentytwentyfour.tenth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Transformer;

import java.util.*;

public class HoofIt {
	final char[][] grid;
	final List<Coordinate> startingPoints = new ArrayList<>();

	public HoofIt(String input) {
		this.grid = Transformer.toMatrix(input);
	}

	void startingPoints() {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				var current = grid[y][x];
				if(current == '0') {
					startingPoints.add(new Coordinate(x, y));
				}
			}
		}
	}

	public long scoreFor(Coordinate coordinate) {
		return scoreFor(coordinate, 0);
	}

	long scoreFor(Coordinate coordinate, int value) {
		return scoreFor(coordinate, value, new HashSet<>());
	}

	public long scoreFor(Coordinate coordinate, int value, Set<Coordinate> nines) {
		long result = 0;
		for(Direction direction : Direction.values()) {
			var next = coordinate.forward(direction);
			if(next.isInBounds(grid)) {
				int current = grid[next.y()][next.x()] - '0';
				if(value == 8 && current == 9 && nines.add(next)) {
					result += 1;
				} else if (current == value + 1) {
					result += scoreFor(next, current, nines);
				}
			}
		}
		return result;
	}

	public long phaseOne() {
		startingPoints();
		return startingPoints.parallelStream()
				.mapToLong(this::scoreFor)
				.sum();
	}
}
