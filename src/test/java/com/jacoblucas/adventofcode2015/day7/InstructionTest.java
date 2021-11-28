package com.jacoblucas.adventofcode2015.day7;

import org.junit.Test;

import static com.jacoblucas.adventofcode2015.day7.InstructionType.GATE;
import static com.jacoblucas.adventofcode2015.day7.InstructionType.VALUE;
import static com.jacoblucas.adventofcode2015.day7.InstructionType.WIRE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstructionTest {
    @Test
    public void testParseValueInstruction() {
        final String text = "123 -> x";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("x")
                .instructionText("123")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(VALUE));
    }

    @Test
    public void testParseAndInstruction() {
        final String text = "x AND y -> z";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("z")
                .instructionText("x AND y")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(GATE));
    }

    @Test
    public void testParseOrInstruction() {
        final String text = "x OR y -> z";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("z")
                .instructionText("x OR y")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(GATE));
    }

    @Test
    public void testParseLeftShiftInstruction() {
        final String text = "p LSHIFT 2 -> q";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("q")
                .instructionText("p LSHIFT 2")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(WIRE));
    }

    @Test
    public void testParseRightShiftInstruction() {
        final String text = "y RSHIFT 2 -> g";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("g")
                .instructionText("y RSHIFT 2")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(WIRE));
    }

    @Test
    public void testParseNotInstruction() {
        final String text = "NOT y -> i";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("i")
                .instructionText("NOT y")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(GATE));
    }

    @Test
    public void testParseWireInstruction() {
        final String text = "lx -> a";
        final Instruction expected = ImmutableInstruction.builder()
                .destinationWireId("a")
                .instructionText("lx")
                .build();

        final Instruction instruction = Instruction.parse(text);
        assertThat(instruction, is(expected));
        assertThat(instruction.getInstructionType(), is(WIRE));
    }
}
