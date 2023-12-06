package io.github.lhug.adventofcode.twentytwentythree.sixth;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class BoatRace {

    private final List<SingleRace> races = new ArrayList<>();
    public int numberOfWinningScenarios(SingleRace race) {
        int count = 0;
        for (int i = 0; i < race.time(); i++) {
            int result = i * (race.time() - i);
            if (result > race.distance())
                count++;
        }
        return count;
    }

    public void parse(String input) {
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

    public int errorMargin(List<SingleRace> races) {
        return races.stream()
                .mapToInt(this::numberOfWinningScenarios)
                .reduce(1, Math::multiplyExact);
    }
}
