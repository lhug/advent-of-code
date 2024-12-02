package io.github.lhug.adventofcode.twentytwentyfour.second;

import java.util.Arrays;
import java.util.List;

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
        Diff current = Diff.INITIAL;
        for (int i = 0; i < data.length - 1; i++) {
            var diff = data[i] - data[i + 1];
            switch (diff) {
                case 1, 2, 3:
                    if (current == Diff.DEC) {
                        return false;
                    }
                    current = Diff.INC;
                    break;
                case -1, -2, -3:
                    if (current == Diff.INC) {
                        return false;
                    }
                    current = Diff.DEC;
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    enum Diff {
        INC, DEC, INITIAL
    }

    public long safeSequences() {
        return data.stream()
                .map(this::isSafe)
                .filter(Boolean.TRUE::equals)
                .count();
    }
}
