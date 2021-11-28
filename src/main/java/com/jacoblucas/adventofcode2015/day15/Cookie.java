package com.jacoblucas.adventofcode2015.day15;

import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
public abstract class Cookie {
    public abstract Map<Ingredient, Integer> getIngredients();

    @Value.Check
    public void check() {
        final int teaspoons = getIngredients().values().stream()
                .mapToInt(Integer::valueOf)
                .sum();

        if (teaspoons != 100) {
            throw new IllegalStateException("Ingredient list must sum to 100 teaspoons - " + getIngredients());
        }
    }

    @Value.Derived
    public int getScore() {
        int capacity = 0;
        int durability = 0;
        int flavour = 0;
        int texture = 0;

        for (Map.Entry<Ingredient, Integer> entry : getIngredients().entrySet()) {
            Ingredient ingredient = entry.getKey();
            Integer amount = entry.getValue();

            capacity += amount * ingredient.getCapacity();
            durability += amount * ingredient.getDurability();
            flavour += amount * ingredient.getFlavour();
            texture += amount * ingredient.getTexture();
        }

        return Math.max(capacity, 0) * Math.max(durability, 0) * Math.max(flavour, 0) * Math.max(texture, 0);
    }

    @Value.Derived
    public int getCalories() {
        int calories = 0;
        for (Map.Entry<Ingredient, Integer> entry : getIngredients().entrySet()) {
            Ingredient ingredient = entry.getKey();
            Integer amount = entry.getValue();

            calories += amount * ingredient.getCalories();
        }
        return calories;
    }
}
