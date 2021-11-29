package com.jacoblucas.adventofcode2015.day16;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SueTest {
    @Test
    public void testParse() {
        final Sue sue = Sue.parse("Sue 494: trees: 8, samoyeds: 1, perfumes: 5");
        assertThat(sue, is(ImmutableSue.builder()
                .ordinal(494)
                .compounds(ImmutableMap.of(
                        "trees", 8,
                        "samoyeds", 1,
                        "perfumes", 5))
                .build()));
    }
}
