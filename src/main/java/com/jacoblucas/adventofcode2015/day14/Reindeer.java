package com.jacoblucas.adventofcode2015.day14;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Reindeer {
    public abstract String getName();

    public abstract int getSpeed();

    public abstract int getFlyDuration();

    public abstract int getRestDuration();

    public int distanceAfter(final int seconds) {
        boolean flying = true;
        int distance = 0;
        int toggleSecond = getFlyDuration();
        for (int i = 1; i <= seconds; i++) {
            if (flying) {
                distance += getSpeed();
            }

//            System.out.println("sec=" + i + "\tdist=" + distance + "\tflying=" + flying);

            if (i == toggleSecond) {
                toggleSecond = flying ? i + getRestDuration() : i + getFlyDuration();
                flying = !flying;
            }
        }
        return distance;
    }

    public static Reindeer parse(final String str) {
        final String[] parts = str.split(" ");
        return ImmutableReindeer.builder()
                .name(parts[0])
                .speed(Integer.parseInt(parts[3]))
                .flyDuration(Integer.parseInt(parts[6]))
                .restDuration(Integer.parseInt(parts[13]))
                .build();
    }
}
