package com.jacoblucas.adventofcode2015.day15;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IngredientTest {
    @Test
    public void testParse() {
        final Ingredient butterscotch = Ingredient.parse("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8");
        assertThat(butterscotch, is(ImmutableIngredient.builder()
                .name("Butterscotch")
                .capacity(-1)
                .durability(-2)
                .flavour(6)
                .texture(3)
                .calories(8)
                .build()));
    }
}
