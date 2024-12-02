package io.github.lhug.adventofcode.twentytwentyfour.second;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ReactorParameters {

    private final List<int[]> data;

    public ReactorParameters(String input) {
        data = input.lines()
                .map(line -> line.split(" "))
                .map(this::convert)
                .toList();
    }

    private int[] convert(String[] in) {
        return Arrays.stream(in)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public boolean isSafe(int[] data) {
        int[] results = new int[data.length - 1];
        for (int i = 0; i < data.length - 1; i++) {
            results[i] = data[i] - data[i + 1];
        }
        return Arrays.stream(results)
                .allMatch(i -> i < 0 && i > -4)
                ||
                Arrays.stream(results)
                .allMatch(i -> i > 0 && i < 4);
    }

    public long safeSequences() {
        return countSequences(this::isSafe);
    }

    private long countSequences(Function<int[], Boolean> checker) {
        return data.stream()
                .map(checker)
                .filter(Boolean.TRUE::equals)
                .count();
    }

    public boolean isSafeWithBuffer(int[] data) {
        if (isSafe(data)) {
            return true;
        }
        for (int i = 0; i < data.length; i++) {
            var current = removeIndexAt(data, i);
            if(isSafe(current)) {
                return true;
            }
        }
        return false;
    }

    public long bufferedSafeSequences() {
        return countSequences(this::isSafeWithBuffer);
    }

    private int[] removeIndexAt(int[] data, int index) {
        int[] newData = new int[data.length - 1];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index + 1, newData, index, data.length - index - 1);
        return newData;
    }
}
