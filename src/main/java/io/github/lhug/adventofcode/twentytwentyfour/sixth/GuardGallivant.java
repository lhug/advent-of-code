package io.github.lhug.adventofcode.twentytwentyfour.sixth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Transformer;

import java.util.HashSet;
import java.util.Set;

import static io.github.lhug.adventofcode.common.Direction.*;

public class GuardGallivant {

	char[][] grid;

	public GuardGallivant(String input) {
		grid = Transformer.toMatrix(input);
	}

	public Coordinate startingPoint() {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x] == '^') {
					return new Coordinate(x, y);
				}
			}
		}
		throw new RuntimeException("No starting point found");
	}

	public long phaseOne() {
		var startingPoint = startingPoint();
		var steps = traverseGrid(startingPoint);
		return steps.size();
	}

	private Set<Coordinate> traverseGrid(Coordinate startingPoint) {
		var result = new HashSet<Coordinate>();
		result.add(startingPoint);
		var direction = Direction.NORTH;
		var current = startingPoint.forward(direction);

		while(current.isInBounds(grid)) {
			var token = grid[current.y()][current.x()];
			if(token == '#') {
				current = current.backward(direction);
				direction = turn(direction);
			} else {
				result.add(current);
			}
			current = current.forward(direction);
		}
		return result;
	}

	private Direction turn(Direction current) {
		return switch (current) {
			case NORTH -> EAST;
			case EAST -> SOUTH;
			case SOUTH -> WEST;
			case WEST -> NORTH;
		};
	}
}
