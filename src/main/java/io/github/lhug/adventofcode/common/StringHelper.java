package io.github.lhug.adventofcode.common;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringHelper {
    public static String transpose(String offer) {
        var matrix = toMatrix(offer);
        int rows = matrix.length;
        int columns = matrix[0].length;
        char[][] result = new char[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return toString(result);
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
