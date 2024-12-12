package io.github.lhug.adventofcode.common;

import java.util.Objects;

public class Matrix {
	public static char[][] copy(char[][] in) {
		Objects.requireNonNull(in);
		Objects.requireNonNull(in[0]);
		var copy = new char[in.length][in[0].length];
		for (int i = 0; i < in.length; i++) {
			System.arraycopy(in[i], 0, copy[i], 0, in[0].length);
		}
		return copy;
	}
}
