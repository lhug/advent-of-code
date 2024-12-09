package io.github.lhug.adventofcode.twentytwentyfour.ninth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiskFragmenterTest {

	private static final String INPUT = "2333133121414131402";
	
	private final DiskFragmenter sut = new DiskFragmenter();

	@Test
	void parses_input_into_file_space() {
		var result = sut.parse("12345");

		assertThat(result).isEqualTo("0..111....22222");
	}

	@Test
	void parses_larger_input_into_file_space() {
		var result = sut.parse(INPUT);

		assertThat(result).isEqualTo("00...111...2...333.44.5555.6666.777.888899");
	}

	@Test
	void moves_each_block_from_end_to_first_position() {
		var in = "0..111....22222";

		var result = sut.compress(in);

		assertThat(result).isEqualTo("022111222......");
	}

	@Test
	void calculates_checksum() {
		var in = "022111222......";

		var result = sut.checksum(in);

		assertThat(result).isEqualTo(60L);
	}

	@Test
	void calculates_result_for_larger_input() {
		var result = sut.phaseOne(INPUT);

		assertThat(result).isEqualTo(1928);
	}

	@Test
	void defragments_by_file() {
		var result = sut.defragment("00...111...2...333.44.5555.6666.777.888899");

		assertThat(result).isEqualTo("00992111777.44.333....5555.6666.....8888..");
	}

	@Test
	void calculates_checksum_after_defragmentation() {
		var result = sut.phaseTwo(INPUT);

		assertThat(result).isEqualTo(2858L);
	}
}
