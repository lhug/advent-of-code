package io.github.lhug.adventofcode.twentytwentyfour.eleventh;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PlutonianPebbles {

    List<String> numbers;

    public PlutonianPebbles(String input) {
            this.numbers = Arrays.stream(input.split(" ")).toList();
    }

    List<String> applyRules(String number) {
        if("0".equals(number)) {
            return List.of("1");
        } else if ((number.length() & 1) == 0) {
            var len = number.length();
            var left = number.substring(0, len / 2);
            var right = number.substring(len / 2);
            right = new BigInteger(right).toString();
            return List.of(left, right);
        } else {
            var num = new BigInteger(number);
            num = num.multiply(BigInteger.valueOf(2024L));
            return List.of(num.toString());
        }
    }

    public Stream<String> blink(int count) {
        Stream<String> result = numbers.stream();
        for (int i = 0; i < count; i++) {
            result = result
                    .map(this::applyRules)
                    .flatMap(List::stream);
        }
        return result;
    }

    public long phaseOne() {
        return blink(25).count();
    }
}
