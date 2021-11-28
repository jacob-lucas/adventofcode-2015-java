package com.jacoblucas.adventofcode2015.day7;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GateLogicTest {
    private final Map<String, Wire> wireMap = ImmutableMap.of(
            "a", new Wire("a", 123),
            "b", new Wire("a", 456)
    );
    private final Wire a = new Wire("a", 123);
    private final Wire b = new Wire("b", 456);

    @Test
    public void testAnd() {
        assertThat(GateLogic.and("a", "b", wireMap), is(72));
    }

    @Test
    public void testOr() {
        assertThat(GateLogic.or("a", "b", wireMap), is(507));
    }

    @Test
    public void testLeftShift() {
        assertThat(GateLogic.leftShift(a, 2), is(492));
    }

    @Test
    public void testRightShift() {
        assertThat(GateLogic.rightShift(b, 2), is(114));
    }

    @Test
    public void testNot() {
        assertThat(GateLogic.not(a), is(-124)); // 65412
        assertThat(GateLogic.not(b), is(-457)); // 65079
    }
}
