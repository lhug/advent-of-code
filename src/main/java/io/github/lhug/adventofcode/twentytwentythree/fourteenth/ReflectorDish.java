package io.github.lhug.adventofcode.twentytwentythree.fourteenth;

import io.github.lhug.adventofcode.common.Transformer;

import java.util.*;
import java.util.stream.LongStream;

public class ReflectorDish {

    private final String data;
    public ReflectorDish(String input) {
        this.data = input;
    }

    public long loadAfterCycling() {
        var matrix = Transformer.toMatrix(data);
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < 1_000_000_000; i++) {
            tiltCycle(matrix);
            var config = Transformer.toString(matrix);
            if(results.containsKey(config)) {
                var delta = i - results.get(config);
                i += delta * ((1_000_000_000 - i) / delta);
            }
            results.put(config, i);
        }
        return calculateLoad(matrix);
    }

    private static void tiltCycle(char[][] matrix) {
        tiltNorth(matrix);
        tiltWest(matrix);
        tiltSouth(matrix);
        tiltEast(matrix);
    }

    private record Coordinate(int y, int x) {}
    
    public long tilt() {
        var matrix = Transformer.toMatrix(data);
        tiltNorth(matrix);
        return calculateLoad(matrix);
    }

    private long calculateLoad(char[][] matrix) {
        long result = 0L;
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                var current = matrix[y][x];
                if(current == 'O') {
                    result += matrix.length - y;
                }
            }
        }
        return result;
    }

    public static void tiltNorth(char[][] in) {
        for (int x = 0; x < in[0].length; x++) {
            int openPosition = 0;
            for (int y = 0; y < in.length; y++) {
                char current = in[y][x];
                if(current == '#') {
                    openPosition = y + 1;
                } else if (current == 'O') {
                    if(y != openPosition) {
                        in[openPosition++][x] = 'O';
                        in[y][x] = '.';
                    } else {
                        openPosition++;
                    }
                }
            }
        }
    }

    public static void tiltSouth(char[][] in) {
        for (int x = 0; x < in[0].length; x++) {
            int openPosition = in.length - 1;
            for (int y = in.length - 1; y >= 0; y--) {
                char current = in[y][x];
                if(current == '#') {
                    openPosition = y - 1;
                } else if (current == 'O') {
                    if(y != openPosition) {
                        in[openPosition--][x] = 'O';
                        in[y][x] = '.';
                    } else {
                        openPosition--;
                    }
                }
            }
        }
    }

    public static void tiltWest(char[][] in) {
        for (int y = 0; y < in.length; y++) {
            int openPosition = 0;
            for (int x = 0; x < in[0].length; x++) {
                char current = in[y][x];
                if(current == '#') {
                    openPosition = x + 1;
                } else if (current == 'O') {
                    if(x != openPosition) {
                        in[y][openPosition++] = 'O';
                        in[y][x] = '.';
                    } else {
                        openPosition++;
                    }
                }
            }
        }
    }

    public static void tiltEast(char[][] in) {
        for (int y = 0; y < in.length; y++) {
            int openPosition = in[0].length - 1;
            for (int x = in[0].length - 1; x >= 0; x--) {
                char current = in[y][x];
                if(current == '#') {
                    openPosition = x - 1;
                } else if (current == 'O') {
                    if(x != openPosition) {
                        in[y][openPosition--] = 'O';
                        in[y][x] = '.';
                    } else {
                        openPosition--;
                    }
                }
            }
        }
    }

    /*
     * First attempt at solving. This calculates the load depending on the amount of
     * 'O' in a column following any '#'. This yields a higher result than desired;
     * Maybe find out why.
     */
    @SuppressWarnings("unused")
    private long calculateLoad() {
        var matrix = Transformer.toMatrix(data);
        Map<Coordinate, Long> values = new LinkedHashMap<>();
        long count = 0L;
        Coordinate currentCoordinate = null;
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                if (y == 0) {
                    currentCoordinate = new Coordinate(0, x);
                }
                var current = matrix[y][x];
                if(current == '#') {
                    if (count != 0L) {
                        values.put(currentCoordinate, count);
                        count = 0L;
                    }
                    currentCoordinate = new Coordinate(y, x);
                } else if (current == 'O') {
                    count++;
                }
            }
            if (count != 0L) {
                values.put(currentCoordinate, count);
                count = 0L;
            }
        }
        long result = 0L;
        int max = matrix[0].length;
        for(Map.Entry<Coordinate, Long> entry : values.entrySet()) {
            var key = entry.getKey();
            result += LongStream.range(0, entry.getValue())
                    .map(stone -> max - row(key.y()) - stone)
                    .sum();
        }
        return result;
    }

    private static int row(int index) {
        return index == 0
                ? index
                : index + 1;
    }


}
