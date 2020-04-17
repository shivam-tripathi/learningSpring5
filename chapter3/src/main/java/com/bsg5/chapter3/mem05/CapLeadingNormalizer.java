package com.bsg5.chapter3.mem05;

import java.util.StringJoiner;
import java.util.stream.Stream;

import com.bsg5.chapter3.Normalizer;

public class CapLeadingNormalizer implements Normalizer {
    @Override
    public String transform(String text) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        Stream.of(text.trim().split("\\s"))
            .filter(s -> !s.isBlank())
            .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
            .forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
