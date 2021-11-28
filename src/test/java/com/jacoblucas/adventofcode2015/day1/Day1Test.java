package com.jacoblucas.adventofcode2015.day1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day1Test {
    @Test
    public void testGetFloor() {
        assertThat(Day1.getFloor("(())"), is(0));
        assertThat(Day1.getFloor("()()"), is(0));
        assertThat(Day1.getFloor("((("), is(3));
        assertThat(Day1.getFloor("(()(()("), is(3));
        assertThat(Day1.getFloor("))((((("), is(3));
        assertThat(Day1.getFloor("())"), is(-1));
        assertThat(Day1.getFloor("))("), is(-1));
        assertThat(Day1.getFloor(")))"), is(-3));
        assertThat(Day1.getFloor(")())())"), is(-3));
        assertThat(Day1.getFloor(""), is(0));
    }

    @Test
    public void testStepsToBasement() {
        assertThat(Day1.stopsToBasement(")"), is(1));
        assertThat(Day1.stopsToBasement("()())"), is(5));
    }
}
