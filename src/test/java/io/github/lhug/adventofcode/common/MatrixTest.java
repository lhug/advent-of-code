package io.github.lhug.adventofcode.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MatrixTest {

	@Test
	void copies_matrix() {
		var in = new char[][]{
				new char[] {'a', 'a', 'a'},
				new char[] {'b', 'b', 'b'},
				new char[] {'c', 'c', 'c'}
		};

		var result = Matrix.copy(in);

		assertThat(result)
				.isEqualTo(in)
				.isNotSameAs(in);
	}

	@Test
	void throws_exception_when_matrix_is_null() {
		assertThatThrownBy(() -> Matrix.copy(null))
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	void throws_exception_when_copying_array_with_initial_null_array() {
		assertThatThrownBy(() -> Matrix.copy(new char[][]{
				null
		}))
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	void throws_exception_when_copying_array_with_null_array() {
		assertThatThrownBy(() -> Matrix.copy(new char[][]{
				new char[]{'a', 'a', 'a'},
				null
		}))
				.isInstanceOf(NullPointerException.class);
	}
}
