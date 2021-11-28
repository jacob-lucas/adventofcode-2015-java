package com.jacoblucas.adventofcode2015.day10;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LookAndSay {
    public static String generate(final String prev) {
        if (prev.length() == 1) {
            return "1" + prev;
        }

        final StringBuilder result = new StringBuilder();
        final char[] value = prev.toCharArray();

        result.append(value[0]);
        for (int i  = 1; i < value.length; i++) {
            if (value[i-1] != value[i]) {
                result.append(" ");
            }
            result.append(value[i]);
        }

        return Arrays.stream(result.toString().split(" "))
                .map(str -> "" + str.length() + str.charAt(0))
                .collect(Collectors.joining());
    }
}
