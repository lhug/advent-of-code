package io.github.lhug.adventofcode.twentytwentyfour.eleventh;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

public class PlutonianPebbles {

    List<String> numbers;

    private Map<String, String> cachedResults = new HashMap<>();

    public PlutonianPebbles(String input) {
            this.numbers = Arrays.stream(input.split(" ")).toList();
    }

    Stream<String> applyRules(String number) {
        if("0".equals(number)) {
            return Stream.of("1");
        } else if ((number.length() & 1) == 0) {
            var len = number.length();
            var left = number.substring(0, len / 2);
            var right = number.substring(len / 2);
            right = new BigInteger(right).toString();
            return Stream.of(left, right);
        } else {
            var num = new BigInteger(number);
            num = num.multiply(BigInteger.valueOf(2024L));
            return Stream.of(num.toString());
        }
    }

    public Stream<String> blink(int count) {
        Stream<String> result = numbers.stream();
        for (int i = 0; i < count; i++) {
            result = result
                    .flatMap(this::applyRules);
        }
        return result;
    }

    public long phaseOne() {
        var n = numbers.stream().map(Long::valueOf).toList();
        return countNumbersAfterPermutations(n, 25);
    }

    public long phaseTwo() {
        var n = numbers.stream().map(Long::valueOf).toList();
        return countNumbersAfterPermutations(n, 75);
    }

    // GENERATED CODE

    public long countNumbersAfterPermutations(List<Long> numbers, int steps) {
        // Initialize frequency map
        // frequency counts each number in the initial input,
        Map<Long, Long> currentNumbers = new HashMap<>();
        for (long num : numbers) {
            currentNumbers.put(num, currentNumbers.getOrDefault(num, 0L) + 1);
        }

        // Apply rules for the given number of steps
        for (int i = 0; i < steps; i++) {
            currentNumbers = processNumbers(currentNumbers);
        }

        // Sum up the total count of numbers
        long totalCount = 0;
        for (long count : currentNumbers.values()) {
            totalCount += count;
        }
        return totalCount;
    }

    public static Map<Long, Long> processNumbers(Map<Long, Long> currentNumbers) {
        Map<Long, Long> nextNumbers = new HashMap<>();

        for (Map.Entry<Long, Long> entry : currentNumbers.entrySet()) {
            long num = entry.getKey();
            long count = entry.getValue();

            if (num == 0) {
                // Rule 1: Replace 0 with 1
                nextNumbers.put(1L, nextNumbers.getOrDefault(1L, 0L) + count);
            } else if (hasEvenDigitCount(num)) {
                // Rule 2: Split into two parts
                long[] parts = splitNumber(num);
                for (long part : parts) {
                    nextNumbers.put(part, nextNumbers.getOrDefault(part, 0L) + count);
                }
            } else {
                // Rule 3: Multiply by 2024
                long multiplied = num * 2024;
                nextNumbers.put(multiplied, nextNumbers.getOrDefault(multiplied, 0L) + count);
            }
        }

        return nextNumbers;
    }

    public static boolean hasEvenDigitCount(long num) {
        int digitCount = String.valueOf(num).length();
        return digitCount % 2 == 0;
    }

    public static long[] splitNumber(long num) {
        String numStr = String.valueOf(num);
        int mid = numStr.length() / 2;

        long firstPart = Long.parseLong(numStr.substring(0, mid));
        long secondPart = Long.parseLong(numStr.substring(mid));

        return new long[]{firstPart, secondPart};
    }
}
