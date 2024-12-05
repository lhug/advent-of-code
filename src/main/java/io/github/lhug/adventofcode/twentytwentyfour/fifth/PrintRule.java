package io.github.lhug.adventofcode.twentytwentyfour.fifth;

import java.util.Arrays;
import java.util.List;

public record PrintRule(int first, int second) {
    static PrintRule from(String input) {
        var parts = input.split("\\|");
        return new PrintRule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    enum Result {
        PASS,
        FAIL,
        NOT_APPLICABLE
    }

    Result matches(int[] values) {
        var list = Arrays.stream(values).boxed().toList();
        if(containsValues(list)) {
            return matches(list) ? Result.PASS : Result.FAIL;
        }
        return Result.NOT_APPLICABLE;
    }

    private boolean containsValues(List<Integer> values) {
        return values.contains(first) && values.contains(second);
    }

    private boolean matches(List<Integer> values) {
        return values.indexOf(first) < values.indexOf(second);
    }
}
