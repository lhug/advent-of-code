package io.github.lhug.adventofcode.twentytwentythree.third;

import java.util.*;
import java.util.stream.IntStream;

public class PartCalculator {
    public int partSum(String input) {
        var coordinates = symbolLocations(input);
        var numbers = numbersAround(coordinates, input);
        return numbers.stream().reduce(0, Integer::sum);
    }

    public List<SymbolCoordinate> symbolLocations(String input) {
        char[][] cells = cellsFrom(input);
        List<SymbolCoordinate> results = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                char current = cells[i][j];
                if(isSymbol(current)) {
                    results.add(new SymbolCoordinate(current, i, j));
                }
            }
        }
        return results;
    }

    private static char[][] cellsFrom(String input) {
        return input.lines().map(String::toCharArray).toArray(char[][]::new);
    }

    private static boolean isSymbol(char offer) {
        return !Character.isDigit(offer) && !Objects.equals(offer, '.');
    }

    public List<Integer> numbersAround(List<SymbolCoordinate> symbolCoordinates, String input) {
        char[][] cells = cellsFrom(input);
        List<Integer> detectedNumbers = new ArrayList<>();
        for(SymbolCoordinate coordinate : symbolCoordinates) {
            List<Point> points = new ArrayList<>();
            points.add(new Point(coordinate.y - 1, coordinate.x - 1));
            points.add(new Point(coordinate.y - 1, coordinate.x));
            points.add(new Point(coordinate.y - 1, coordinate.x + 1));
            points.add(new Point(coordinate.y, coordinate.x - 1));
            points.add(new Point(coordinate.y, coordinate.x + 1));
            points.add(new Point(coordinate.y + 1, coordinate.x - 1));
            points.add(new Point(coordinate.y + 1, coordinate.x));
            points.add(new Point(coordinate.y + 1, coordinate.x + 1));
            Set<Integer> currentNums = new LinkedHashSet<>();
            for (Point p : points) {
                char c = cells[p.y][p.x];
                if (Character.isDigit(c)) {
                    StringBuilder s = new StringBuilder();
                    int startIndex = p.x;
                    char current = cells[p.y][startIndex];
                    while (Character.isDigit(current)) {
                        startIndex--;
                        if (startIndex < 0) {
                            break;
                        } else {
                            current = cells[p.y][startIndex];
                        }
                    }
                    int endIndex = p.x;
                    current = cells[p.y][endIndex];
                    while (Character.isDigit(current)) {
                        endIndex++;
                        if (endIndex >= cells[p.y].length) {
                            endIndex = cells[p.y].length;
                            break;
                        } else {
                            current = cells[p.y][endIndex];
                        }
                    }
                    for (int i = startIndex + 1; i < endIndex; i++) {
                        s.append(cells[p.y][i]);
                    }
                    currentNums.add(Integer.parseInt(s.toString()));
                }
            }
            detectedNumbers.addAll(currentNums);
        }
        return detectedNumbers;
    }

    public record SymbolCoordinate(char symbol, int y, int x) {}

    private record Point(int y, int x) {}
}
