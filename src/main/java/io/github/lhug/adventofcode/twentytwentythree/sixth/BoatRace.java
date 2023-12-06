package io.github.lhug.adventofcode.twentytwentythree.sixth;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoatRace {

    private final List<SingleRace> races = new ArrayList<>();
    public long numberOfWinningScenarios(SingleRace race) {
        long count = 0;
        for (int i = 0; i < race.time(); i++) {
            long result = i * (race.time() - i);
            if (result > race.distance())
                count++;
        }
        return count;
    }

    public void parseManyRaces(String input) {
        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        var scanner = new Scanner(input);
        scanner.next("Time:");
        while(scanner.hasNextInt()) {
            times.add(scanner.nextInt());
        }
        scanner.next("Distance:");
        while(scanner.hasNextInt()) {
            distances.add(scanner.nextInt());
        }
        for (int i = 0; i < times.size(); i++) {
            races.add(new SingleRace(times.get(i), distances.get(i)));
        }
    }

    public List<SingleRace> races() {
        return races;
    }

    public long errorMargin(List<SingleRace> races) {
        return races.stream()
                .mapToLong(this::numberOfWinningScenarios)
                .reduce(1, Math::multiplyExact);
    }

    public SingleRace parseRace(String input) {
        long[] result = input.lines()
                .map(line -> line.split(": "))
                .map(parts -> parts[1])
                .map(numbers -> numbers.replace(" ", ""))
                .mapToLong(Long::parseLong)
                .toArray();
        return new SingleRace(result[0], result[1]);
    }
}
