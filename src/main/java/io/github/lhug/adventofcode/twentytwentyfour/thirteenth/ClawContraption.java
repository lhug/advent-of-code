package io.github.lhug.adventofcode.twentytwentyfour.thirteenth;

import io.github.lhug.adventofcode.common.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class ClawContraption {
	private static final Pattern NUMBERS = Pattern.compile("(?>X=|X\\+)([0-9]+), (?>Y\\+|Y=)([0-9]+)");
	final List<Machine> raw = new ArrayList<>();

	public ClawContraption(String input) {
		for(String dataSet : input.split("\n\n")) {
			parse(dataSet);
		}
	}

	private void parse(String data) {
		var lines = data.split("\n");
		var a = parseTo(lines[0], Vector::new);
		var b = parseTo(lines[1], Vector::new);
		var prize = parseTo(lines[2], Coordinate::new);
		raw.add(new Machine(a, b, prize));
	}

	private <T> T parseTo(String input, BiFunction<Integer, Integer, T> mapper) {
		var matcher = NUMBERS.matcher(input);
		if(matcher.find()) {
			int x = Integer.parseInt(matcher.group(1));
			int y = Integer.parseInt(matcher.group(2));
			return mapper.apply(x, y);
		} else {
			throw new IllegalArgumentException(input + " cannot be parsed");
		}
	}

	Optional<Vector> findCombination(Machine machine) {
		for (int a = 0; a < 100; a++) {
			int numeratorB = machine.win.x() - a * machine.a.x;
			if (numeratorB % machine.b.x == 0) {
				int b = numeratorB / machine.b.x;
				if(a * machine.a.y + b * machine.b.y == machine.win.y()) {
					return Optional.of(new Vector(a, b));
				}
			}
		}
		return Optional.empty();
	}

	public long phaseOne() {
		return raw.stream()
				.map(this::findCombination)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.mapToLong(v -> (v.x * 3L) + v.y)
				.sum();
	}

	public record Vector(int x, int y) {}
	public record Machine(Vector a, Vector b, Coordinate win) {}
}
