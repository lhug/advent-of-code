package io.github.lhug.adventofcode.twentytwentythree.sixteenth;

import io.github.lhug.adventofcode.common.Direction;
import io.github.lhug.adventofcode.common.Transformer;

import java.util.*;

public class FloorIsLava {

    private final char[][] grid;
    public FloorIsLava(String input) {
        this.grid = Transformer.toMatrix(input);
    }

    public long energized() {
        return findPathFrom(new Light(-1, 0, Direction.EAST));
    }

    public long idealEnergized() {
        int width = grid[0].length;
        int height = grid.length;
        long result = 0L;
        for (int x = 0; x < width; x++) {
            var source = new Light(x, -1, Direction.SOUTH);
            result = Math.max(result, findPathFrom(source));
            source = new Light(x, height, Direction.NORTH);
            result = Math.max(result, findPathFrom(source));
        }
        for (int y = 0; y < height; y++) {
            var source = new Light(-1, y, Direction.EAST);
            result = Math.max(result, findPathFrom(source));
            source = new Light(width, y, Direction.WEST);
            result = Math.max(result, findPathFrom(source));
        }
        return result;
    }

    record Beam(Set<Direction> directions) {
        Beam() {
            this(new HashSet<>());
        }

        boolean add(Direction direction) {
            return directions.add(direction);
        }

        boolean isEnergized() {
            return !directions.isEmpty();
        }
    }

    public record Light(int x, int y, Direction direction) {
        List<Light> reflect(char reflector) {
            return switch (reflector) {
                case '/' -> switch (direction) {
                    case NORTH -> reflectTo(Direction.EAST);
                    case EAST -> reflectTo(Direction.NORTH);
                    case WEST -> reflectTo(Direction.SOUTH);
                    case SOUTH -> reflectTo(Direction.WEST);
                };
                case '\\' -> switch (direction) {
                    case NORTH -> reflectTo(Direction.WEST);
                    case EAST -> reflectTo(Direction.SOUTH);
                    case SOUTH -> reflectTo(Direction.EAST);
                    case WEST -> reflectTo(Direction.NORTH);
                };
                case '|' -> switch (direction) {
                    case EAST, WEST -> reflectTo(Direction.NORTH, Direction.SOUTH);
                    case NORTH, SOUTH -> reflectTo(direction);
                };
                case '-' -> switch (direction) {
                    case EAST, WEST -> reflectTo(direction);
                    case NORTH, SOUTH -> reflectTo(Direction.EAST, Direction.WEST);
                };
                case '.' -> reflectTo(direction);
                default -> throw new IllegalArgumentException("Illegal reflector encountered");
            };
        }

        private int newX() {
            return this.x + direction.diffX();
        }

        private int newY() {
            return this.y + direction.diffY();
        }

        private List<Light> reflectTo(Direction... directions) {
            List<Light> results = new ArrayList<>();
            for(Direction d : directions) {
                results.add(new Light(newX(), newY(), d));
            }
            return results;
        }
    }

    public long findPathFrom(Light initial) {
        Beam[][] beams = initBeams();
        moveAlong(initial, beams);
        long result = 0L;
        for (int y = 0; y < beams.length; y++) {
            for (int x = 0; x < beams[0].length; x++) {
                if(beams[y][x].isEnergized()) {
                    result++;
                }
            }
        }
        return result;
    }

    private Beam[][] initBeams() {
        int width = grid[0].length;
        int height = grid.length;
        Beam[][] beams = new Beam[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                beams[y][x] = new Beam();
            }
        }
        return beams;
    }

    private void moveAlong(Light source, Beam[][] beams) {
        int x = source.newX();
        int y = source.newY();
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
            return;
        }
        if (beams[y][x].add(source.direction)) {
            char reflector = grid[y][x];
            var newSources = source.reflect(reflector);
            for(Light newSource : newSources) {
                moveAlong(newSource, beams);
            }
        }

    }


}
