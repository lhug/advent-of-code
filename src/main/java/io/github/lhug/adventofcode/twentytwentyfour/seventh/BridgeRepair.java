package io.github.lhug.adventofcode.twentytwentyfour.seventh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class BridgeRepair {

	public final List<EquationPair> equations;

	public BridgeRepair(String input) {
		equations = input.lines()
				.map(this::parse)
				.toList();
	}

	private EquationPair parse(String input) {
		var parts = input.split(": ", 2);
		var operands = Arrays.stream(parts[1].split(" "))
				.mapToLong(Long::parseLong)
				.toArray();
		return new EquationPair(
				Long.parseLong(parts[0]),
				operands
		);
	}

	public List<Solution> findValidSolutionsFor(EquationPair offer) {
		var solutions = new ArrayList<Solution>();
		var combinations = Math.pow(2, offer.operands.length -1);
		for (int i = 0; i < combinations; i++) {
			var operators = generateOperators(i, offer.operands.length - 1);
			var result = evaluate(offer.operands(), operators);
			if(result == offer.result()) {
				solutions.add(new Solution(offer, operators));
			}
		}
		return solutions;
	}
	
	private List<Operator> generateOperators(int combination, int length) {
		List<Operator> result = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			if ((combination & (1 << i)) != 0) {
				result.add(Operator.PLUS);
			} else {
				result.add(Operator.MULTIPLY);
			}
		}
		return result;
	}

	private static long evaluate(long[] numbers, List<Operator> operators) {
		long result = numbers[0];
		for (int i = 0; i < operators.size(); i++) {
			result = operators.get(i).apply(result, numbers[i + 1]);
		}
		return result;
	}

	public long phaseOne() {
		return equations.stream()
				.map(this::findValidSolutionsFor)
				.filter(list -> !list.isEmpty())
				.mapToLong(list -> list.get(0).pair().result())
				.sum();
	}

	public record EquationPair(
			long result,
			long[] operands
	) {
	}

	enum Operator {
		PLUS(Math::addExact),
		MULTIPLY(Math::multiplyExact);

		private final BiFunction<Long, Long, Long> operation;

		Operator(BiFunction<Long, Long, Long> operation) {
			this.operation = operation;
		}

		long apply(long a, long b) {
			return operation.apply(a, b);
		}
	}

	public record Solution(EquationPair pair, List<Operator> operators) {}
}
