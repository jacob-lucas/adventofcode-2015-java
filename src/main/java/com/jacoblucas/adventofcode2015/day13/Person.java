package com.jacoblucas.adventofcode2015.day13;

import org.immutables.value.Value;

import java.util.Map;

@Value.Modifiable
public abstract class Person {
    public abstract String getName();
    public abstract  Map<String, Integer> getHappyMap();

    @Override
    public String toString() {
        return getName() + " :: " + getHappyMap().toString();
    }

    public void addHappyLevel(final String otherPersonName, final int happyLevel) {
        getHappyMap().put(otherPersonName, happyLevel);
    }

    public int happyLevel(final String otherPersonName) {
        return getHappyMap().getOrDefault(otherPersonName, 0);
    }
}
