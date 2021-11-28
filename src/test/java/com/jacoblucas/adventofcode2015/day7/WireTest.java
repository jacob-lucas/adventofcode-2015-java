package com.jacoblucas.adventofcode2015.day7;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WireTest {
    private Wire a;

    @Before
    public void setUp() {
        a = new Wire("a");
    }

    @Test
    public void testGetId() {
        assertThat(a.getId(), is("a"));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetWithoutSignal() {
        final Wire b = new Wire("b");
        b.getValue();
    }

    @Test
    public void setValuePositive() {
        final int value = 1234;
        a.setValue(value);
        assertThat(a.getValue(), is(value));
    }

    @Test
    public void setValueNegative() {
        final int value = -124;
        a.setValue(value);
        assertThat(a.getValue(), is(65412));
    }
}
