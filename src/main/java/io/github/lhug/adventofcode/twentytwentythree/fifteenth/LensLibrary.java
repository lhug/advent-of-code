package io.github.lhug.adventofcode.twentytwentythree.fifteenth;

import java.util.Arrays;

public class LensLibrary {
    public long hashFor(String in) {
        return in.chars().reduce(0, (l, r) -> (((l+r)*17)%256));
    }

    public long hashSumFor(String input) {
        return Arrays.stream(input.split(","))
                .mapToLong(this::hashFor)
                .sum();
    }
}
