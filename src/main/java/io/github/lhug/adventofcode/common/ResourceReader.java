package io.github.lhug.adventofcode.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public final class ResourceReader {
    private final String resourcePath;

    private ResourceReader(int year){
        this.resourcePath = "/" + year + "/input_%d.txt";
    }

    public static ResourceReader year(int year) {
        Objects.requireNonNull(
                ResourceReader.class.getResource("/" + year),
                "no input for year " + year);
        return new ResourceReader(year);
    }

    public List<String> day(int day) {
        String resource = String.format(resourcePath, day);
        InputStream stream = Objects.requireNonNull(
                getClass().getResourceAsStream(resource),
                "no input for day number " + day);
        try(var reader = new BufferedReader(new InputStreamReader(stream))) {
            return reader.lines().toList();
        } catch (IOException ex) {
            throw new IllegalArgumentException("could not read resource " + resource);
        }
    }
}
