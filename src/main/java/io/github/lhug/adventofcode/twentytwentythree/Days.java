package io.github.lhug.adventofcode.twentytwentythree;

import io.github.lhug.adventofcode.common.ResourceReader;
import io.github.lhug.adventofcode.twentytwentythree.fifth.Fertilizer;
import io.github.lhug.adventofcode.twentytwentythree.first.TrebuchetCalibration;
import io.github.lhug.adventofcode.twentytwentythree.fourth.Scratchcards;
import io.github.lhug.adventofcode.twentytwentythree.second.ColoredBoxesGame;
import io.github.lhug.adventofcode.twentytwentythree.sixth.BoatRace;
import io.github.lhug.adventofcode.twentytwentythree.third.PartCalculator;

public class Days {

    private final ResourceReader reader = ResourceReader.year(2023);

    public static void main(String[] args) {
        Days days = new Days();
        days.dayOne();
        days.dayTwo();
        days.dayThree();
        days.dayFour();
        days.dayFive();
        days.daySix();
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
        var calculator = new PartCalculator(input);
        System.out.println("Results for day three:");
        System.out.println("Sum of all parts: " + calculator.partSum());
        System.out.println("Sum of all gear ratios: " + calculator.gearRatio());
    }

    public void dayFour() {
        var input = reader.day(4);
        var scratchcards = new Scratchcards();
        System.out.println("Results for day four:");
        System.out.println("Sum of all card scores: " + scratchcards.winningSum(input));
        System.out.println("Sum of all collected cards: " + scratchcards.collectedCards(input));
    }

    public void dayFive() {
        var input = reader.day(5);
        var fertilizer = new Fertilizer();
        fertilizer.parse(input);
        System.out.println("Results for day five:");
        System.out.println("Lowest location value: " + fertilizer.lowestLocationForSeeds());
        System.out.println("Lowest location for range: " + fertilizer.lowestLocationForSeedRange(fertilizer.seedRanges()));
    }

    public void daySix() {
        var input = reader.day(6);
        var race = new BoatRace();
        race.parseManyRaces(input);
        System.out.println("Results for day six:");
        System.out.println("Error Margin Factor: " + race.errorMargin(race.races()));
        System.out.println("Error Margin for single race: " + race.numberOfWinningScenarios(race.parseRace(input)));
    }
}