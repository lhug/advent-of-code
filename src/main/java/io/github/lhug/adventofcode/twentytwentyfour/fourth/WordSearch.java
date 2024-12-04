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
                    count += checkXmas(x, y, WordDirection.UP);
                    count += checkXmas(x, y, WordDirection.DOWN);
                    count += checkXmas(x, y, WordDirection.LEFT);
                    count += checkXmas(x, y, WordDirection.RIGHT);
                    count += checkXmas(x, y, WordDirection.UP_RIGHT);
                    count += checkXmas(x, y, WordDirection.UP_LEFT);
                    count += checkXmas(x, y, WordDirection.DOWN_RIGHT);
                    count += checkXmas(x, y, WordDirection.DOWN_LEFT);
                }
            }
        }
        return count;
    }

    private long checkXmas(int x, int y, WordDirection wordDirection) {
        if(isInRange(x, y, wordDirection)) {
            var coords = getCoordinates(x, y, wordDirection);
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

    private boolean isInRange(int x, int y, WordDirection wordDirection) {
        return
                x + wordDirection.x >= 0 &&
                x + wordDirection.x < length &&
                y + wordDirection.y >= 0 &&
                y + wordDirection.y < height;
    }

    private Coordinates getCoordinates(int x, int y, WordDirection wordDirection) {
        return new Coordinates(
                toArray(x, x + wordDirection.x),
                toArray(y, y + wordDirection.y)
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

    enum WordDirection {
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

        WordDirection(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public long countX_mas() {
        var count = 0L;
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < length - 1; x++) {
                char current = grid[y][x];
                if (current == 'A' && isDiagonalXmas(x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isDiagonalXmas(int x, int y) {
        char[] uldr = new char[3];
        char[] dlur = new char[3];
        uldr[0] = grid[y - 1][x - 1];
        uldr[1] = grid[y][x];
        uldr[2] = grid[y + 1][x + 1];

        dlur[0] = grid[y + 1][x - 1];
        dlur[1] = grid[y][x];
        dlur[2] = grid[y - 1][x + 1];

        return isMas(uldr) && isMas(dlur);
    }

    private boolean isMas(char[] in) {
        var string = new String(in);
        return "MAS".equals(string) || "SAM".equals(string);
    }


}
