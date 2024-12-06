package io.github.lhug.adventofcode.twentytwentyfour.sixth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Transformer;

import java.util.HashSet;
import java.util.Objects;
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
		var steps = Objects.requireNonNull(traverseGrid(startingPoint, grid));
		// re-insert starting point to ensure it is counted
		steps.add(new StepTaken(startingPoint, NORTH));
		return steps.stream().map(StepTaken::coordinate).distinct().count();
	}


	public Set<StepTaken> traverseGrid(Coordinate startingPoint, char[][] currentGrid) {
		var result = new HashSet<StepTaken>();
		var direction = Direction.NORTH;
		var current = startingPoint.forward(direction);

		while(current.isInBounds(grid)) {
			var token = currentGrid[current.y()][current.x()];
			if(token == '#') {
				current = current.backward(direction);
				direction = turn(direction);
			} else {
				if (!result.add(new StepTaken(current, direction))) {
					return null;
				}
			}
			current = current.forward(direction);
		}
		return result;
	}

	public long phaseTwo() {
		var start = startingPoint();
		var locations = Objects.requireNonNull(traverseGrid(start, grid))
				.stream()
				.map(StepTaken::coordinate)
				.distinct()
				// can't set obstacle at start position
				.filter(c -> !c.equals(start))
				.toList();
		return locations.stream()
				.parallel()
				.map(coordinate -> makesInfiniteLoop(start, coordinate))
				.filter(Boolean.TRUE::equals)
				.count();
	}

	private boolean makesInfiniteLoop(Coordinate startingPoint, Coordinate obstacle) {
		var newGrid = copyGrid();
		newGrid[obstacle.y()][obstacle.x()] = '#';
		return traverseGrid(startingPoint, newGrid) == null;
	}

	private char[][] copyGrid() {
		char[][] copy = new char[grid.length][];
		for (int i = 0; i < grid.length; i++) {
			copy[i] = grid[i].clone();
		}
		return copy;
	}

	public record StepTaken(Coordinate coordinate, Direction direction) {

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
