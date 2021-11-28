package com.jacoblucas.adventofcode2015.day14;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Day14Test {
    @Test
    public void testScore() {
        final String cometStr = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.";
        final String dancerStr = "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.";
        final Reindeer dancer = Reindeer.parse(dancerStr);
        final Reindeer comet = Reindeer.parse(cometStr);

        Map<Reindeer, Integer> scores = Day14.score(ImmutableList.of(comet, dancer), 1);
        assertThat(scores, is(ImmutableMap.of(
                dancer, 1,
                comet, 0
        )));

        scores = Day14.score(ImmutableList.of(comet, dancer), 140);
        assertThat(scores, is(ImmutableMap.of(
                dancer, 139,
                comet, 1
        )));

        scores = Day14.score(ImmutableList.of(comet, dancer), 1000);
        assertThat(scores, is(ImmutableMap.of(
                dancer, 689,
                comet, 311
        )));
    }
}
