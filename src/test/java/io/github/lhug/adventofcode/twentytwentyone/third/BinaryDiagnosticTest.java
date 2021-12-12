package io.github.lhug.adventofcode.twentytwentyone.third;

import io.github.lhug.adventofcode.common.ResourceReader;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.assertj.core.util.URLs.contentOf;

public class BinaryDiagnosticTest {

    private final BinaryDiagnostic sut = new BinaryDiagnostic();

    private List<String> input;

    @Test
    void shouldParseAllStringsToBinaryIntegers() {
        input = List.of("11111", "00000", "11001");

        sut.parse(input);

        assertThat(sut)
                .extracting("values", list(Integer.class))
                .containsExactly(0b11111,0b00000,0b11001);
    }

    @Test
    void shouldCreateGammaWithOneIfMostValuesContainOneAtFirstBit() {
        input = List.of("11111", "10000", "01111");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b10000).isEqualTo(0b10000);
    }

    @Test
    void shouldCreateGammaWithZeroIfMostValuesContainZeroAtFirstBit() {
        input = List.of("01111", "10000", "01111");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b10000).isEqualTo(0b00000);
    }

    @Test
    void shouldCreateGammaWithOneIfMostValuesContainOneAtSecondBit() {
        input = List.of("11111", "01000", "10111");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b01000).isEqualTo(0b01000);
    }

    @Test
    void shouldCreateGammaWithZeroIfMostValuesContainZeroAtSecondBit() {
        input = List.of("11111", "00000", "10111");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b01000).isEqualTo(0b00000);
    }

    @Test
    void shouldCreateGammaWithOneIfMostValuesContainOneAtThirdBit() {
        input = List.of("11111", "00100", "11011");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b00100).isEqualTo(0b00100);
    }

    @Test
    void shouldCreateGammaWithZeroIfMostValuesContainZeroAtThirdBit() {
        input = List.of("11111", "00000", "11011");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b00100).isEqualTo(0b00000);
    }

    @Test
    void shouldCreateGammaWithOneIfMostValuesContainOneAtFourthBit() {
        input = List.of("11111", "00010", "11101");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b00010).isEqualTo(0b00010);
    }

    @Test
    void shouldCreateGammaWithZeroIfMostValuesContainZeroAtFourthBit() {
        input = List.of("11111", "00000", "11101");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b00010).isEqualTo(0b00000);
    }

    @Test
    void shouldCreateGammaWithOneIfMostValuesContainOneAtFifthBit() {
        input = List.of("11111", "00001", "11110");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b00001).isEqualTo(0b00001);
    }

    @Test
    void shouldCreateGammaWithZeroIfMostValuesContainZeroAtFifthBit() {
        input = List.of("11111", "00000", "11110");

        sut.parse(input);
        int result = sut.gamma();

        assertThat(result & 0b00001).isEqualTo(0b00000);
    }

    @Test
    void shouldCalculateEpsilonAsInverseOfGamma() {
        input = List.of("11111", "00000", "11001");

        sut.parse(input);

        int result = sut.epsilon();
        assertThat(result).isEqualTo(0b00110);
    }

    @Test
    void shouldReturnValuesForDataSet() {
        input = ResourceReader.year(2021).day(3);

        sut.parse(input);

        assertThat(sut.gamma()).isEqualTo(22);
        assertThat(sut.epsilon()).isEqualTo(9);
    }

    @Test
    void shouldFindOxygenRating() {
        //removes 00000 in first pass, and in second, as 1 and 0 are equal, removes 10101
        input = List.of("11111", "00000", "10101");

        sut.parse(input);

        int result = sut.oxygenRating();

        assertThat(result).isEqualTo(0b11111);
    }

    @Test
    void shouldFindCo2Rating() {
        //removes 11111, 10101 and 11000 in first pass, and in second, as 1 and 0 are equal, 01010
        input = List.of("11111", "00000", "10101", "01010", "11000");

        sut.parse(input);

        int result = sut.co2Rating();

        assertThat(result).isEqualTo(0b00000);
    }

    @Test
    void shouldDetermineResultsForDataSet() {
        input = ResourceReader.year(2021).day(3);

        sut.parse(input);

        assertThat(sut.oxygenRating()).isEqualTo(23);
        assertThat(sut.co2Rating()).isEqualTo(10);
    }
}
