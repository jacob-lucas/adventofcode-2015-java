package com.jacoblucas.adventofcode2015.day17;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class Day17Test {
    @Test
    public void testSumToExample1() {
        List<Integer> values = new ArrayList<>(ImmutableList.of(20, 15, 10, 5, 5));
        final List<List<Integer>> combinations = Day17.sumTo(25, values);
        assertThat(combinations, containsInAnyOrder(
                ImmutableList.of(10, 15),
                ImmutableList.of(5, 20),
                ImmutableList.of(5, 20),
                ImmutableList.of(5, 5, 15)));
    }

    @Test
    public void testSumToExample2() {
        List<Integer> values = new ArrayList<>(ImmutableList.of(1, 1, 1, 2, 3));
        final List<List<Integer>> combinations = Day17.sumTo(6, values);
        assertThat(combinations, containsInAnyOrder(
                ImmutableList.of(1, 1, 1, 3),
                ImmutableList.of(1, 2, 3),
                ImmutableList.of(1, 2, 3),
                ImmutableList.of(1, 2, 3)));
    }
}
