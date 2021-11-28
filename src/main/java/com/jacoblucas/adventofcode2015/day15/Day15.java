package com.jacoblucas.adventofcode2015.day15;

import com.google.common.collect.ImmutableMap;
import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day15-input.txt");
        final List<Ingredient> ingredients = input.stream()
                .map(Ingredient::parse)
                .collect(Collectors.toList());

        Cookie cookie = getBestCookie(ingredients);
        System.out.println(cookie.getScore());

        cookie = getBestCookie(ingredients, 500);
        System.out.println(cookie.getScore());
    }

    public static Cookie getBestCookie(final List<Ingredient> ingredients) {
        return getBestCookie(ingredients, Integer.MAX_VALUE);
    }

    public static Cookie getBestCookie(final List<Ingredient> ingredients, final int calorieCeiling) {
        int bestScore = Integer.MIN_VALUE;
        Cookie bestCookie = null;
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100 - a; b++) {
                for (int c = 0; c <= 100 - a - b; c++) {
                    for (int d = 0; d <= 100 - a - b - c; d++) {
                        try {
                            final Cookie cookie = ImmutableCookie.builder()
                                    .ingredients(ImmutableMap.of(
                                            ingredients.get(0), a,
                                            ingredients.get(1), b,
                                            ingredients.get(2), c,
                                            ingredients.get(3), d))
                                    .build();
                            final int score = cookie.getScore();
                            if (score > bestScore && cookie.getCalories() <= calorieCeiling) {
                                bestCookie = cookie;
                                bestScore = score;
                            }
                        } catch (final IllegalStateException ise) {
                            continue;
                        }
                    }
                }
            }
        }
        return bestCookie;
    }
}
