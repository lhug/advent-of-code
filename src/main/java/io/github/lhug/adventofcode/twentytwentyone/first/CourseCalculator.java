package io.github.lhug.adventofcode.twentytwentyone.first;

import java.util.List;

public class CourseCalculator {

    private long hPosition = 0;
    private long depth = 0;
    private long aim = 0;

    public void plot(List<String> input) {
        input.stream()
                .map(s -> s.split("\\s"))
                .map(ar -> new Movement(ar[0], Integer.parseInt(ar[1])))
                .forEach(this::modifyPosition);
    }

    record Movement(String direction, int value){}

    private void modifyPosition(Movement input) {
        switch (input.direction) {
            case "up" -> aim -= input.value;
            case "down" -> aim += input.value;
            default -> updatePosition(input.value);
        }
    }

    private void updatePosition(int value) {
        hPosition += value;
        depth += value * aim;
    }

    public long hPosition() {
        return hPosition;
    }

    public long depth() {
        return depth;
    }

    public long aim() {
        return aim;
    }
}
