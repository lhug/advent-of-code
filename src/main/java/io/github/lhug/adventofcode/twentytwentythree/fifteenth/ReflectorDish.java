package io.github.lhug.adventofcode.twentytwentythree.fifteenth;

import io.github.lhug.adventofcode.common.StringHelper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class ReflectorDish {

    private final String data;
    public ReflectorDish(String input) {
        this.data = input;
    }
    
    public record Coordinate(int y, int x) {}
    
    public long tilt() {
        var matrix = StringHelper.toMatrix(data);
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

    /*
     * First attempt at solving. This calculates the load depending on the amount of
     * 'O' in a column following any '#'. This yields a higher result than desired;
     * Maybe find out why.
     */
    @SuppressWarnings("unused")
    private long calculateLoad() {
        var matrix = StringHelper.toMatrix(data);
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
