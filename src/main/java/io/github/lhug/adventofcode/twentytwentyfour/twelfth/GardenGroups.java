package io.github.lhug.adventofcode.twentytwentyfour.twelfth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Matrix;
import io.github.lhug.adventofcode.common.Transformer;

import java.util.*;

public class GardenGroups {
	final char[][] grid;

	Map<Character, List<Region>> regions = new HashMap<>();

	public GardenGroups(String input) {
		this.grid = Transformer.toMatrix(input);
		parseMatrix();
	}

	private void parseMatrix() {
		Set<Coordinate> visitedAreas = new HashSet<>();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[0].length; x++) {
				var start = new Coordinate(x, y);
				if (!visitedAreas.contains(start)) {
					parseRegion(visitedAreas, start);
				}
			}
		}
	}

	private void parseRegion(Set<Coordinate> visited, Coordinate start) {
		var gridCopy = Matrix.copyChars(grid);
		Deque<Coordinate> toVisit = new LinkedList<>();
		toVisit.add(start);
		Coordinate current;
		var areaType = gridCopy[start.y()][start.x()];
		long area = 0;
		long perimeter = 0;
		while(!toVisit.isEmpty()) {
			current = toVisit.pollLast();
			area++;
			for(Direction direction : Direction.values()) {
				var next = current.forward(direction);
				if(next.isInBounds(gridCopy)) {
					var currentType = gridCopy[next.y()][next.x()];
					if(currentType == areaType) {
						if(!toVisit.contains(next)) {
							toVisit.push(next);
						}
					} else if (currentType != '.') {
						perimeter++;
					}
				} else {
					perimeter++;
				}
			}
			gridCopy[current.y()][current.x()] = '.';
			visited.add(current);
		}
		regions.computeIfAbsent(areaType, __ -> new ArrayList<>())
				.add(new Region(area, perimeter));
	}

	public long phaseOne() {
		return regions
				.values()
				.stream()
				.flatMap(List::stream)
				.mapToLong(Region::fenceCost)
				.sum();
	}

	record Region(long area, long perimeter){

		public long fenceCost() {
			return area * perimeter;
		}
	}
}
