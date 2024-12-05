package io.github.lhug.adventofcode.twentytwentyfour.fifth;

import java.util.*;

public class PrintQueue {

    final List<PrintRule> rules;
    final List<int[]> rows;

    public PrintQueue(String input) {
        var parts = input.split("\\n\\n", 2);
        rules = parseRules(parts[0]);
        rows = parseData(parts[1]);
    }

    private List<PrintRule> parseRules(String input) {
        return input.lines()
                .map(PrintRule::from)
                .toList();
    }

    private List<int[]> parseData(String input) {
        return input.lines()
                .map(line -> line.split(","))
                .map(parts -> Arrays.stream(parts).mapToInt(Integer::parseInt).toArray())
                .toList();
    }


    List<int[]> validRows() {
        return rows.stream()
                .filter(row -> rules.stream().allMatch(rule -> rule.matches(row) != PrintRule.Result.FAIL))
                .toList();
    }

    static int middleValue(int[] row) {
        if (row.length == 0) {
            return 0;
        }
        return row[row.length / 2];
    }

    public long phaseOne() {
        return validRows().stream()
                .mapToLong(PrintQueue::middleValue)
                .sum();
    }

    /**
     * Applies Khan's Algorithm for topological sorting.
     * @param array the array to sort
     * @param criteria the list of PrintRules tu apply
     * @return the sorted array, or an empty array if no sort is possible
     */
    public int[] khan(int[] array, List<PrintRule> criteria) {
        // Step 1: Build the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // graph contains all "first" values, and each value the "first" points to
        // inDegree contains all values and the amount of rules that point "towards" it
        for (PrintRule criterion : criteria) {
            graph.putIfAbsent(criterion.first(), new ArrayList<>());
            graph.putIfAbsent(criterion.second(), new ArrayList<>());
            inDegree.putIfAbsent(criterion.first(), 0);
            inDegree.putIfAbsent(criterion.second(), 0);

            graph.get(criterion.first()).add(criterion.second());
            inDegree.put(criterion.second(), inDegree.get(criterion.second()) + 1);
        }

        // Step 2: Topological Sort using Kahn's Algorithm
        Queue<Integer> queue = new LinkedList<>();
        // finds all numbers that have 0 rules pointing towards them
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedOrder.add(current);

            // pops the queue, examines all values that the current number points to
            // reduces the "toward" count by 1 and if any number has no more rules pointing
            // towards it, it is added to the queue.
            for (int neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 3: Check for cycles (unsatisfiable criteria)
        if (sortedOrder.size() < inDegree.size()) {
            return new int[0];
        }

        // Step 4: Reorder the array according to the sorted order
        return Arrays.stream(array)
                .boxed()
                .sorted(Comparator.comparingInt(sortedOrder::indexOf))
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public List<int[]> badRows() {
        return rows.stream()
                .filter(row -> rules.stream()
                        .anyMatch(rule -> rule.matches(row) == PrintRule.Result.FAIL))
                .toList();

    }

    public long phaseTwo() {

        return badRows()
                .stream()
                .map(this::sort)
                .mapToLong(PrintQueue::middleValue)
                .sum();
    }

    private int[] sort(int[] array) {
        var rulesToUse = rules.stream()
                .filter(rule ->
                        rule.matches(array) != PrintRule.Result.NOT_APPLICABLE)
                .toList();
        return khan(array, rulesToUse);
    }
}
