package io.github.lhug.adventofcode.twentytwentythree.sixteenth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FloorIsLavaTest {
    public static final String input = """
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....
            """;

    private FloorIsLava sut;

    @BeforeEach
    void setUp() {
        sut = new FloorIsLava(input);
    }

    @Test
    void current_start_position_has_energized_tiles() {
        assertThat(sut.energized()).isEqualTo(46);
    }
}