package io.github.lhug.adventofcode.common;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;

import java.util.Arrays;

public class ArrayConverter extends TypedArgumentConverter<String, int[]> {
    public ArrayConverter() {
        super(String.class, int[].class);
    }

    @Override
    protected int[] convert(String s) throws ArgumentConversionException {
        return Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
