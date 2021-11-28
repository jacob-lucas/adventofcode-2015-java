package com.jacoblucas.adventofcode2015.day11;

@FunctionalInterface
public interface Validator {
    // Returns true if the string is valid, false otherwise
    boolean validate(final String str);
}
