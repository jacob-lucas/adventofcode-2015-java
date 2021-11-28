package com.jacoblucas.adventofcode2015.day10;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LookAndSayTest {
    @Test
    public void example1() {
        assertThat(LookAndSay.generate("1"), is("11"));
    }

    @Test
    public void example2() {
        assertThat(LookAndSay.generate("11"), is("21"));
    }

    @Test
    public void example3() {
        assertThat(LookAndSay.generate("21"), is("1211"));
    }

    @Test
    public void example4() {
        assertThat(LookAndSay.generate("1211"), is("111221"));
    }

    @Test
    public void example5() {
        assertThat(LookAndSay.generate("111221"), is("312211"));
    }

    @Test
    public void example6() {
        assertThat(LookAndSay.generate("1111111111"), is("101"));
    }
}
