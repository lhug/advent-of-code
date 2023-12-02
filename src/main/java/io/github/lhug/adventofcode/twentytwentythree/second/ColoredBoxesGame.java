package io.github.lhug.adventofcode.twentytwentythree.second;

import java.util.Arrays;
import java.util.List;

public class ColoredBoxesGame {
    private int red = 12;
    private int blue = 14;
    private int green = 13;

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public Game toData(String gameData) {
        var firstSplit = gameData.split(": ");
        var gameId = gameId(firstSplit[0]);
        var configurations = configurations(firstSplit[1]);
        return new Game(gameId, configurations);
    }

    private static int gameId(String gameInfo) {
        return Integer.parseInt(gameInfo.substring(5));
    }

    private static List<Configuration> configurations(String configInfo) {
        var configs = configInfo.split("; ");
        return Arrays.stream(configs).map(ColoredBoxesGame::singleConfig).toList();
    }

    private static Configuration singleConfig(String data) {
        var contents = data.split(", ");
        int red = 0;
        int blue = 0;
        int green = 0;
        for(String content : contents) {
            var datapoint = content.split(" ");
            String value = datapoint[0];
            String color = datapoint[1];
            switch (color) {
                case "blue":
                    blue += Integer.parseInt(value);
                    break;
                case "red":
                    red += Integer.parseInt(value);
                    break;
                case "green":
                    green += Integer.parseInt(value);
                    break;
            }
        }
        return new Configuration(red, green, blue);
    }

    public boolean isPossible(List<Configuration> configs) {
        return configs.stream().allMatch(config ->
            config.blue() <= this.blue &&
            config.red() <= this.red &&
            config.green() <= this.green
        );
    }

    public int calculatePossibleGames(String input) {
        return input.lines()
                .map(this::toData)
                .filter(game -> isPossible(game.configurations()))
                .mapToInt(Game::id)
                .sum();
    }

    public Configuration minimumConfig(List<Configuration> offer) {
        int red = 0;
        int green = 0;
        int blue = 0;
        for(Configuration config : offer) {
            red = Math.max(config.red(), red);
            green = Math.max(config.green(), green);
            blue = Math.max(config.blue(), blue);
        }
        return new Configuration(red, green, blue);
    }

    public int addPowers(String input) {
        return input.lines()
                .map(this::toData)
                .map(game -> minimumConfig(game.configurations()))
                .mapToInt(Configuration::power)
                .sum();
    }

    public record Game(
            int id,
            List<Configuration> configurations) {
    }

    public record Configuration(int red, int green, int blue) {
        public int power() {
            return red * green * blue;
        }
    }
}
