package io.github.lhug.adventofcode.twentytwentyone.first;

import io.github.lhug.adventofcode.common.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IncrementCounterTest {

    IncrementCounter sut = new IncrementCounter();

    @Test
    void shouldReportZeroIncreasesOnSingleNumber() {
        long result = sut.increments(List.of("1"));

        assertThat(result).isZero();
    }

    @Test
    void shouldReportZeroIncrementsWhenTwoNumbersAreSame() {
        long result = sut.increments(List.of("1", "1"));

        assertThat(result).isZero();
    }

    @Test
    void shouldReportOneIncrementWhenSecondNumberIsHigherThanFirst() {
        long result = sut.increments(List.of("1", "2"));

        assertThat(result).isOne();
    }

    @Test
    void shouldReportZeroIncrementsWhenSecondNumberIsLowerThanFirst() {
        long result = sut.increments(List.of("2", "1"));

        assertThat(result).isZero();
    }

    @Test
    void shouldReportTwoIncrements() {
        long result = sut.increments(List.of("1", "2", "3"));

        assertThat(result).isEqualTo(2L);
    }

    @Test
    void shouldReportIncrementWithGaps() {
        long result = sut.increments(List.of("2", "1", "2"));

        assertThat(result).isOne();
    }

    @Test
    void shouldReportSevenForProvidedTestData() {
        List<String> input = ResourceReader.year(2021).day(1).lines().toList();

        long result = sut.increments(input);

        assertThat(result).isEqualTo(7L);
    }

    @Test
    void shouldReportZeroWhenLessThanFourInputsAreGiven() {
        assertThat(sut.triples(List.of("1"))).isZero();
        assertThat(sut.triples(List.of("1", "2"))).isZero();
        assertThat(sut.triples(List.of("1", "2", "3"))).isZero();
    }

    @Test
    void shouldReportZeroWhenSumOfFirstThreeIsGreaterThanOrEqualToSumOfSecondThree() {
        assertThat(sut.triples(List.of("3", "2", "1", "2"))).isZero();
        assertThat(sut.triples(List.of("3", "2", "1", "3"))).isZero();
    }

    @Test
    void shouldReportOneWhenSumOfFirstThreeIsLessThanSumOfSecondThree() {
        assertThat(sut.triples(List.of("1", "2", "3", "4"))).isOne();
    }

    @Test
    void shouldReturnExpectedResultsOnTriples() {
        List<String> input = ResourceReader.year(2021).day(1).lines().toList();

        long result = sut.triples(input);

        assertThat(result).isEqualTo(5L);
    }
}
