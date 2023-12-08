package io.github.lhug.adventofcode.twentytwentythree.eigth;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class HauntedWasteland {

    private final List<Direction> instructionLoop = new ArrayList<>();
    private final Map<String, Fork> ways = new HashMap<>();
    public HauntedWasteland(String input) {
        var lines = input.split("\\n");
        parseInstructions(lines[0]);
        lines[0] = "";
        parseWays(lines);
    }

    private void parseInstructions(String input) {
        instructionLoop.clear();
        for (char c : input.toCharArray()) {
            instructionLoop.add(c == 'R'
                ? Direction.RIGHT
                : Direction.LEFT);
        }
    }

    private void parseWays(String[] input) {
        record Pair(String key, Fork value){}
        ways.clear();
        Arrays.stream(input)
                .filter(Predicate.not(String::isBlank))
                .map(line -> {
                    var meta = line.split(" = ");
                    var directions = meta[1].split(", ");
                    return new Pair(
                            meta[0],
                            new Fork(
                                    directions[0].substring(1),
                                    directions[1].substring(0, directions[1].length() - 1)
                            )
                    );
                })
                .forEach(pair -> ways.put(pair.key(), pair.value()));
    }

    public List<Direction> instructionLoop() {
        return instructionLoop;
    }

    public Map<String, Fork> ways() {
        return ways;
    }

    public long stepCount() {
        var path = findPath("AAA", "ZZZ");
        return path.size();
    }

    public List<String> findPath(String start, String end) {
        int index = 0;
        List<String> steps = new ArrayList<>();
        String currentStep = start;
        while (!currentStep.equals(end)) {
            if(index >= instructionLoop.size()) {
                index = 0;
            }
            Direction direction = instructionLoop.get(index++);
            Fork fork = ways.get(currentStep);
            currentStep = direction.nextStep(fork);
            steps.add(currentStep);
        }
        return steps;
    }

    public enum Direction {
        LEFT(Fork::left),
        RIGHT(Fork::right);

        private final Function<Fork, String> stepper;

        Direction(Function<Fork, String> stepper) {
            this.stepper = stepper;
        }

        String nextStep(Fork fork) {
            return stepper.apply(fork);
        }
    }

    public record Fork(String left, String right) {
    }
}
