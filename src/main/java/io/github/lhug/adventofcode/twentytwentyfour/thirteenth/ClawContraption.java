package io.github.lhug.adventofcode.twentytwentyfour.thirteenth;

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
		var prize = parseTo(lines[2], Vector::new);
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
		for (long a = 0; a < 100; a++) {
			long numeratorB = machine.win.x() - a * machine.a.x;
			if (numeratorB % machine.b.x == 0) {
				long b = numeratorB / machine.b.x;
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

	public long phaseTwo() {
		return raw.stream()
				.map(machine -> new Machine(
						machine.a,
						machine.b,
						new Vector(
								machine.win.x() + 10_000_000_000_000L,
								machine.win.y() + 10_000_000_000_000L)
				))
				.map(this::findSolutions)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.mapToLong(v -> (v.x * 3L) + v.y)
				.sum();
	}

	public Optional<Vector> findSolutions(Machine machine) {
		double divisor = (machine.a.x * machine.b.y) - (machine.a.y * machine.b.x);
		double a = ((machine.win.x() * machine.b.y) - (machine.win.y() * machine.b.x)) / divisor;
		double b = ((machine.a.x * machine.win.y()) - (machine.a.y * machine.win.x())) / divisor;
		if (isInt(a) && isInt(b)) {
			return Optional.of(new Vector((long) a, (long) b));
		}
		return Optional.empty();
	}

	static boolean isInt(double in) {
		return in % 1 == 0;
	}

	public record Vector(long x, long y) {}
	public record Machine(Vector a, Vector b, Vector win) {}
}
