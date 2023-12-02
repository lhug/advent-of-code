package io.github.lhug.adventofcode.twentytwentythree.first;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TrebuchetCalibration
{

	Map<String, String> REPLACEMENTS = Map.ofEntries(
			Map.entry("one", "1"),
			Map.entry("two", "2"),
			Map.entry("three", "3"),
			Map.entry("four", "4"),
			Map.entry("five", "5"),
			Map.entry("six", "6"),
			Map.entry("seven", "7"),
			Map.entry("eight", "8"),
			Map.entry("nine", "9")
	);

	Pattern p = Pattern.compile("([0-9]|one|two|three|four|five|six|seven|eight|nine)");

	public long calibrationValueFor(String line) {
		var m = p.matcher(line);
		List<String> matchList = new ArrayList<>();
		// effing missing test data -.-
		for (int i = 0; i < line.length(); i++) {
			if (m.find(i)) {
				matchList.add(m.group());
			}
			m.reset();
		}

		var matches = matchList.toArray(String[]::new);
		var left = matches[0];
		var right = matches[matches.length - 1];
		left = REPLACEMENTS.getOrDefault(left, left);
		right = REPLACEMENTS.getOrDefault(right, right);
		return Long.parseLong(left + right);
	}

	public long calibrateFor(String input) {
		return input.lines().mapToLong(this::calibrationValueFor).sum();
	}
}
