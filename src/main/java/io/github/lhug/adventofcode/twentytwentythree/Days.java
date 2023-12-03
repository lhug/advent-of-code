package io.github.lhug.adventofcode.twentytwentythree;

import io.github.lhug.adventofcode.common.ResourceReader;
import io.github.lhug.adventofcode.twentytwentythree.first.TrebuchetCalibration;
import io.github.lhug.adventofcode.twentytwentythree.second.ColoredBoxesGame;
import io.github.lhug.adventofcode.twentytwentythree.third.PartCalculator;

public class Days {

    private final ResourceReader reader = ResourceReader.year(2023);

    public static void main(String[] args) throws Exception{
        Days days = new Days();
        days.dayOne();
        days.dayTwo();
        days.dayThree();
    }

    public void dayOne() {
        var input = reader.day(1);
        var result = new TrebuchetCalibration().calibrateFor(input);
        System.out.println("Results for day one:");
        System.out.println("Calibration value is " + result);
    }

    public void dayTwo() {
        var input = reader.day(2);
        var game = new ColoredBoxesGame();
        System.out.println("Results for day two:");
        System.out.println("Sum of possible games: " + game.calculatePossibleGames(input));
        System.out.println("Sum of powers: " + game.addPowers(input));
    }

    public void dayThree() {
        var input = reader.day(3);
        var calculator = new PartCalculator();
        System.out.println("Results for day three:");
        System.out.println("Sum of all parts: " + calculator.partSum(input));
    }
}