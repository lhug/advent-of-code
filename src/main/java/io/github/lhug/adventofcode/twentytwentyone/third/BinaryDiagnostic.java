package io.github.lhug.adventofcode.twentytwentyone.third;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class BinaryDiagnostic {

    private static final BiPredicate<Integer, Integer> REMOVE_IF_EQUAL = Objects::equals;

    private List<Integer> values;
    private int mask;

    private int bits;

    private int gamma;
    private int epsilon;
    private int oxygenRating;
    private int co2Rating;


    public int gamma() {
        return gamma;
    }

    public int epsilon() {
        return epsilon;
    }

    public int oxygenRating() {
        return oxygenRating;
    }

    public int co2Rating() {
        return co2Rating;
    }

    public void parse(List<String> input) {
        bits = input.get(0).length();
        mask = Integer.parseInt("1".repeat(bits), 2);
        values = input.stream()
                .mapToInt(s -> Integer.parseInt(s, 2))
                .boxed()
                .collect(Collectors.toList());
        determineGamma();
        determineEpsilon();
        determineOxygenRating();
        determineCo2Rating();
    }

    private void determineGamma() {
        StringBuilder b = new StringBuilder();
        for(var i = 0; i < bits; i++) {
           b.append(significantBitAtPosition(values, i));
        }
        gamma = Integer.parseInt(b.toString(), 2);
    }

    private int significantBitAtPosition(List<Integer> sample, int bitPosition) {
        int pos = bits - 1 - bitPosition;
        int sum = sample.stream()
                .mapToInt(num -> getBit(num, pos))
                .sum();
        return isSignificant(sum, sample.size()) ? 1 : 0;
    }

    private static int getBit(int number, int bitAt) {
        return (number >> bitAt) & 1;
    }

    private static boolean isSignificant(int val, int sampleSize) {
        return val >= sampleSize - val;
    }

    private void determineEpsilon() {
        epsilon = gamma ^ mask;
    }

    private void determineOxygenRating() {
        oxygenRating = determineRating(REMOVE_IF_EQUAL.negate());
    }

    private int determineRating(BiPredicate<Integer, Integer> filter) {
        List<Integer> sample = new ArrayList<>(values);
        for(var i = 0; i < bits && sample.size() != 1; i++) {
            int significantBit = significantBitAtPosition(sample, i);
            final int pos = bits - i - 1;
            sample.removeIf(val -> filter.test(getBit(val, pos), significantBit));
        }
        return sample.get(0);
    }

    private void determineCo2Rating() {
        co2Rating=  determineRating(REMOVE_IF_EQUAL);
    }
}
