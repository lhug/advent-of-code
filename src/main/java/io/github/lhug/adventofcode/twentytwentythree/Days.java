package io.github.lhug.adventofcode.twentytwentythree;

import io.github.lhug.adventofcode.common.ResourceReader;
import io.github.lhug.adventofcode.twentytwentythree.eigth.HauntedWasteland;
import io.github.lhug.adventofcode.twentytwentythree.eleventh.CosmicExpansion;
import io.github.lhug.adventofcode.twentytwentythree.fourteenth.ReflectorDish;
import io.github.lhug.adventofcode.twentytwentythree.fifth.Fertilizer;
import io.github.lhug.adventofcode.twentytwentythree.first.TrebuchetCalibration;
import io.github.lhug.adventofcode.twentytwentythree.fourth.Scratchcards;
import io.github.lhug.adventofcode.twentytwentythree.ninth.MirageMaintenance;
import io.github.lhug.adventofcode.twentytwentythree.second.ColoredBoxesGame;
import io.github.lhug.adventofcode.twentytwentythree.seventh.CamelCards;
import io.github.lhug.adventofcode.twentytwentythree.sixth.BoatRace;
import io.github.lhug.adventofcode.twentytwentythree.tenth.PipeMaze;
import io.github.lhug.adventofcode.twentytwentythree.third.PartCalculator;
import io.github.lhug.adventofcode.twentytwentythree.thirteenth.PointOfIncidence;
import io.github.lhug.adventofcode.twentytwentythree.twelfth.HotSprings;

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
        days.daySeven();
        days.dayEight();
        days.dayNine();
        days.dayTen();
        days.dayEleven();
        days.dayTwelve();
        days.dayThirteen();
        days.dayFourteen();
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

    public void daySeven() {
        var input = reader.day(7);
        var game = new CamelCards();
        game.parse(input, CamelCards.CardOrder.DEFAULT);
        System.out.println("Results for day seven:");
        System.out.println("Overall Winnings: " + game.calculateWinnings(CamelCards.CardOrder.DEFAULT));
        game.parse(input, CamelCards.CardOrder.JOKERS);
        System.out.println("Overall Winnings with Joker set: " + game.calculateWinnings(CamelCards.CardOrder.JOKERS));
    }

    public void dayEight() {
        var input = reader.day(8);
        var game = new HauntedWasteland(input);
        System.out.println("Results for day eight:");
        System.out.println("Steps from AAA to ZZZ: " + game.stepCount());
        System.out.println("Ghost Steps: " + game.ghostStepCount());
    }

    public void dayNine() {
        var input = reader.day(9);
        var game = new MirageMaintenance(input);
        System.out.println("Results for day nine:");
        System.out.println("Sum of extrapolated values: " + game.sumNextSteps());
        System.out.println("Sum of previous values: " + game.sumPreviousSteps());
    }

    public void dayTen() {
        var input = reader.day(10);
        var game = new PipeMaze();
        System.out.println("Results for day 10:");
        System.out.println("Farthest loop step: " + game.farthest(input));
        System.out.println("Points on grid: " + game.pointsInArea(input));
    }

    public void dayEleven() {
        var input = reader.day(11);
        var game = new CosmicExpansion(input);
        System.out.println("Results for day 11:");
        System.out.println("Shortest distance between each pair: " + game.findPathsWithExpansion(2));
        System.out.println("Shortest distance between each old pair: " + game.findPathsWithExpansion(1000000));
    }

    public void dayTwelve() {
        var input = reader.day(12);
        var game = new HotSprings(input);
        System.out.println("Results for day 12:");
        System.out.println("Sum of all broken gear arrangements: " + game.sumMatches());
        System.out.println("Sum of all unfolded gear arrangements: " + game.sumUnfolded());
    }

    public void dayThirteen() {
        var input = reader.day(13);
        var game = new PointOfIncidence(input);
        System.out.println("Results for day 13:");
        System.out.println("Sum of lines: " + game.calculateMirrorLines());
        System.out.println("Sum of smudged lines: " + game.calculateSmudgedLines());
    }

    public void dayFourteen() {
        var input = reader.day(14);
        var game = new ReflectorDish(input);

        System.out.println("Results for day 14:");
        System.out.println("Load on north side: " + game.tilt());
        System.out.println("Load on north after some cycles: " + game.loadAfterCycling());
    }
}