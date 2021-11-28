package com.jacoblucas.adventofcode2015.day8;

import com.jacoblucas.adventofcode2015.utils.InputReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day8Test {
    private List<String> testInput;

    @Before
    public void setUp() throws IOException {
        testInput = InputReader.readFile("src/test/resources/", "day8-test-input.txt");
    }

    @Test
    public void testCodeLength() {
        assertThat(Day8.codeLength(testInput.get(0)), is(2));
        assertThat(Day8.codeLength(testInput.get(1)), is(5));
        assertThat(Day8.codeLength(testInput.get(2)), is(10));
        assertThat(Day8.codeLength(testInput.get(3)), is(6));
    }

    @Test
    public void testEscapeExample1() {
        assertThat(Day8.escape(testInput.get(0)), is(""));
    }

    @Test
    public void testEscapeExample2() {
        assertThat(Day8.escape(testInput.get(1)), is("abc"));
    }

    @Test
    public void testEscapeExample3() {
        assertThat(Day8.escape(testInput.get(2)), is("aaa'aaa"));
    }

    @Test
    public void testEscapeExample4() {
        assertThat(Day8.escape(testInput.get(3)), is("'"));
    }

    @Test
    public void testEscapeExample5() {
        assertThat(Day8.escape(testInput.get(4)), is("aaa456'bb'ccc"));
    }

    @Test
    public void testEscapeExample6() {
        assertThat(Day8.escape(testInput.get(5)), is("lhyjky\\m'pvnm\\xmynpxnlhndmahjl"));
    }

    @Test
    public void testEncodeExample1() {
        final String encoded = Day8.encode(testInput.get(0));
        assertThat(encoded, is("\"\\\"\\\"\""));
        assertThat(encoded.length(), is(6));
    }

    @Test
    public void testEncodeExample2() {
        final String encoded = Day8.encode(testInput.get(1));
        assertThat(encoded, is("\"\\\"abc\\\"\""));
        assertThat(encoded.length(), is(9));
    }

    @Test
    public void testEncodeExample3() {
        final String encoded = Day8.encode(testInput.get(2));
        assertThat(encoded, is("\"\\\"aaa\\\\\\\"aaa\\\"\""));
        assertThat(encoded.length(), is(16));
    }

    @Test
    public void testEncodeExample4() {
        final String encoded = Day8.encode(testInput.get(3));
        assertThat(encoded, is("\"\\\"\\\\x27\\\"\""));
        assertThat(encoded.length(), is(11));
    }

    @Test
    public void testEncodeExample5() {
        final String encoded = Day8.encode(testInput.get(4));
        assertThat(encoded, is("\"\\\"aaa456\\\\x27bb\\\\x27ccc\\\"\""));
        assertThat(encoded.length(), is(27));
    }

    @Test
    public void testEncodeExample6() {
        final String encoded = Day8.encode(testInput.get(5));
        assertThat(encoded, is("\"\\\"lhyjky\\\\m\\\\\\\"pvnm\\\\xmynpxnlhndmahjl\\\"\""));
        assertThat(encoded.length(), is(41));
    }

    @Test
    public void testEncodeExample7() {
        final String encoded = Day8.encode(testInput.get(6));
//        "\"twaailoypu\\\"oas\\\"kpuuyedlaw\\\\\\xb0vzt\""
        assertThat(encoded, is("\"\\\"twaailoypu\\\\\\\"kpuuyedlaw               \\\"\""));
        assertThat(encoded.length(), is(41));
    }
}
