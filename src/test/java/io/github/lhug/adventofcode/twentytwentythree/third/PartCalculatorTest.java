package io.github.lhug.adventofcode.twentytwentythree.third;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.lhug.adventofcode.twentytwentythree.third.PartCalculator.*;
import static org.assertj.core.api.Assertions.assertThat;

class PartCalculatorTest {

    private static final String input_1 = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
            """;

    private final PartCalculator sut = new PartCalculator(input_1);

    @Test
    void returns_locations_of_symbols_in_input() {
        List<SymbolCoordinate> results = sut.symbolLocations();

        assertThat(results).containsExactly(
                new SymbolCoordinate('*', 1, 3),
                new SymbolCoordinate('#', 3, 6),
                new SymbolCoordinate('*', 4, 3),
                new SymbolCoordinate('+', 5, 5),
                new SymbolCoordinate('$', 8, 3),
                new SymbolCoordinate('*', 8, 5)
        );
    }

    @Test
    void finds_single_number_around_coordinate() {
        List<Integer> results = sut.numbersAround(
                List.of(new SymbolCoordinate('*', 4, 3)));

        assertThat(results).containsExactly(617);
    }

    @Test
    void finds_multiple_numbers_around_coordinate() {
        List<Integer> results = sut.numbersAround(
                List.of(new SymbolCoordinate('*', 1, 3)));

        assertThat(results).containsExactly(467, 35);
    }

    @Test
    void finds_multiple_numbers_around_multiple_coordinates() {
        List<Integer> results = sut.numbersAround(
                List.of(
                        new SymbolCoordinate('*', 4, 3),
                        new SymbolCoordinate('*', 1, 3)));

        assertThat(results).containsExactly(617, 467, 35);
    }

    @Test
    void finds_all_numbers_in_test_input() {
        var coordinates = sut.symbolLocations();
        var results = sut.numbersAround(coordinates);

        assertThat(results).containsExactly(
                467, 35,
                633,
                617,
                592,
                664,
                755, 598
        );
    }

    @Test
    void calculates_part_sum() {
        int result = sut.partSum();

        assertThat(result).isEqualTo(4361);
    }

    @Test
    void finds_all_gears() {
        var offer = sut.symbolLocations();

        List<Gear> results = sut.findGears(offer);

        assertThat(results).containsExactly(
                new Gear(467, 35),
                new Gear(755, 598)
        );
    }

    @Test
    void returns_gear_ratio() {
        Gear offer = new Gear(5, 5);

        assertThat(offer.ratio()).isEqualTo(25);
    }

    @Test
    void calculates_gear_ratio() {
        int result = sut.gearRatio();

        assertThat(result).isEqualTo(467835);
    }
}