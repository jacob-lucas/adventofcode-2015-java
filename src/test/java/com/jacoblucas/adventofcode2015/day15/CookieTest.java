package com.jacoblucas.adventofcode2015.day15;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CookieTest {
    static final Ingredient BUTTERSCOTCH = Ingredient.parse("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8");
    static final Ingredient CINNAMON = Ingredient.parse("Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3");

    @Test(expected = IllegalStateException.class)
    public void testMaxIngredientAmounts() {
         ImmutableCookie.builder()
                .ingredients(ImmutableMap.of(
                        BUTTERSCOTCH, 1,
                        CINNAMON, 1))
                .build();
    }

    @Test
    public void testGetScore() {
        final Cookie cookie = ImmutableCookie.builder()
                .ingredients(ImmutableMap.of(
                        BUTTERSCOTCH, 44,
                        CINNAMON, 56))
                .build();

        assertThat(cookie.getScore(), is(62842880));
    }

    @Test
    public void testGetScoreWithZero() {
        final Cookie cookie = ImmutableCookie.builder()
                .ingredients(ImmutableMap.of(BUTTERSCOTCH, 100))
                .build();

        assertThat(cookie.getScore(), is(0));
    }

    @Test
    public void testGetCalories() {
        final Cookie cookie = ImmutableCookie.builder()
                .ingredients(ImmutableMap.of(
                        BUTTERSCOTCH, 40,
                        CINNAMON, 60))
                .build();

        assertThat(cookie.getCalories(), is(500));
    }
}
