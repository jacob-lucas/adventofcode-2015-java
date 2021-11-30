package com.jacoblucas.adventofcode2015.day18;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GridTest {
    @Test
    public void testInit() {
        Grid grid = new Grid(6);
        grid.init(ImmutableList.of(
                ".#.#.#",
                "...##.",
                "#....#",
                "..#...",
                "#.#..#",
                "####.."));

        final int[][] expected = {
                {0, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 0, 0},
        };

        assertThat(grid.getGridArray(), is(expected));
    }

    @Test
    public void testCountNeighbours() {
        Grid grid = new Grid(6);
        grid.init(ImmutableList.of(
                ".#.#.#",
                "...##.",
                "#....#",
                "..#...",
                "#.#..#",
                "####.."));

        assertThat(grid.countNeighboursInState(0, 1, 1), is(0));
        assertThat(grid.countNeighboursInState(0, 2, 1), is(3));
        assertThat(grid.countNeighboursInState(5, 1, 1), is(4));
        assertThat(grid.countNeighboursInState(3, 2, 1), is(1));

        assertThat(grid.countNeighboursInState(0, 1, 0), is(5));
        assertThat(grid.countNeighboursInState(5, 5, 0), is(2));
    }

    @Test
    public void testIterate() {
        Grid grid = new Grid(6);
        grid.init(ImmutableList.of(
                ".#.#.#",
                "...##.",
                "#....#",
                "..#...",
                "#.#..#",
                "####.."));

        grid.iterate();
        grid.iterate();
        grid.iterate();
        grid.iterate();

        final int[][] expected = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
        };

        assertThat(grid.getGridArray(), is(expected));
    }
}
