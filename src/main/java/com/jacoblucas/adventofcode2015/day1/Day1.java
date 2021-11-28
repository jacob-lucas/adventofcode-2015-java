package com.jacoblucas.adventofcode2015.day1;

import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day1 {
    public static final String UP = "(";
    public static final String DOWN = ")";

    public static int getFloor(final String directions) {
        return Arrays.stream(directions.split(""))
                .map(Day1::getDirectionValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int stopsToBasement(final String directions) {
        int pos = 0;
        int currentFloor = 0;
        for (final char direction : directions.toCharArray()) {
            pos++;
            currentFloor += getDirectionValue(String.valueOf(direction));
            if (currentFloor == -1) {
                break;
            }
        }
        return pos;
    }

    private static int getDirectionValue(final String direction ) {
        if (direction.equals(UP)) {
            return 1;
        } else if (direction.equals(DOWN)) {
            return -1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day1-input.txt");
        final String directions = input.get(0);

        System.out.println(getFloor(directions));
        System.out.println(stopsToBasement(directions));
    }
}
