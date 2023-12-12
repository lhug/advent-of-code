package io.github.lhug.adventofcode.twentytwentythree.twelfth;

import org.apache.commons.lang3.function.TriFunction;

import java.util.*;

public class HotSprings {
    // solution taken and variable names refactored (so I could understand it)
    // from Simon Baars https://github.com/SimonBaars/AdventOfCode-Java/blob/master/src/main/java/com/sbaars/adventofcode/year23/days/Day12.java

    private final List<Spring> springs;

    public HotSprings(String input) {
        this.springs = parse(input);
    }

    public List<Spring> springs() {
        return springs;
    }

    private List<Spring> parse(String input) {
        return input.lines()
                .map(line -> line.split(" "))
                .map(array -> new Spring(array[0], toIntArray(array[1])))
                .toList();

    }

    private int[] toIntArray(String in) {
        return Arrays.stream(in.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public long sumMatches() {
        return springs().stream()
                .mapToLong(spring -> countArrangements(
                        new HashMap<>(),
                        spring.map(),
                        spring.amounts(),
                        0,
                        0,
                        0
                ))
                .sum();
    }

    public long sumUnfolded() {
        return springs.stream()
                .map(spring -> new Spring(unfold(spring.map()), repeat(spring.amounts(), 5)))
                .mapToLong(spring -> countArrangements(
                        new HashMap<>(),
                        spring.map(),
                        spring.amounts(),
                        0,
                        0,
                        0
                ))
                .sum();
    }

    private String unfold(String in) {
        return String.join("?", in, in, in, in, in);
    }

    public record Tuple<A, B, C>(A a, B b, C c) {
        public static <A, B, C> Tuple<A, B, C> of(A a, B b, C c) {
            return new Tuple<>(a, b, c);
        }

        public <D, E, F> Tuple<D, E, F> map(TriFunction<A, B, C, Tuple<D, E, F>> func) {
            return func.apply(a(), b(), c());
        }
    }

    public record Spring(String map, int[] amounts) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Spring spring = (Spring) o;
            return Objects.equals(map, spring.map) && Arrays.equals(amounts, spring.amounts);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(map);
            result = 31 * result + Arrays.hashCode(amounts);
            return result;
        }
    }

    /**
     * Repeats a passed int[] multiple times.
     * <p></p>
     * Passing an array {@code [1, 2, 3]} and the number 3 will yield a new array {@code [1, 2, 3, 1, 2, 3, 1, 2, 3]}.
     *
     * @param arr the array to repeat
     * @param times the number of times the initial array should be repeated
     * @return the new array
     */
    public static int[] repeat(int[] arr, int times) {
        int newLength = arr.length * times;
        int[] dup = Arrays.copyOf(arr, newLength);
        for (int last = arr.length; last != 0 && last < newLength; last <<= 1) {
            System.arraycopy(dup, 0, dup, last, Math.min(last << 1, newLength) - last);
        }
        return dup;
    }

    /**
     * Counts possible valid arrangements of broken gear groups.
     * <p></p>
     * More specifically, this recursively checks each map position for its character.
     * The passed {@code mapPosition} corresponds to the character index of the passed map, the
     * {@code groupIndex} refers to the current index of the passed amounts, and the {@code currentGroupSize} refers to
     * the currently encountered number of "broken" gears.
     * <p></p>
     * Each examination is cached in the passed {@code blockMap} meaning that the results of each examination will be returned immediately.
     * <p></p>
     * If the end of the passed map is reached a final check is performed, if:
     * <ul>
     *     <li>all groups have been examined, and the current group size is zero</li>
     *     <li>all groups have been examined, and the current group size is equal to the size of the last group</li>
     * </ul>
     * then a valid configuration is found, and 1 is returned.
     * Else, the found configuration is invalid and 0 is returned.
     * <p></p>
     * Each character is being examined by itself.
     * If the current character is either {@code ?} or {@code .}, and the current group size is zero, the next character is examined.
     * If the current group is not the last group and the current group size matches the group size defined at the current group index,
     * the next character is examined and the group index is also increased, meaning the examination now considers the next group size.
     * The results of these examinations are added to the current total.
     * Then, if the current character is either {@code ?} or {@code #}, the next character is being examined and the current group size is increased by one.
     * The results of this examination will also be added to the total.
     * Finally, the aggregated total is returned.
     *
     * @param blockMap a map to cache the results
     * @param map the string containing the input to be examined
     * @param amounts the array of group sizes as defined in the puzzle input
     * @param mapPosition the current position on the index string
     * @param groupIndex the current index of the group amounts
     * @param currentGroupSize the amount of "broken" gears to consider for the current group
     * @return the total number of valid configurations.
     */
    public long countArrangements(
            HashMap<Tuple<Integer, Integer, Integer>, Long> blockMap,
            String map,
            int[] amounts,
            int mapPosition,
            int groupIndex,
            int currentGroupSize)
    {
        var key = new Tuple<>(mapPosition, groupIndex, currentGroupSize);
        if (blockMap.containsKey(key)) {
            return blockMap.get(key);
        }
        if (mapPosition == map.length()) {
            return (groupIndex == amounts.length && currentGroupSize == 0) || (groupIndex == amounts.length - 1 && amounts[groupIndex] == currentGroupSize) ? 1 : 0;
        }
        long total = 0;
        char c = map.charAt(mapPosition);
        if ((c == '.' || c == '?') && currentGroupSize == 0) {
            total += countArrangements(
                    blockMap,
                    map,
                    amounts,
                    mapPosition + 1,
                    groupIndex,
                    0);
        } else if ((c == '.' || c == '?') && currentGroupSize > 0 && groupIndex < amounts.length && amounts[groupIndex] == currentGroupSize) {
            total += countArrangements(
                    blockMap,
                    map,
                    amounts,
                    mapPosition + 1,
                    groupIndex + 1,
                    0);
        }
        if (c == '#' || c == '?') {
            total += countArrangements(blockMap, map, amounts, mapPosition + 1, groupIndex, currentGroupSize + 1);
        }
        blockMap.put(key, total);
        return total;
    }
}
