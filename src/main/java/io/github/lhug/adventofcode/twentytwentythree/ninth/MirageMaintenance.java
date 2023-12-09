package io.github.lhug.adventofcode.twentytwentythree.ninth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MirageMaintenance {

    List<List<Long>> values = new ArrayList<>();
    public MirageMaintenance(String input) {
        parse(input);
    }

    private void parse(String input) {
        input.lines()
                .map(line -> line.split(" "))
                .map(array -> Arrays.stream(array).map(Long::parseLong).toList())
                .forEach(values::add);
    }

    List<Long> getNext(List<Long> offer) {
        List<Long> res = new ArrayList<>();
        for (int i = 0; i < offer.size() - 1; i++) {
            res.add(offer.get(i + 1) - offer.get(i));
        }
        return res;
    }

    public List<List<Long>> values() {
        return values;
    }

    public long sumNextSteps() {
        return values().stream()
                .mapToLong(this::recurseToLastValue)
                .sum();
    }

    long recurseToLastValue(List<Long> input) {
        return input.isEmpty()
                ? 0
                : recurseToLastValue(getNext(input)) + input.getLast();
    }
}
