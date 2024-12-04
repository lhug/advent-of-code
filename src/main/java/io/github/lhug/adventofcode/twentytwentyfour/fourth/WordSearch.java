package io.github.lhug.adventofcode.twentytwentyfour.fourth;

import io.github.lhug.adventofcode.common.Transformer;

import java.util.stream.IntStream;

public class WordSearch {

    private final char[][] grid;
    private final int length;
    private final int height;

    public WordSearch(String input) {
        grid = Transformer.toMatrix(input);
        length = grid[0].length;
        height = grid.length;
    }

    public long countXmas() {
        long count = 0L;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                char current = grid[y][x];
                if (current == 'X') {
                    count += checkXmas(x, y, Direction.UP);
                    count += checkXmas(x, y, Direction.DOWN);
                    count += checkXmas(x, y, Direction.LEFT);
                    count += checkXmas(x, y, Direction.RIGHT);
                    count += checkXmas(x, y, Direction.UP_RIGHT);
                    count += checkXmas(x, y, Direction.UP_LEFT);
                    count += checkXmas(x, y, Direction.DOWN_RIGHT);
                    count += checkXmas(x, y, Direction.DOWN_LEFT);
                }
            }
        }
        return count;
    }

    private long checkXmas(int x, int y, Direction direction) {
        if(isInRange(x, y, direction)) {
            var coords = getCoordinates(x, y, direction);
            var word = getWord(coords);
            if("XMAS".equals(word)) {
                return 1L;
            }
        }
        return 0L;
    }

    private String getWord(Coordinates coordinates) {
        return IntStream.range(0, 4)
                .mapToObj(i -> String.valueOf(grid[coordinates.y[i]][coordinates.x[i]]))
                .reduce("", String::concat);
    }

    private boolean isInRange(int x, int y, Direction direction) {
        return
                x + direction.x >= 0 &&
                x + direction.x < length &&
                y + direction.y >= 0 &&
                y + direction.y < height;
    }

    private Coordinates getCoordinates(int x, int y, Direction direction) {
        return new Coordinates(
                toArray(x, x + direction.x),
                toArray(y, y + direction.y)
        );
    }

    private int[] toArray(int start, int end) {
        if (Math.abs(end - start) != 3) {
            return new int[] { start, start, start, start };
        }
        var r = new int[4];
        var step = start < end ? 1 : -1;
        for (int i = 0; i < 4; i++) {
            r[i] = start + i * step;
        }
        return r;
    }

    record Coordinates(int[] x, int[] y) {
    }

    enum Direction {
        UP(0, -3),
        DOWN(0, 3),
        LEFT(-3, 0),
        RIGHT(3, 0),
        UP_RIGHT(3, -3),
        UP_LEFT(-3, -3),
        DOWN_RIGHT(3, 3),
        DOWN_LEFT(-3, 3);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
