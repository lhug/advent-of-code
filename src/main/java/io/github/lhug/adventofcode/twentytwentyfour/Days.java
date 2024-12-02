package io.github.lhug.adventofcode.twentytwentyfour;

import io.github.lhug.adventofcode.common.ResourceReader;
import io.github.lhug.adventofcode.twentytwentyfour.first.DistanceCalculator;
import io.github.lhug.adventofcode.twentytwentyfour.second.ReactorParameters;
import io.github.lhug.adventofcode.twentytwentyfour.third.MullItOver;

public class Days {

	private final ResourceReader reader = ResourceReader.year(2024);

	public static void main(String[] args) throws Exception{
		Days days = new Days();
		days.dayOne();
		days.dayTwo();
		days.dayThree();
	}

	private void dayOne() {
		var in = reader.day(1);
		var calculator = new DistanceCalculator(in);
		var day1 = calculator.distance_smallest_pairs();
		System.out.println("Distance from smallest pairs: " + day1);
		var day1_2 = calculator.similarityIndex();
		System.out.println("Similarity index: " + day1_2);
	}

	private void dayTwo() {
		var in = reader.day(2);
		var parameters = new ReactorParameters(in);
		var day2 = parameters.safeSequences();
		System.out.println("Safe sequences: " + day2);
		var day2_2 = parameters.bufferedSafeSequences();
		System.out.println("Buffered safe sequences: " + day2_2);
	}

	private void dayThree() {
		var in = reader.day(3);
		var mull = new MullItOver();
		var day3_1 = mull.phaseOne(in);
		System.out.println("Multiplied values: " + day3_1);
		var day3_2 = mull.phaseTwo(in);
		System.out.println("Filtered values: " + day3_2);
	}
}
