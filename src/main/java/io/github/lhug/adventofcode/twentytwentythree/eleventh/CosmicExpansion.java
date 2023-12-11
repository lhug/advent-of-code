package io.github.lhug.adventofcode.twentytwentythree.eleventh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CosmicExpansion {

    private final String base;
    public CosmicExpansion(String input) {
        this.base = input;
    }

    public String expand() {
        String expandedRows = internalExpand(base, "V");
        var transposed = transpose(expandedRows);
        var fullyExpanded = internalExpand(transposed, ">");
        return transpose(fullyExpanded);
    }

    private String transpose(String offer) {
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

    private char[][] toMatrix(String in) {
        return in.lines()
                .map(String::strip)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private String toString(char[][] matrix) {
        return Arrays.stream(matrix)
                .map(String::new)
                .collect(Collectors.joining("\n"));
    }

    private String internalExpand(String in, String v) {
        var rows = in.split("\n");
        List<String> newRows = new ArrayList<>();
        for (String currentRow : rows) {
            if (!currentRow.contains("#")) {
                newRows.add(v.repeat(currentRow.length()));
            } else {
                newRows.add(currentRow);
            }
        }
        return String.join("\n", newRows);
    }

    public List<Coordinate> findCoordinates(String input, int expand) {
        List<Coordinate> result = new ArrayList<>();
        var matrix = toMatrix(input);
        int multY = 0;
        for (int i = 0; i < matrix.length; i++) {
            boolean added = false;
            int multX = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                char current = matrix[i][j];
                switch (current) {
                    case '>' -> multX += expand - 1;
                    case 'V' -> {
                        if (!added) {
                            added = true;
                            multY += expand - 1;
                        }
                    }
                    case '#' -> result.add(new Coordinate(i + multY, j + multX));
                }
            }
        }
        return result;
    }

    public long pathLength(Coordinate from, Coordinate to) {
        long diffA = Math.abs(from.x - to.x);
        long diffB = Math.abs(from.y - to.y);
        return diffA + diffB;
    }

    public long findPathsWithExpansion(int i) {
        String newGalaxy = expand();
        var galaxyCoordinates = findCoordinates(newGalaxy, i);
        long result = 0;
        int start = 1;
        for (Coordinate coordinate: galaxyCoordinates) {
            for(int count = start; count < galaxyCoordinates.size(); count++) {
                result += pathLength(coordinate, galaxyCoordinates.get(count));
            }
            start++;
        }
        return result;
    }

    public record Coordinate(int y, int x) {
    }
}
