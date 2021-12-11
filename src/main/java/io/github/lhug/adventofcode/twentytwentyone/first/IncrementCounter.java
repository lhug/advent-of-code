package io.github.lhug.adventofcode.twentytwentyone.first;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class IncrementCounter {
    public long increments(List<String> strings) {
        long increments = 0;
        Integer prev = null;
        for (String number : strings) {
            int current = Integer.parseInt(number);
            if(prev != null) {
                if (prev < current) {
                    increments++;
                }
            }
            prev = current;
        }
        return increments;
    }

    public long triples(List<String> strings) {
        Triples triples = new Triples();
        Long lastSum = null;
        long increments = 0;
        for (String string : strings) {
            triples.add(string);
            Long sum = triples.sum();
            if(lastSum != null) {
                if(lastSum < sum) {
                    increments++;
                }
            }
            lastSum = sum;
        }
        return increments;
    }

    private static class Triples {
        private final Deque<Long> deque = new LinkedList<>();

        public void add(String value) {
            Long v = Long.valueOf(value);
            if(deque.size() == 3) {
                deque.remove();
            }
            deque.add(v);
        }

        public Long sum() {
            if(deque.size() != 3) {
                return null;
            }
            return deque.stream().reduce(Long::sum).orElseThrow();
        }
    }
}
