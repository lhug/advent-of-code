package io.github.lhug.adventofcode.common;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public String day(int day) {
        String resource = String.format(resourcePath, day);
        try(InputStream stream = getClass().getResourceAsStream(resource);) {
            Objects.requireNonNull(stream, "no input for day number " + day);
            var output = new ByteArrayOutputStream();
            stream.transferTo(output);
            return output.toString(StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new IllegalArgumentException("could not read resource " + resource);
        }
    }
}
