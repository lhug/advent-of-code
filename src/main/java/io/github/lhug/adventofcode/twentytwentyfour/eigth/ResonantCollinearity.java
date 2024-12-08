package io.github.lhug.adventofcode.twentytwentyfour.eigth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ResonantCollinearity {

	final char[][] grid;
	final List<Antenna> antennae;

	public ResonantCollinearity(String input) {
		this.grid = Transformer.toMatrix(input);
		this.antennae = findAntennae();
	}

	List<Antenna> findAntennae() {
		var result = new ArrayList<Antenna>();
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[0].length; x++) {
				var current = grid[y][x];
				if(isFrequency(current)) {
					result.add(new Antenna(current, new Coordinate(x, y)));
				}
			}
		}
		return result;
	}

	private static boolean isFrequency(char in) {
		return
				(in >= 65 && in <= 90) // A-Z
				|| (in >= 97 && in <= 122) // a-z
				|| (in >= 48 && in <= 57); // 0-9
	}

	public long phaseOne() {
		return antennae.stream()
				.parallel()
				.map(antenna -> antenna.antinodes(antennae, grid))
				.flatMap(List::stream)
				.distinct()
				.count();

	}

	record Antenna(char frequency, Coordinate coordinate) {

		public List<Direction> distanceFrom(Antenna second) {
			var diffX = second.coordinate.x() - coordinate.x();
			var diffY = second.coordinate.y() - coordinate.y();
			List<Direction> result = new ArrayList<>();
			IntStream.range(
					Math.min(0, diffX),
					Math.max(0, diffX)
			).mapToObj(
					i -> i < 0 ? Direction.WEST : Direction.EAST
			).forEach(
					result::add
			);
			IntStream.range(
					Math.min(0, diffY),
					Math.max(0, diffY)
			).mapToObj(
					i -> i < 0 ? Direction.NORTH : Direction.SOUTH
			).forEach(
					result::add
			);
			return result;
		}

		public List<Coordinate> antinodes(List<Antenna> antennae, char[][] grid) {
			List<Coordinate> results = new ArrayList<>();
			for(Antenna antenna : antennae) {
				if(this == antenna || this.frequency != antenna.frequency) {
					continue;
				}
				var directions = this.distanceFrom(antenna);
				var current = coordinate();
				for(Direction direction : directions) {
					current = current.backward(direction);
				}
				if(current.isInBounds(grid)) {
					results.add(current);
				}
			}
			return results;
		}
	}
}
