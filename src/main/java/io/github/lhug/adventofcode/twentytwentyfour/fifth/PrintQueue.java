package io.github.lhug.adventofcode.twentytwentyfour.fifth;

import java.util.Arrays;
import java.util.List;

public class PrintQueue {

    final List<PrintRule> rules;
    final List<int[]> rows;

    public PrintQueue(String input) {
        var parts = input.split("\\n\\n", 2);
        rules = parseRules(parts[0]);
        rows = parseData(parts[1]);
    }

    private List<PrintRule> parseRules(String input) {
        return input.lines()
                .map(PrintRule::from)
                .toList();
    }

    private List<int[]> parseData(String input) {
        return input.lines()
                .map(line -> line.split(","))
                .map(parts -> Arrays.stream(parts).mapToInt(Integer::parseInt).toArray())
                .toList();
    }


    List<int[]> validRows() {
        return rows.stream()
                .filter(row -> rules.stream().allMatch(rule -> rule.matches(row) != PrintRule.Result.FAIL))
                .toList();
    }

    static int middleValue(int[] row) {
        return row[row.length / 2];
    }

    public long phaseOne() {
        return validRows().stream()
                .mapToLong(PrintQueue::middleValue)
                .sum();
    }
}
