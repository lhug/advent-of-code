package io.github.lhug.adventofcode.twentytwentyone.first;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.URLs.contentOf;

public class CourseCalculatorTest {

    private final CourseCalculator sut = new CourseCalculator();

    private List<String> input = new ArrayList<>();

    @Test
    void shouldStartWithHPositionZero() {
        assertThat(sut.hPosition()).isZero();
    }

    @Test
    void shouldIncrementHPositionWhenForwardIsPassed() {
        forward(9);

        sut.plot(input);

        assertThat(sut.hPosition()).isGreaterThan(0L);
    }

    private void forward(int steps) {
        input.add("forward " + steps);
    }

    @Test
    void shouldStartWithDepthZero() {
        assertThat(sut.depth()).isZero();
    }

    @Test
    void shouldIncrementAimWhenDownIsPassed() {
        down(2);

        sut.plot(input);

        assertThat(sut.aim()).isGreaterThan(0L);
    }

    private void down(int steps) {
        input.add("down " + steps);
    }

    @Test
    void shouldIncrementHPositionByAmountOfStepsPassed() {
        forward(2);

        sut.plot(input);

        assertThat(sut.hPosition()).isEqualTo(2L);
    }

    @Test
    void shouldIncrementAimByAmountOfStepsPassed() {
        down(6);

        sut.plot(input);

        assertThat(sut.aim()).isEqualTo(6L);
    }

    @Test
    void shouldReduceAimWhenUpIsPassed() {
        up(4);

        sut.plot(input);

        assertThat(sut.aim()).isLessThan(0L);
    }

    private void up(int steps) {
        input.add("up " + steps);
    }

    @Test
    void shouldReduceAimByAmountOfSteps() {
        up(99);

        sut.plot(input);

        assertThat(sut.aim()).isEqualTo(-99L);
    }

    @Test
    void shouldNotIncreaseDepthWhenAimIsZero() {
        forward(4);

        sut.plot(input);

        assertThat(sut.depth()).isZero();
    }

    @Test
    void shouldModifyDepthByForwardWhenAimIsOne() {
        down(1);
        forward(4);

        sut.plot(input);

        assertThat(sut.depth()).isEqualTo(4L);
    }

    @Test
    void shouldModifyDepthByMultipleOfForwardWhenAimIsNeitherZeroNorOne() {
        down(4);
        forward(2);

        sut.plot(input);

        assertThat(sut.depth()).isEqualTo(8L);
    }

    @Test
    void shouldCalculatePositionAsDefinedByTestData() {
        URL resource = getClass().getResource("/2021/2/input.txt");
        input = contentOf(Objects.requireNonNull(resource), StandardCharsets.UTF_8).lines().toList();

        sut.plot(input);

        assertThat(sut.hPosition()).isEqualTo(15L);
        assertThat(sut.depth()).isEqualTo(60L);
    }
}
