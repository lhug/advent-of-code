package io.github.lhug.adventofcode.twentytwentythree.tenth;

import java.util.ArrayList;
import java.util.List;

public class PipeMaze {

    public long farthest(String input) {
        var map = toNavigableArray(input);
        var start = findStart(map);
        var paths = findConnectedPoints(map, start);
        int stepCount = 1;
        List<Coordinate> from = new ArrayList<>();
        List<Coordinate> to = new ArrayList<>();
        paths.stream().map(Path::from).forEach(from::add);
        paths.stream().map(Path::to).forEach(to::add);
        while(to.stream().distinct().count() != 1) {
            for (int i = 0; i < to.size(); i++) {
                var currentStep = to.get(i);
                var p = new Path(from.get(i), currentStep, nextSymbol(map, currentStep));
                var nextStep = next(p);
                from.set(i, currentStep);
                to.set(i, nextStep);
            }
            stepCount++;
        }
        return stepCount;
    }

    public long pointsInArea(String input) {
        var map = toNavigableArray(input);
        var start = findStart(map);
        var paths = findConnectedPoints(map, start);
        replaceStartNode(start, paths.get(0).to(), paths.get(1).to(), map);
        var nodes = findNodes(map, paths.get(0));
        var area = area(nodes);
        long points = farthest(input) * 2;
        return picksTheorem(area, points);
    }

    private long picksTheorem(long area, long nodeCount) {
        long boundaryPoints = nodeCount / 2;
        return area - boundaryPoints + 1;
    }

    private long area(List<Coordinate> coordinates) {
        long area = 0;
        for (int i = 0; i < coordinates.size() - 1; i++) {
            var right = coordinates.get(i);
            var left = coordinates.get(i+1);
            area += ((long) right.x * left.y) - ((long) left.x * right.y);
        }
        var last = coordinates.getLast();
        var first = coordinates.getFirst();
        area += ((long) last.x * first.y) - ((long) first.x * last.y);
        return Math.abs(area) / 2;
    }

    private void replaceStartNode(Coordinate start, Coordinate first, Coordinate second, String[][] map) {
        int diffOne = first.y() - second.y();
        int diffTwo = first.x() - second.x();
        String symbol = getSymbol(diffOne, diffTwo);
        map[start.y][start.x] = symbol;
    }

    private String getSymbol(int diffY, int diffX) {
        if(diffY == 0) {
            return "-";
        }
        if (diffX == 0) {
            return "|";
        }
        if (diffY < 0) {
            return diffX < 0
                    ? "7"
                    : "F";
        } else {
            return diffX < 0
                    ? "J"
                    : "L";
        }
    }

    private String[][] toNavigableArray(String input) {
        return input.lines().map(line -> line.split(""))
                .toArray(String[][]::new);
    }

    private Coordinate findStart(String[][] navigableArray) {
        for (int i = 0; i < navigableArray.length; i++) {
            var line = navigableArray[i];
            for (int j = 0; j < line.length; j++) {
                var item = line[j];
                if("S".equals(item)) {
                    return new Coordinate(i,j);
                }
            }
        }
        throw new IllegalArgumentException("No starting position found");
    }

    private static List<Path> findConnectedPoints(String[][] map, Coordinate start) {
        List<Path> results = new ArrayList<>();
        int x = start.x();
        int y = start.y();
        if(x - 1 >= 0) {
            String symbol = map[y][x-1];
            if(List.of("L", "-", "F").contains(symbol)) {
                results.add(new Path(start, new Coordinate(y, x-1), symbol));
            }
        }
        if(x + 1 <= map[0].length) {
            String symbol = map[y][x+1];
            if(List.of("J", "-", "7").contains(symbol)) {
                results.add(new Path(start, new Coordinate(y, x+1), symbol));
            }
        }
        if(y - 1 >= 0) {
            String symbol = map[y-1][x];
            if(List.of("7", "|", "F").contains(symbol)) {
                results.add(new Path(start, new Coordinate(y-1, x), symbol));
            }
        }
        if(y + 1 <= map.length) {
            String symbol = map[y+1][x];
            if(List.of("J", "|", "L").contains(symbol)) {
                results.add(new Path(start, new Coordinate(y+1, x), symbol));
            }
        }
        return results;
    }

    private Coordinate next(Path path) {
        return switch (path.connector) {
            case "-" -> path.from.x() < path.to.x()
                    ? new Coordinate(path.to.y(), path.to.x() + 1)
                    : new Coordinate(path.to.y(), path.to.x() - 1);
            case "J" -> path.from.x() < path.to().x()
                    ? new Coordinate(path.to.y() - 1, path.to.x())
                    : new Coordinate(path.to.y(), path.to.x() - 1);
            case "7" ->  path.from.x() < path.to.x()
                    ? new Coordinate(path.to.y() + 1, path.to.x())
                    : new Coordinate(path.to.y(), path.to.x() - 1);
            case "|" -> path.from.y() < path.to.y()
                    ? new Coordinate(path.to.y() + 1, path.to.x())
                    : new Coordinate(path.to.y() - 1, path.to.x());
            case "L" -> path.from.y() < path.to.y()
                    ? new Coordinate(path.to.y(), path.to.x() + 1)
                    : new Coordinate(path.to.y() - 1, path.to.x());
            case "F" -> path.from.y() > path.to.y()
                    ? new Coordinate(path.to.y(), path.to.x() + 1)
                    : new Coordinate(path.to.y() + 1, path.to.x());
            default ->
                    throw new IllegalArgumentException("No valid path from " + path.from() + " to " + path.to() + " using connector " + path.connector);
        };
    }

    private List<Coordinate> findNodes(String[][] map, Path first) {
        List<String> nodeSymbols = List.of("7", "L", "J", "F");
        List<Coordinate> nodes = new ArrayList<>();
        Coordinate start = first.from;
        if(nodeSymbols.contains(nextSymbol(map, start))) {
            nodes.add(start);
        }
        Path current = first;
        while(!current.to.equals(start)) {
            if(nodeSymbols.contains(current.connector())) {
                nodes.add(current.to);
            }
            Coordinate next = next(current);
            String symbol = nextSymbol(map, next);
            current = new Path(current.to, next, symbol);
        }
        return nodes;
    }

    private String nextSymbol(String[][] map, Coordinate coordinate) {
        return map[coordinate.y()][coordinate.x()];
    }

    record Path(Coordinate from, Coordinate to, String connector) {}

    record Coordinate(int y, int x) {}
}
