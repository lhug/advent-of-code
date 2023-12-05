package io.github.lhug.adventofcode.twentytwentythree.fifth;

import java.util.*;

public class Fertilizer {
    private final List<Long> seeds = new ArrayList<>();
    private Converter seedToSoil;
    private Converter soilToFertilizer;
    private Converter fertilizerToWater;
    private Converter waterToLight;
    private Converter lightToTemperature;
    private Converter temperatureToHumidity;
    private Converter humidityToLocation;

    public List<Long> seeds() {
        return seeds;
    }

    public void parse(String input) {
        var scanner = new Scanner(input);
        readSeeds(scanner);
        seedToSoil = readMap("seed-to-soil", scanner);
        soilToFertilizer = readMap("soil-to-fertilizer", scanner);
        fertilizerToWater = readMap("fertilizer-to-water", scanner);
        waterToLight = readMap("water-to-light", scanner);
        lightToTemperature = readMap("light-to-temperature", scanner);
        temperatureToHumidity = readMap("temperature-to-humidity", scanner);
        humidityToLocation = readMap("humidity-to.location", scanner);
    }

    private void readSeeds(Scanner scanner) {
        scanner.next("seeds:");
        while(scanner.hasNextLong()) {
            seeds.add(scanner.nextLong());
        }
    }

    private static Converter readMap(
            String mapName,
            Scanner scanner) {
        scanner.next(mapName);
        var line = scanner.nextLine(); // skip one blank;
        line = scanner.nextLine();
        List<Mapping> mappings = new ArrayList<>();
        while (!line.isBlank()) {
            var parts = line.split(" ");
            long target = Long.parseLong(parts[0]);
            long source = Long.parseLong(parts[1]);
            long count = Long.parseLong(parts[2]);
            mappings.add(new Mapping(source, target, count));
            line = scanner.nextLine();
        }
        return new Converter(mappings);
    }

    public Converter seedToSoil() {
        return seedToSoil;
    }

    public Converter soilToFertilizer() {
        return soilToFertilizer;
    }

    public Converter fertilizerToWater() {
        return fertilizerToWater;
    }

    public Converter waterToLight() {
        return waterToLight;
    }

    public Converter lightToTemperature() {
        return lightToTemperature;
    }

    public Converter temperatureToHumidity() {
        return temperatureToHumidity;
    }

    public Converter humidityToLocation() {
        return humidityToLocation;
    }

    public long lowestLocationForSeeds() {
        return seeds.stream()
                .mapToLong(seedToSoil::valueFor)
                .map(soilToFertilizer::valueFor)
                .map(fertilizerToWater::valueFor)
                .map(waterToLight::valueFor)
                .map(lightToTemperature::valueFor)
                .map(temperatureToHumidity::valueFor)
                .map(humidityToLocation::valueFor)
                .min()
                .orElseThrow(() -> new IllegalStateException("no mapped value found?"));
    }

    public record Converter(List<Mapping> mappings) {
        public long valueFor(long offer) {
            for(Mapping m : mappings) {
                var value = m.valueFor(offer);
                if (value != -1) {
                    return value;
                }
            }
            return offer;
        }
    }
    public record Mapping(long source, long target, long range) {
        public long valueFor(long offer) {
            if (source <= offer && source + range >= offer) {
                var diff = offer-source;
                return target + diff;
            }
            return -1;
        }
    }
}
