package io.github.lhug.adventofcode.twentytwentyone;

import io.github.lhug.adventofcode.common.ResourceReader;
import io.github.lhug.adventofcode.twentytwentyone.second.CourseCalculator;
import io.github.lhug.adventofcode.twentytwentyone.first.IncrementCounter;
import io.github.lhug.adventofcode.twentytwentyone.third.BinaryDiagnostic;

import java.util.List;

public class Days {

    private final ResourceReader reader = ResourceReader.year(2021);

    public static void main(String[] args) throws Exception{
        Days days = new Days();
        days.dayOne();
        days.dayTwo();
        days.dayThree();
    }

    public void dayOne() {
        List<String> input = reader.day(1).lines().toList();
        var cnt = new IncrementCounter();
        long increments = cnt.increments(input);
        long triples  = cnt.triples(input);
        System.out.println("Day one:");
        System.out.printf("single increments: %d%n", increments);
        System.out.printf("triple increments: %d%n", triples);
    }

    public void dayTwo() {
        List<String> input = reader.day(2).lines().toList();
        var plotter = new CourseCalculator();
        plotter.plot(input);
        System.out.println("Day two:");
        System.out.printf("final position: %d%n", plotter.hPosition() * plotter.depth());
    }

    private void dayThree() {
        List<String> input = reader.day(3).lines().toList();
        var diagnostic = new BinaryDiagnostic();
        diagnostic.parse(input);
        System.out.println("Day 3:");
        System.out.printf("power consumption: %d%n", diagnostic.gamma() * diagnostic.epsilon());
        System.out.printf("life support rating: %d%n", diagnostic.oxygenRating() * diagnostic.co2Rating());
    }
}
