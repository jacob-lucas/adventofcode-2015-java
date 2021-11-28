package com.jacoblucas.adventofcode2015.day12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public class JsonTreeWalkerTest {
    private JsonTreeWalker walker;

    @Before
    public void setUp() {
        walker = new JsonTreeWalker();
    }

    @Test
    public void testExample1() throws JsonProcessingException {
        final JsonNode jsonNode = walker.read("[1,2,3]");
//        assertThat(walker.contains(jsonNode, "red"), is(false));
        assertThat(walker.collectNumbers(jsonNode, "red"), containsInAnyOrder(1,2,3));
    }

    @Test
    public void testExample2() throws JsonProcessingException {
        final JsonNode jsonNode = walker.read("[1,{\"c\":\"red\",\"b\":2},3]");
//        assertThat(walker.contains(jsonNode, "red"), is(true));
        assertThat(walker.collectNumbers(jsonNode, "red"), containsInAnyOrder(1,3));
    }

    @Test
    public void testExample3() throws JsonProcessingException {
        final JsonNode jsonNode = walker.read("{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}");
//        assertThat(walker.contains(jsonNode, "red"), is(true));
        assertThat(walker.collectNumbers(jsonNode, "red"), is(empty()));
    }

    @Test
    public void testExample4() throws JsonProcessingException {
        final JsonNode jsonNode = walker.read("[1,\"red\",5]");
//        assertThat(walker.contains(jsonNode, "red"), is(true));
        assertThat(walker.collectNumbers(jsonNode, "red"), containsInAnyOrder(1,5));
    }
}
