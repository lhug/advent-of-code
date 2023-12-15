package io.github.lhug.adventofcode.twentytwentythree.thirteenth;

import io.github.lhug.adventofcode.common.Transformer;

import java.util.Arrays;
import java.util.List;

public class PointOfIncidence {

    private final List<MirrorMaze> mirrors;

    public List<MirrorMaze> mirrors() {
        return mirrors;
    }

    public long calculateMirrorLines() {
        return mirrors.stream()
                .map(mirror -> new Pair(
                        countLinesBeforeMirror(mirror.lines(), 0),
                        countLinesBeforeMirror(mirror.columns(), 0)
                ))
                .mapToLong(Pair::join)
                .sum();
    }

    public long calculateSmudgedLines() {
        return mirrors.stream()
                .map(mirror -> new Pair(
                        countLinesBeforeMirror(mirror.lines(), 1),
                        countLinesBeforeMirror(mirror.columns(), 1)
                ))
                .mapToLong(Pair::join)
                .sum();
    }

    public record MirrorMaze(String[] lines, String[] columns){
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MirrorMaze that = (MirrorMaze) o;
            return Arrays.equals(lines, that.lines) && Arrays.equals(columns, that.columns);
        }

        @Override
        public int hashCode() {
            int result = Arrays.hashCode(lines);
            result = 31 * result + Arrays.hashCode(columns);
            return result;
        }
    }
    public PointOfIncidence(String input) {
        this.mirrors = parse(input);
    }

    private List<MirrorMaze> parse(String input) {
        var parts = input.split("\n\n");
        return Arrays.stream(parts)
                .map(this::parseSet)
                .toList();
    }

    private MirrorMaze parseSet(String input) {
        var lines = input.lines().toArray(String[]::new);
        var columns = Transformer.matrixTranspose(input).lines().toArray(String[]::new);
        return new MirrorMaze(lines, columns);
    }

    public long countLinesBeforeMirror(String[] lines, int errorCount) {
        for (int i = 0; i < lines.length -1; i++) {
            var left = lines[i];
            var right = lines[i+1];
            if(mirrorsWithErrors(left, right) <= errorCount) {
                if (mirrorsFrom(i, lines, errorCount)) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    private int mirrorsWithErrors(String left, String right) {
        int errorCounter = 0;
        for (int i = 0; i < left.length(); i++) {
            if(left.charAt(i) != right.charAt(i)) {
                errorCounter++;
            }
        }
        return errorCounter;
    }

    private boolean mirrorsFrom(int start, String[] lines, int expectedErrors) {
        int errorCount = 0;
        for (int i = start, j = start + 1; i >= 0 && j < lines.length; i--, j++) {
            var left = lines[i];
            var right = lines[j];
            errorCount += mirrorsWithErrors(left, right);
            if(errorCount > expectedErrors) {
                return false;
            }
        }
        return errorCount == expectedErrors;
    }

    record Pair(long left, long right) {
        long join() {
            return (100 * left) + right;
        }
    }

}
