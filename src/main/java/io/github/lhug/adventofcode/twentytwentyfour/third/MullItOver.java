package io.github.lhug.adventofcode.twentytwentyfour.third;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MullItOver {

	private static final Pattern MUL = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");

	public List<String> instructions(String input) {
		List<String> results = new ArrayList<>();
		Matcher matcher = MUL.matcher(input);
		while(matcher.find()) {
			results.add(matcher.group());
		}
		return results;
	}

	public long execute(String instruction) {
		var sep = instruction.indexOf(',');
		var left = instruction.substring(4,sep);
		var right = instruction.substring(sep+1, instruction.length()-1);
		return Math.multiplyExact(
				Long.parseLong(left),
				Long.parseLong(right)
		);
	}

	public long phaseOne(String input) {
		return instructions(input)
				.stream()
				.filter(s -> s.startsWith("mul"))
				.mapToLong(this::execute)
				.sum();
	}

	public long phaseTwo(String input) {
		return
				filter(
					instructions(input)
				)
				.stream()
				.mapToLong(this::execute)
				.sum();
	}

	public List<String> filter(List<String> raw) {
		List<String> results = new ArrayList<>();
		boolean use = true;
		for (String s : raw) {
			switch(s) {
				case "do()":
					use = true;
					break;
				case "don't()":
					use = false;
					break;
				default:
					if(use) {
						results.add(s);
					}
			}
		}
		return results;
	}
}
