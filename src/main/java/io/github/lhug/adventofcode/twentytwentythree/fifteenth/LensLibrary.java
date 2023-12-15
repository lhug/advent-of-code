package io.github.lhug.adventofcode.twentytwentythree.fifteenth;

import java.util.*;

public class LensLibrary {
    public long hashFor(String in) {
        return in.chars().reduce(0, (l, r) -> (((l+r)*17)%256));
    }

    public long hashSumFor(String input) {
        return Arrays.stream(input.split(","))
                .mapToLong(this::hashFor)
                .sum();
    }

    public long focusingPower(String input) {
        Map<Long, List<Lens>> results = new HashMap();
        var instructions = input.split(",");
        for(String instruction : instructions) {
            var parts = instruction.split("[=-]");
            var label = parts[0];
            var hash = hashFor(label);
            List<Lens> lenses = results.computeIfAbsent(
                    hash,
                    i -> new ArrayList<>()
            );
            if(parts.length == 1) {
                lenses.removeIf(lens -> Objects.equals(lens.label(), label));
            } else {
                var focus = Integer.parseInt(parts[1]);
                var lens = new Lens(label, focus);
                var labelIndex = indexOfLabel(lenses, label);
                if (labelIndex != -1) {
                    lenses.set(labelIndex, lens);
                } else {
                    lenses.add(lens);
                }
            }
        }
        long result = 0L;
        for(Map.Entry<Long, List<Lens>> entry : results.entrySet()) {
            var lenses = entry.getValue();
            for (int i = 0; i < lenses.size(); i++) {
                long current = 1 + entry.getKey();
                var lens = lenses.get(i);
                current *= (i+1) * lens.focus();
                result += current;
            }
        }
        return result;

    }

    private int indexOfLabel(List<Lens> lenses, String label) {
        for (int i = 0; i < lenses.size(); i++) {
            if (lenses.get(i).label.equals(label))
                return i;
        }
        return -1;
    }

    private record Lens(String label, int focus) {}
}
