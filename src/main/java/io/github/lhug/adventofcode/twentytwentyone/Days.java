package io.github.lhug.adventofcode.twentytwentyone;

import io.github.lhug.adventofcode.twentytwentyone.second.CourseCalculator;
import io.github.lhug.adventofcode.twentytwentyone.first.IncrementCounter;
import io.github.lhug.adventofcode.twentytwentyone.third.BinaryDiagnostic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class Days {

    private static List<String> inputFrom(String day) {
        String resource = "/2021/" + day + "/input.txt";
        InputStream inputStream = Objects.requireNonNull(Days.class.getResourceAsStream(resource), "no input data found");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try(reader) {
            return reader.lines().toList();
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not read resource " + resource);
        }
    }

    public static void main(String[] args) throws Exception{
        Days days = new Days();
        days.dayOne();
        days.dayTwo();
        days.dayThree();
    }

    public void dayOne() {
        List<String> input = inputFrom("1");
        var cnt = new IncrementCounter();
        long increments = cnt.increments(input);
        long triples  = cnt.triples(input);
        System.out.println("Day one:");
        System.out.printf("single increments: %d%n", increments);
        System.out.printf("triple increlemts: %d%n", triples);
    }

    public void dayTwo() {
        List<String> input = inputFrom("2");
        var plotter = new CourseCalculator();
        plotter.plot(input);
        System.out.println("Day two:");
        System.out.printf("final position: %d%n", plotter.hPosition() * plotter.depth());
    }

    private void dayThree() {
        List<String> input = inputFrom("3");
        var diagnostic = new BinaryDiagnostic();
        diagnostic.parse(input);
        System.out.println("Day 3:");
        System.out.printf("power consumption: %d%n", diagnostic.gamma() * diagnostic.epsilon());
        System.out.printf("life support rating: %d%n", diagnostic.oxygenRating() * diagnostic.co2Rating());
    }
}
