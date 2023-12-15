package io.github.lhug.adventofcode.twentytwentythree.fifteenth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LensLibraryTest {

    public static final String input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";

    private final LensLibrary sut = new LensLibrary();

    @ParameterizedTest
    @CsvSource(textBlock = """
            HASH, 52
            rn=1, 30,
            cm-, 253
            qp=3, 97""")
    void hashes_input_for_part_one_correctly(String in, int expected) {
        assertThat(sut.hashFor(in)).isEqualTo(expected);
    }

    @Test
    void sums_hashes_for_string() {
        long result = sut.hashSumFor(input);

        assertThat(result).isEqualTo(1320L);
    }
}