package io.github.lhug.adventofcode.twentytwentyfour.fourteenth;

import io.github.lhug.adventofcode.common.Coordinate;
import io.github.lhug.adventofcode.common.Matrix;
import io.github.lhug.adventofcode.common.Vector;

import java.util.*;
import java.util.stream.IntStream;

public class RestroomRedoubt {

	final List<Robot> raw = new ArrayList<>();

	public RestroomRedoubt(String input) {
		input.lines().forEach(this::parseLine);
	}

	private void parseLine(String input) {
		var parts = input.split(" ");
		var start = parsePart(parts[0]);
		var direction = parsePart(parts[1]);
		raw.add(new Robot(
				new Coordinate(start[0], start[1]),
				new Vector(direction[0], direction[1])
		));
	}

	private int[] parsePart(String input) {
		var parts = input.split(",");
		return new int[] {
				Integer.parseInt(parts[0].substring(2)),
				Integer.parseInt(parts[1])
		};

	}

	private List<Coordinate> robotMovements(int steps, int[][] grid) {
		return raw.stream()
				.map(r -> r.move(steps, grid))
				.toList();
	}

	public Map<String, Integer> simulate(int steps, int[][] grid) {
		var result = new HashMap<String, Integer>();
		var length = grid[0].length;
		var height = grid.length;
		var quadLength = ((grid[0].length -1) / 2);
		var quadHeight = ((grid.length -1) / 2);
		var ul = new Quadrant(0, quadLength - 1, 0, quadHeight - 1);
		var ur = new Quadrant(length - quadLength, length - 1, 0, quadHeight - 1);
		var ll = new Quadrant(0, quadLength -1, height - quadHeight, height - 1);
		var lr = new Quadrant(length - quadLength, length - 1, height - quadHeight, height - 1);
		var newPositions = robotMovements(steps, grid);
		for(Coordinate stepResult : newPositions) {
			if(ul.inQuadrant(stepResult)) {
				var count = result.getOrDefault("UL", 0);
				result.put("UL", ++count);
			}
			if(ur.inQuadrant(stepResult)) {
				var count = result.getOrDefault("UR", 0);
				result.put("UR", ++count);
			}
			if(ll.inQuadrant(stepResult)) {
				var count = result.getOrDefault("LL", 0);
				result.put("LL", ++count);
			}
			if(lr.inQuadrant(stepResult)) {
				var count = result.getOrDefault("LR", 0);
				result.put("LR", ++count);
			}
		}
		return result;
	}

	public long safetyFactor(int steps, int[][] grid) {
		var positions = simulate(steps, grid);
		return positions.values()
				.stream()
				.mapToLong(i -> (long) i)
				.reduce(1L, Math::multiplyExact);

	}

	public int[] christmasTree(int[][] grid, boolean print) {
		var maxSteps = 10_403; // repeating after this
		return IntStream.rangeClosed(1, maxSteps)
				.mapToObj(i -> patternFrame(i, grid))
				.filter(this::hasUniquePositions)
				.filter(this::isPotentialChristmasTree)
				.peek(frame -> {
					if (print) {
						printFrame(frame, grid);
					}
				})
				.mapToInt(Frame::steps)
				.toArray();
	}

	private void printFrame(Frame frame, int[][] grid) {
		var img = fill(grid);
		for(Coordinate position : frame.positions) {
			img[position.y()][position.x()] = '*';
		}
		Arrays.stream(img)
				.map(String::new)
				.forEach(System.out::println);
	}

	private char[][] fill(int[][] grid) {
		char[][] copy = new char[grid.length][grid[0].length];
		for (int y = 0; y < copy.length; y++) {
			for (int x = 0; x < copy[0].length; x++) {
				copy[y][x] = '.';
			}
		}
		return copy;
	}

	private boolean isPotentialChristmasTree(Frame frame) {
		Map<Integer, SortedSet<Coordinate>> groups = new HashMap<>();
		for(Coordinate position : frame.positions()) {
			groups.computeIfAbsent(
					position.y(),
					__ -> new TreeSet<>(Comparator.comparing(Coordinate::x)))
					.add(position);
		}
		var lines = IntStream.rangeClosed(1, groups.size())
				.mapToObj(i -> groups.getOrDefault(i, new TreeSet<>()))
				.filter(this::isStraightLine)
				.count();
		return lines != 0;
	}

	private boolean isStraightLine(SortedSet<Coordinate> offer) {
		Coordinate compare = null;
		for(Coordinate position : offer) {
			if (compare == null) {
				compare = position;
			} else {
				if(position.x() - compare.x() != 1) {
					return false;
				} else {
					compare = position;
				}
			}
		}
		return true;
	}

	private boolean hasUniquePositions(Frame frame) {
		var asSet = new HashSet<>(frame.positions);
		return frame.positions.size() == asSet.size();
	}

	private Frame patternFrame(int steps, int[][] grid) {
		var positions =  raw.stream()
				.map(r -> r.move(steps, grid))
				.toList();
		return new Frame(steps, positions);
	}

	record Frame(int steps, List<Coordinate> positions) {}

	record Quadrant(int left, int right, int upper, int lower) {
		boolean inQuadrant(Coordinate c) {
			return
					c.x() >= left && c.x() <= right && c.y() >= upper && c.y() <= lower;
		}
	}

	record Robot(Coordinate start, Vector movement) {
		public Coordinate move(int steps, int[][] grid) {
			var overallVector = new Vector(
					movement.x() * steps,
					movement.y() * steps
			);
			var x = start.x() + overallVector.x();
			var y = start.y() + overallVector.y();
			x = x % grid[0].length;
			y = y % grid.length;
			return new Coordinate(
					convertIndex(x, grid[0].length),
					convertIndex(y, grid.length)
			);
		}

		private int convertIndex(long val, long len) {
			if (val >= 0) {
				return (int) val;
			}
			return (int) (len + val);
		}
	}
}
