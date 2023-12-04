package io.github.lhug.adventofcode.common;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ResourceReaderTest {

    @Test
    void allows_access_to_resources_2021() {
        ResourceReader result = ResourceReader.year(2021);

        assertThat(result)
                .extracting("resourcePath", InstanceOfAssertFactories.STRING)
                .isEqualTo("/2021/input_%d.txt");
    }

    @Test
    void throws_exception_when_year_does_not_exist() {
        assertThatThrownBy(() -> ResourceReader.year(1912))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("no input for year 1912");
    }

    @Test
    void reads_input_for_day() {
        String result = ResourceReader.year(2021).day(1);

        assertThat(result).isEqualTo(contentOf(getClass().getResource("/2021/input_1.txt")));
    }

    @Test
    void throws_exception_when_input_for_day_does_not_exist() {
        ResourceReader reader = ResourceReader.year(2021);

        assertThatThrownBy(() -> reader.day(9999))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("no input for day id 9999");
    }
}
