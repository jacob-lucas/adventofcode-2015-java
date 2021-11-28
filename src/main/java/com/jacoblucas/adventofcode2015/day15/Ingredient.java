package com.jacoblucas.adventofcode2015.day15;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Ingredient {
    public abstract String getName();

    public abstract int getCapacity();

    public abstract int getDurability();

    public abstract int getFlavour();

    public abstract int getTexture();

    public abstract int getCalories();

    @Override
    public String toString() {
        return getName();
    }

    public static Ingredient parse(String str) {
        final String[] parts = str.split(" ");
        return ImmutableIngredient.builder()
                .name(parts[0].replaceAll(":", ""))
                .capacity(Integer.parseInt(parts[2].replaceAll(",", "")))
                .durability(Integer.parseInt(parts[4].replaceAll(",", "")))
                .flavour(Integer.parseInt(parts[6].replaceAll(",", "")))
                .texture(Integer.parseInt(parts[8].replaceAll(",", "")))
                .calories(Integer.parseInt(parts[10].replaceAll(",", "")))
                .build();
    }
}
