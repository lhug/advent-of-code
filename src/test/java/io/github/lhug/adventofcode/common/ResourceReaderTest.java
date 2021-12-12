package io.github.lhug.adventofcode.common;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ResourceReaderTest {

    @Test
    void shouldAllowAccessToResourcesA2021() {
        ResourceReader result = ResourceReader.year(2021);

        assertThat(result)
                .extracting("resourcePath", InstanceOfAssertFactories.STRING)
                .isEqualTo("/2021/input_%d.txt");
    }

    @Test
    void shouldThrowExceptionWhenYearDoesNotExist() {
        assertThatThrownBy(() -> ResourceReader.year(2019))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("no input for year 2019");
    }

    @Test
    void shouldReadLinesFromInputForDay() {
        List<String> result = ResourceReader.year(2021).day(1);

        assertThat(result).isEqualTo(linesOf(getClass().getResource("/2021/input_1.txt")));
    }

    @Test
    void shouldThrowExceptionWhenInputForDayDoesNotExist() {
        ResourceReader reader = ResourceReader.year(2021);

        assertThatThrownBy(() -> reader.day(9999))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("no input for day number 9999");
    }
}
