package io.github.lhug.adventofcode.twentytwentythree.eigth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.lhug.adventofcode.twentytwentythree.eigth.HauntedWasteland.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class HauntedWastelandTest {

    public static final String input_1 = """
            RL
                        
            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
            """;

    public static final String input_2 = """
            LLR
                        
            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
            """;

    public static final String input_3 = """
            LR
                        
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
            """;

    private HauntedWasteland sut;

    @BeforeEach
    void setUp() {
        sut = new HauntedWasteland(input_1);
    }

    @Test
    void takes_2_steps_to_reach_zzz() {
        long result = sut.stepCount();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void ghost_path_takes_6_steps_to_reach_z() {
        sut = new HauntedWasteland(input_3);

        long result = sut.ghostStepCount();

        assertThat(result).isEqualTo(6L);
    }

    @Test
    void reads_instruction_loop() {
        List<Direction> result = sut.instructionLoop();

        assertThat(result).containsExactly(
                Direction.RIGHT,
                Direction.LEFT);
    }

    @Test
    void reads_other_instruction_loop() {
        sut = new HauntedWasteland(input_2);

        assertThat(sut.instructionLoop()).containsExactly(
                Direction.LEFT,
                Direction.LEFT,
                Direction.RIGHT
        );
    }

    @Test
    void reads_ways() {
        Map<String, Fork> result = sut.ways();

        assertThat(result).containsOnly(
                entry("AAA", new Fork("BBB", "CCC")),
                entry("BBB", new Fork("DDD", "EEE")),
                entry("CCC", new Fork("ZZZ", "GGG")),
                entry("DDD", new Fork("DDD", "DDD")),
                entry("EEE", new Fork("EEE", "EEE")),
                entry("GGG", new Fork("GGG", "GGG")),
                entry("ZZZ", new Fork("ZZZ", "ZZZ"))
        );
    }

    @Test
    void finds_path_following_instruction_loop() {
        List<String> results = sut.findPath("AAA", "ZZZ");

        assertThat(results).containsExactly(
                "CCC", "ZZZ"
        );
    }

    @Test
    void finds_path_following_repeating_instruction_loop() {
        sut = new HauntedWasteland(input_2);

        List<String> results = sut.findPath("AAA", "ZZZ");

        assertThat(results).containsExactly(
                "BBB", "AAA", "BBB",
                "AAA", "BBB", "ZZZ"
        );
    }
}