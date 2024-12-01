package io.github.lhug.adventofcode.twentytwentyfour.first;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DistanceCalculator {

	final List<Integer> first = new ArrayList<>();
	final List<Integer> second = new ArrayList<>();
	public DistanceCalculator(String input) {
		record Values(int left, int right) {
			Values(String left, String right) {
				this(Integer.parseInt(left), Integer.parseInt(right));
			}
		}
		input.lines()
			.map(str -> str.split("\\s+"))
			.forEach(arr -> {
				first.add(Integer.parseInt(arr[0]));
				second.add(Integer.parseInt(arr[1]));
			});

	}

	public List<Integer> first() {
		return first;
	}

	public List<Integer> second() {
		return second;
	}

	public long distance_smallest_pairs() {
		var firstSort = sortForSmallest(first);
		var secondSort = sortForSmallest(second);
		long diff = 0;

		for (int i = 0; i < firstSort.length; i++) {
			var a = firstSort[i];
			var b = secondSort[i];
			diff += Math.abs(a - b);
		}
		return diff;
	}

	private int[] sortForSmallest(List<Integer> in) {
		return in
				.stream()
				.sorted(Comparator.reverseOrder())
				.mapToInt(Integer::intValue)
				.toArray();
	}

	public long similarityIndex() {
		long result = 0;
		for (Integer i : first) {
			var count = second.stream().filter(i::equals).count();
			result += i * count;
		}
		return result;
	}
}
