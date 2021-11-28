package com.jacoblucas.adventofcode2015.day14;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReindeerTest {
    @Test
    public void testParse() {
        final String cometStr = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.";

        final Reindeer comet = Reindeer.parse(cometStr);
        assertThat(comet, is(ImmutableReindeer.builder()
                .name("Comet")
                .speed(14)
                .flyDuration(10)
                .restDuration(127)
                .build()));
    }

    @Test
    public void testCometDistanceAfter() {
        final String cometStr = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.";
        final Reindeer comet = Reindeer.parse(cometStr);

        assertThat(comet.distanceAfter(1), is(14));
        assertThat(comet.distanceAfter(10), is(140));
        assertThat(comet.distanceAfter(11), is(140));
        assertThat(comet.distanceAfter(12), is(140));
        assertThat(comet.distanceAfter(148), is(140+140));
        assertThat(comet.distanceAfter(1000), is(1120));
    }

    @Test
    public void testDancerDistanceAfter() {
        final String dancerStr = "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.";
        final Reindeer dancer = Reindeer.parse(dancerStr);

        assertThat(dancer.distanceAfter(1), is(16));
        assertThat(dancer.distanceAfter(10), is(160));
        assertThat(dancer.distanceAfter(11), is(176));
        assertThat(dancer.distanceAfter(12), is(176));
        assertThat(dancer.distanceAfter(185), is(352));
        assertThat(dancer.distanceAfter(1000), is(1056));
    }
}
