package com.jacoblucas.adventofcode2015.day16;

import com.google.common.collect.ImmutableMap;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
public abstract class Sue {
    public abstract int getOrdinal();

    public abstract Map<String, Integer> getCompounds();

    public static Sue parse(final String str) {
        final String[] parts = str.replaceAll(":", "").replaceAll(",", "").split(" ");
        return ImmutableSue.builder()
                .ordinal(Integer.parseInt(parts[1]))
                .compounds(ImmutableMap.of(
                        parts[2], Integer.parseInt(parts[3]),
                        parts[4], Integer.parseInt(parts[5]),
                        parts[6], Integer.parseInt(parts[7])))
                .build();
    }
}
