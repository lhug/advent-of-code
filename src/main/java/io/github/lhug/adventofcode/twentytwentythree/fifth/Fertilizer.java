package io.github.lhug.adventofcode.twentytwentythree.fifth;

import java.util.*;
import java.util.stream.Stream;

public class Fertilizer {
    private final List<Long> seeds = new ArrayList<>();
    private final List<ValueRange> seedRanges = new ArrayList<>();
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
        convertSeedRanges();
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

    private void convertSeedRanges() {
        for (int i = 0; i < seeds.size(); i += 2) {
            seedRanges.add(
                    new ValueRange(
                            seeds.get(i),
                            seeds.get(i + 1)
                    )
            );
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
        mappings.sort(Comparator.comparing(Mapping::source));
        return new Converter(mappings);
    }

    public List<ValueRange> seedRanges() {
        return seedRanges;
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

    public long lowestLocationForSeedRange(List<ValueRange> offer) {
        return Stream.of(offer)
                .map(seedToSoil::rangesFor)
                .map(soilToFertilizer::rangesFor)
                .map(fertilizerToWater::rangesFor)
                .map(waterToLight::rangesFor)
                .map(lightToTemperature::rangesFor)
                .map(temperatureToHumidity::rangesFor)
                .map(humidityToLocation::rangesFor)
                .flatMap(List::stream)
                .mapToLong(ValueRange::source)
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

        public List<ValueRange> rangesFor(List<ValueRange> ranges) {
            List<ValueRange> results = new ArrayList<>();
            for(ValueRange range : ranges) {
                results.addAll(mapSingleRange(range));
            }
            return results.isEmpty()
                    ? ranges
                    : results;
        }

        private List<ValueRange> mapSingleRange(ValueRange range) {
            List<ValueRange> results = new ArrayList<>();
            var notFound = List.of(range);
            ValueRange examined = range;
            for(Mapping mapping : mappings) {
                if (examined == null) {
                    break;
                }
                List<ValueRange> mappingResult = new ArrayList<>(mapping.targetRangesFor(examined));
                if(mappingResult.equals(notFound)) {
                    continue; // not mapped, need to continue
                }
                if (mappingResult.size() == 1) {
                    if (results.isEmpty())
                        return mappingResult;
                    else
                        results.addAll(mappingResult);
                    examined = null;
                } else if (mappingResult.size() == 2) {
                    results.add(mappingResult.get(0));
                    var second = mappingResult.get(1);
                    var targetMapping = new ValueRange(mapping.target, mapping.range);
                    if(targetMapping.contains(second.maxValue())) {
                        results.add(second);
                        examined = null;
                    } else {
                        examined = second;
                    }
                } else {
                    // 3 mapping results
                    examined = mappingResult.removeLast();
                    results.addAll(mappingResult);
                }
            }
            if (examined != null) {
                results.add(examined);
            }
            return results;
        }
    }
    public record Mapping(long source, long target, long range) {
        public long valueFor(long offer) {
            if (source <= offer && source + range - 1 >= offer) {
                var diff = offer-source;
                return target + diff;
            }
            return -1;
        }

        public List<ValueRange> targetRangesFor(ValueRange offer) {
            var sourceRange = new ValueRange(source, range);
            var processed = sourceRange.process(offer);
            return processed.stream()
                    .map(range -> {
                        if (sourceRange.equals(range)) {
                            return new ValueRange(target, sourceRange.range);
                        }
                        if (!sourceRange.contains(range.source())){
                            return range;
                        }
                        return new ValueRange(
                                valueFor(range.source),
                                range.range);

                    })
                    .toList();
        }
    }

    public record ValueRange(long source, long range) {
        public List<ValueRange> process(ValueRange offer) {
            if (this.equals(offer)) {
                return List.of(offer);
            }
            if (contains(offer)) {
                if (offer.source >= source() && offer.maxValue() >= maxValue()) {
                    long offset = offer.source - source();
                    long newRange = offer.range - range + offset;
                    return List.of(
                            new ValueRange(offer.source, range - offset),
                            new ValueRange(source + range, newRange)
                    );
                }
                if (offer.source < source && offer.maxValue() < maxValue()) {
                    long offset = source - offer.source;
                    return List.of(
                            new ValueRange(offer.source, offset),
                            new ValueRange(source, offer.range - offset)
                    );
                }
            }
            if (offer.source <= source && offer.maxValue() >= maxValue()) {
                long minOffset = source - offer.source;
                long maxOffset = offer.maxValue() - maxValue();
                List<ValueRange> ranges = new ArrayList<>();
                if (minOffset > 0) {
                    ranges.add(new ValueRange(offer.source, minOffset));
                }
                ranges.add(this);
                if (maxOffset > 0) {
                    ranges.add(new ValueRange(maxValue() + 1, maxOffset));
                }
                return ranges;
            }
            return List.of(offer);
        }

        private long maxValue() {
            return source + range - 1;
        }

        private boolean contains(ValueRange offer) {
            return (offer.maxValue() <= maxValue() && offer.maxValue() >= source)
                    || (offer.source >= source && offer.source <= maxValue());
        }

        public boolean contains(long value) {
            return value >= source && value <= maxValue();
        }
    }
}
