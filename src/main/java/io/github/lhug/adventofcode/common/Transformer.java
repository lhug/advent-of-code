package io.github.lhug.adventofcode.common;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Transformer {
    public static String matrixTranspose(String offer) {
        var matrix = toMatrix(offer);
        var transposed = transpose(matrix);
        return toString(transposed);
    }

    public static char[][] transpose(char[][] in) {
        int rows = in.length;
        int columns = in[0].length;
        var result = new char[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = in[i][j];
            }
        }
        return result;
    }

    public static char[][] toMatrix(String in) {
        return in.lines()
                .map(String::strip)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static String toString(char[][] matrix) {
        return Arrays.stream(matrix)
                .map(String::new)
                .collect(Collectors.joining("\n"));
    }
}
