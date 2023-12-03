package io.github.lhug.adventofcode.twentytwentythree.third;

import java.util.*;

public class PartCalculator {

    private final char[][] cells;

    public PartCalculator(String input) {
        this.cells = cellsFrom(input);
    }
    public int partSum() {
        var coordinates = symbolLocations();
        var numbers = numbersAround(coordinates);
        return numbers.stream().reduce(0, Integer::sum);
    }

    public List<SymbolCoordinate> symbolLocations() {
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

    public List<Integer> numbersAround(List<SymbolCoordinate> symbolCoordinates) {
        return symbolCoordinates.stream()
                .map(this::numbersAround)
                .flatMap(List::stream)
                .toList();
    }

    private List<Integer> numbersAround(SymbolCoordinate coordinate) {
        List<Point> points = getPotentialPoints(coordinate);
        Set<Integer> currentNums = new LinkedHashSet<>();
        for (Point p : points) {
            char c = cells[p.y][p.x];
            if (Character.isDigit(c)) {
                StringBuilder s = new StringBuilder();
                int startIndex = startIndex(p.y, p.x);
                int endIndex = endIndex(p.y, p.x);
                for (int i = startIndex; i < endIndex; i++) {
                    s.append(cells[p.y][i]);
                }
                currentNums.add(Integer.parseInt(s.toString()));
            }
        }
        return new ArrayList<>(currentNums);
    }

    private int startIndex(int y, int x) {
        int startIndex = x;
        char current = cells[y][startIndex];
        while (Character.isDigit(current)) {
            startIndex--;
            if (startIndex < 0) {
                break;
            } else {
                current = cells[y][startIndex];
            }
        }
        return startIndex + 1;
    }

    private int endIndex(int y, int x) {
        int endIndex = x;
        char current = cells[y][endIndex];
        while (Character.isDigit(current)) {
            endIndex++;
            if (endIndex >= cells[y].length) {
                endIndex = cells[y].length;
                break;
            } else {
                current = cells[y][endIndex];
            }
        }
        return endIndex;
    }

    private static List<Point> getPotentialPoints(SymbolCoordinate coordinate) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(coordinate.y - 1, coordinate.x - 1));
        points.add(new Point(coordinate.y - 1, coordinate.x));
        points.add(new Point(coordinate.y - 1, coordinate.x + 1));
        points.add(new Point(coordinate.y, coordinate.x - 1));
        points.add(new Point(coordinate.y, coordinate.x + 1));
        points.add(new Point(coordinate.y + 1, coordinate.x - 1));
        points.add(new Point(coordinate.y + 1, coordinate.x));
        points.add(new Point(coordinate.y + 1, coordinate.x + 1));
        return points;
    }

    public int gearRatio() {
        var coordinates = symbolLocations();
        return findGears(coordinates).stream()
                .mapToInt(Gear::ratio)
                .sum();

    }

    public List<Gear> findGears(List<SymbolCoordinate> coordinates) {
        return coordinates.stream()
                .filter(coordinate -> coordinate.symbol == '*')
                .map(this::numbersAround)
                .filter(list -> list.size() == 2)
                .map(list -> new Gear(list.get(0), list.get(1)))
                .toList();
    }

    public record SymbolCoordinate(char symbol, int y, int x) {}

    private record Point(int y, int x) {}

    public record Gear(int left, int right) {
        public int ratio() {
            return left * right;
        }
    }
}
