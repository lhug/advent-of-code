package io.github.lhug.adventofcode.twentytwentythree.fifth;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.github.lhug.adventofcode.twentytwentythree.fifth.Fertilizer.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

class FertilizerTest {

    public static final String input = """          
            seeds: 79 14 55 13
                        
            seed-to-soil map:
            50 98 2
            52 50 48
                        
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
                        
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
                        
            water-to-light map:
            88 18 7
            18 25 70
                        
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
                        
            temperature-to-humidity map:
            0 69 1
            1 0 69
                        
            humidity-to-location map:
            60 56 37
            56 93 4
            
            """;

    private final Fertilizer sut = new Fertilizer();

    @Test
    void parses_seed_numbers_correctly() {
        sut.parse(input);

        assertThat(sut.seeds()).containsExactly(79L,14L,55L,13L);
    }

    @Test
    void parses_seed_to_soil_correctly() {
        sut.parse(input);

       Converter result = sut.seedToSoil();

       assertThat(result.mappings())
               .containsExactly(
                       new Mapping(98, 50, 2),
                       new Mapping(50, 52, 48)
               );

    }

    @Test
    void parses_other_map_sizes_correctly() {
        sut.parse(input);

        assertThat(sut.soilToFertilizer().mappings()).containsExactly(
              new Mapping(15, 0, 37),
                new Mapping(52, 37, 2),
                new Mapping(0, 39, 15)
        );
        assertThat(sut.fertilizerToWater().mappings()).containsExactly(
                new Mapping(53, 49, 8),
                new Mapping(11, 0, 42),
                new Mapping(0, 42, 7),
                new Mapping(7, 57, 4)
        );
        assertThat(sut.waterToLight().mappings()).containsExactly(
                new Mapping(18, 88, 7),
                        new Mapping(25, 18, 70)
        );
        assertThat(sut.lightToTemperature().mappings()).containsExactly(
                new Mapping(77, 45, 23),
                new Mapping(45, 81, 19),
                new Mapping(64, 68, 13)
        );
        assertThat(sut.temperatureToHumidity().mappings()).containsExactly(
                new Mapping(69, 0, 1),
                new Mapping(0, 1, 69)
        );
        assertThat(sut.humidityToLocation().mappings()).containsExactly(
                new Mapping(56, 60, 37),
                new Mapping(93, 56, 4)
        );
    }

    @Test
    void returns_mapped_value() {
        Converter offer = new Converter(List.of(
                new Mapping(0, 10, 5),
                new Mapping(10, 15, 5)
        ));

        assertThat(offer.valueFor(0)).isEqualTo(10);
        assertThat(offer.valueFor(5)).isEqualTo(15);
        assertThat(offer.valueFor(10)).isEqualTo(15);
        assertThat(offer.valueFor(15)).isEqualTo(20);
        assertThat(offer.valueFor(6)).isEqualTo(6);
        assertThat(offer.valueFor(9)).isEqualTo(9);
    }

    @Test
    void finds_lowest_location_value() {
        sut.parse(input);

        long result = sut.lowestLocationForSeeds();

        assertThat(result).isEqualTo(35);
    }
}