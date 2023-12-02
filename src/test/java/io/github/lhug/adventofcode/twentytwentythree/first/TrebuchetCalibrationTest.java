package io.github.lhug.adventofcode.twentytwentythree.first;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TrebuchetCalibrationTest
{
	private static final String input_1 = """
1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
""";

	private static final String LINE_1 = "1abc2   ";
	public static final String LINE_2 = "pqr3stu8vwx";
	public static final String LINE_3 = "a1b2c3d4e5f";
	public static final String LINE_4 = "treb7uchet";

	public static final String input_2 = """
			two1nine
			eightwothree
			abcone2threexyz
			xtwone3four
			4nineeightseven2
			zoneight234
			7pqrstsixteen
			""";

	private final TrebuchetCalibration sut = new TrebuchetCalibration();

	@ParameterizedTest
	@CsvSource(textBlock = """
			1abc2, 12
			pqr3stu8vwx, 38
			a1b2c3d4e5f, 15
			treb7uchet, 77
			""")
	void phase_1(String input, int expected) {
		long result = sut.calibrationValueFor(input);

		assertThat(result).isEqualTo(expected);
	}

	@Test
	void adds_all_lines_together_phase_one() {
		long result = sut.calibrateFor(input_1);

		assertThat(result).isEqualTo(142);
	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			two1nine, 29
			eightwothree, 83
			abcone2threexyz, 13
			xtwone3four, 24
			4nineeightseven2, 42
			zoneight234, 14
			7pqrstsixteen, 76
			sevenine, 79
			""")
	void phase_2(String input, int expected) {
		long result = sut.calibrationValueFor(input);

		assertThat(result).isEqualTo(expected);
	}

	@Test
	void adds_all_lines_together_phase_two() {
		long result = sut.calibrateFor(input_2);

		assertThat(result).isEqualTo(281);
	}
}