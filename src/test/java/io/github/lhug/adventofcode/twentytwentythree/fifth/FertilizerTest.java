package io.github.lhug.adventofcode.twentytwentythree.fifth;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.lhug.adventofcode.twentytwentythree.fifth.Fertilizer.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

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
                       new Mapping(50, 52, 48),
                       new Mapping(98, 50, 2)
               );

    }

    @Test
    void parses_other_map_sizes_correctly() {
        sut.parse(input);

        assertThat(sut.soilToFertilizer().mappings()).containsExactly(
                new Mapping(0, 39, 15),
                new Mapping(15, 0, 37),
                new Mapping(52, 37, 2)
        );
        assertThat(sut.fertilizerToWater().mappings()).containsExactly(
                new Mapping(0, 42, 7),
                new Mapping(7, 57, 4),
                new Mapping(11, 0, 42),
                new Mapping(53, 49, 8)
        );
        assertThat(sut.waterToLight().mappings()).containsExactly(
                new Mapping(18, 88, 7),
                new Mapping(25, 18, 70)
        );
        assertThat(sut.lightToTemperature().mappings()).containsExactly(
                new Mapping(45, 81, 19),
                new Mapping(64, 68, 13),
                new Mapping(77, 45, 23)
        );
        assertThat(sut.temperatureToHumidity().mappings()).containsExactly(
                new Mapping(0, 1, 69),
                new Mapping(69, 0, 1)
        );
        assertThat(sut.humidityToLocation().mappings()).containsExactly(
                new Mapping(56, 60, 37),
                new Mapping(93, 56, 4)
        );
    }

    @Test
    void returns_mapped_value() {
        Converter offer = new Converter(List.of(
                new Mapping(0, 10, 6),
                new Mapping(10, 15, 6)
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

    @Test
    void converts_seeds_to_value_ranges() {
        sut.parse(input);

        List<ValueRange> result = sut.seedRanges();

        assertThat(result).containsExactly(
                new ValueRange(79, 14),
                new ValueRange(55, 13)
        );
    }

    @Test
    void maps_value_range_matching_value_range() {
        var out = new ValueRange(1, 10);
        var in = new ValueRange(1, 10);

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(in);
    }

    @Test
    void maps_value_range_outside_value_range() {
        var out = new ValueRange(10, 10);
        var in = new ValueRange(1, 5);

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(in);
    }

    @Test
    void maps_value_range_longer_than_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(10, 10); // 10 - 19

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(
                new ValueRange(10, 5),
                new ValueRange(15, 5)
        );
    }

    @Test
    void maps_value_range_extending_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(12, 6); // 12 - 17

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(
                new ValueRange(12, 3),
                new ValueRange(15, 3)
        );
    }

    @Test
    void maps_value_range_shorter_than_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(10, 2); // 10 - 11

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(in);
    }

    @Test
    void maps_value_range_within_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(11, 2); // 11 - 12

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(in);
    }

    @Test
    void maps_value_range_before_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(5, 2); // 5 - 6

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(in);
    }

    @Test
    void maps_value_range_after_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(20, 2); // 20 - 21

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(in);
    }

    @Test
    void maps_value_range_passing_in_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(8, 5); // 8 - 12

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(
                new ValueRange(8, 2), // 8 - 9
                new ValueRange(10, 3) // 10 - 12
        );
    }

    @Test
    void maps_value_range_overlapping_value_range() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(9, 7); // 9 - 15

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(
                new ValueRange(9, 1), // 9
                new ValueRange(10, 5), // 10 - 14
                new ValueRange(15, 1) // 15
        );
    }

    @Test
    void maps_min_to_exact() {
        var out = new ValueRange(10, 5); // 10 - 14
        var in = new ValueRange(9, 6); // 9 - 14

        List<ValueRange> result = out.process(in);

        assertThat(result).containsExactly(
                new ValueRange(9, 1), // 9
                new ValueRange(10, 5) // 10 - 14
        );
    }

    @Test
    void maps_contained_value_range_to_targets() {
        var mapping = new Mapping(5, 77, 10);
        var offer = new ValueRange(7, 3);

        List<ValueRange> results = mapping.targetRangesFor(offer);

        assertThat(results).containsExactly(
                new ValueRange(79, 3)
        );
    }

    @Test
    void maps_previous_value_range_to_targets() {
        var mapping = new Mapping(5, 77, 10);
        var offer = new ValueRange(1, 3);

        List<ValueRange> results = mapping.targetRangesFor(offer);

        assertThat(results).containsExactly(
                new ValueRange(1, 3)
        );
    }

    @Test
    void maps_following_value_range_to_targets() {
        var mapping = new Mapping(5, 77, 10);
        var offer = new ValueRange(20, 3);

        List<ValueRange> results = mapping.targetRangesFor(offer);

        assertThat(results).containsExactly(
                new ValueRange(20, 3)
        );
    }

    @Test
    void maps_matching_value_range_to_targets() {
        var mapping = new Mapping(5, 77, 10);
        var offer = new ValueRange(5, 10);

        List<ValueRange> results = mapping.targetRangesFor(offer);

        assertThat(results).containsExactly(
                new ValueRange(77, 10)
        );
    }

    @Test
    void maps_entering_value_range_to_targets() {
        var mapping = new Mapping(5, 77, 10);
        var offer = new ValueRange(3, 5); // 3 - 7

        List<ValueRange> results = mapping.targetRangesFor(offer);

        assertThat(results).containsExactly(
                new ValueRange(3, 2),
                new ValueRange(77, 3)
        );
    }

    @Test
    void maps_leaving_value_range_to_targets() {
        var mapping = new Mapping(5, 77, 10);
        var offer = new ValueRange(12, 5); // 12 - 16

        List<ValueRange> results = mapping.targetRangesFor(offer);

        assertThat(results).containsExactly(
                new ValueRange(84, 3),
                new ValueRange(15, 2)
        );
    }

    @Test
    void converter_finds_fitting_ranges() {
        var converter = new Converter(List.of(
                new Mapping(1, 7, 5),// 1-5 -> 7-11
                new Mapping(9, 55, 7) // 9 - 15 -> 55-62
        ));

        var result = converter.rangesFor(List.of(new ValueRange(1, 15))); // 1 - 15

        assertThat(result).containsOnly(
                new ValueRange(7, 5),
                new ValueRange(6, 3),
                new ValueRange(55, 7)
        );
    }

    @Test
    void finds_min_value_of_range() {
        sut.parse(input);

        var offer = sut.seedRanges();

        long result = sut.lowestLocationForSeedRange(offer);

        assertThat(result).isEqualTo(46);
    }

    @Test
    void maps_overlapping_ranges_correctly() {
        // target range of mapping 2 overlaps with range of mapping 3
        var range = new ValueRange(74, 14);
        var con = new Converter(List.of(
                new Mapping(45,81,19),
                new Mapping(64,68,13),
                new Mapping(77,45,23)
        ));

        var result = con.rangesFor(List.of(range));

        assertThat(result).containsOnly(
                new ValueRange(78, 3),
                new ValueRange(45, 11)
        );
    }
}