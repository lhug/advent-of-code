package io.github.lhug.adventofcode.twentytwentyfour;

import io.github.lhug.adventofcode.common.ResourceReader;
import io.github.lhug.adventofcode.twentytwentyfour.eigth.ResonantCollinearity;
import io.github.lhug.adventofcode.twentytwentyfour.eleventh.PlutonianPebbles;
import io.github.lhug.adventofcode.twentytwentyfour.fifth.PrintQueue;
import io.github.lhug.adventofcode.twentytwentyfour.first.DistanceCalculator;
import io.github.lhug.adventofcode.twentytwentyfour.fourth.WordSearch;
import io.github.lhug.adventofcode.twentytwentyfour.ninth.DiskFragmenter;
import io.github.lhug.adventofcode.twentytwentyfour.second.ReactorParameters;
import io.github.lhug.adventofcode.twentytwentyfour.seventh.BridgeRepair;
import io.github.lhug.adventofcode.twentytwentyfour.sixth.GuardGallivant;
import io.github.lhug.adventofcode.twentytwentyfour.tenth.HoofIt;
import io.github.lhug.adventofcode.twentytwentyfour.third.MullItOver;

public class Days {

	private final ResourceReader reader = ResourceReader.year(2024);

	public static void main(String[] args) {
		Days days = new Days();
		days.dayOne();
		days.dayTwo();
		days.dayThree();
		days.dayFour();
		days.dayFive();
		days.daySix();
		days.daySeven();
		days.dayEight();
		days.dayNine();
		days.dayTen();
		days.dayEleven();
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

	private void dayFour() {
		var in = reader.day(4);
		var ws = new WordSearch(in);
		var day4_1 = ws.countXmas();
		System.out.println("XMAS count: " + day4_1);
		var day4_2 = ws.countX_mas();
		System.out.println("X-MAS count: " + day4_2);
	}

	private void dayFive() {
		var in = reader.day(5);
		var pq = new PrintQueue(in);
		var day5_1 = pq.phaseOne();
		System.out.println("Phase one: " + day5_1);
		var day5_2 = pq.phaseTwo();
		System.out.println("Phase two: " + day5_2);
	}

	private void daySix() {
		var in = reader.day(6);
		var gg = new GuardGallivant(in);
		var day6_1 = gg.phaseOne();
		System.out.println("Distinct guard positions: " + day6_1);
		var day6_2 = gg.phaseTwo();
		System.out.println("Possible obstacle locations: " + day6_2);
	}

	private void daySeven() {
		var in = reader.day(7);
		var br = new BridgeRepair(in);
		var day7_1 = br.phaseOne();
		System.out.println("Calibration value 1: " + day7_1);
		var day7_2 = br.phaseTwo();
		System.out.println("Calibration value 2: " + day7_2);
	}

	private void dayEight() {
		var in = reader.day(8);
		var rc = new ResonantCollinearity(in);
		var day8_1 = rc.phaseOne();
		System.out.println("Unique antinode locations: " + day8_1);
		var day8_2 = rc.phaseTwo();
		System.out.println("Long unique antinode locations: " + day8_2);
	}

	private void dayNine() {
		var in = reader.day(9);
		var df = new DiskFragmenter();
		var day9_1 = df.phaseOne(in);
		System.out.println("Checksum part one: " + day9_1);
		var day9_2 = df.phaseTwo(in);
		System.out.println("Defragmented checksum: " + day9_2);
	}

	private void dayTen() {
		var in = reader.day(10);
		var hi = new HoofIt(in);
		var day10_1 = hi.phaseOne();
		System.out.println("Trailhead score unique target:" + day10_1);
		var day10_2 = hi.phaseTwo();
		System.out.println("Trailhead score distinct paths:" + day10_2);
	}

	private void dayEleven() {
		var in = reader.day(11);
		var pp = new PlutonianPebbles(in);
		var day11_1 = pp.phaseOne();
		System.out.println("Initial stone count: " + day11_1);
	}
}
