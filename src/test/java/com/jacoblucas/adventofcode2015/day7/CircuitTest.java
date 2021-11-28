package com.jacoblucas.adventofcode2015.day7;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class CircuitTest {
    private Circuit circuit;
    private List<Instruction> instructionList;

    @Before
    public void setUp() {
        final List<String> instructionStrs = ImmutableList.of(
                "123 -> x",
                "456 -> y",
                "x AND y -> d",
                "x OR y -> e",
                "x LSHIFT 2 -> f",
                "y RSHIFT 2 -> g",
                "NOT x -> h",
                "NOT y -> i");

        instructionList = instructionStrs.stream()
                .map(Instruction::parse)
                .collect(Collectors.toList());

        circuit = new Circuit(instructionList);
    }

    @Test
    public void testInitialisation() {
        final Map<String, Wire> wireMap = circuit.getWireMap();
        assertThat(wireMap.containsKey("x"), is(true));
        assertThat(wireMap.containsKey("y"), is(true));
        assertThat(wireMap.containsKey("d"), is(true));
        assertThat(wireMap.containsKey("e"), is(true));
        assertThat(wireMap.containsKey("f"), is(true));
        assertThat(wireMap.containsKey("g"), is(true));
        assertThat(wireMap.containsKey("h"), is(true));
        assertThat(wireMap.containsKey("i"), is(true));
    }

    @Test
    public void testReadyToConnect() {
        List<Instruction> readyToConnect = circuit.getReadyInstructions(Collections.emptyList());
        assertThat(readyToConnect, containsInAnyOrder(
                instructionList.get(0),
                instructionList.get(1)
        ));
    }

    @Test
    public void testIgnoresExecutedReadyToConnect() {
        final ImmutableList<Instruction> valueInstructions = ImmutableList.of(
                instructionList.get(0),
                instructionList.get(1));

        circuit.connect(valueInstructions);

        List<Instruction> readyToConnect = circuit.getReadyInstructions(valueInstructions);
        assertThat(readyToConnect, containsInAnyOrder(
                instructionList.get(2),
                instructionList.get(3),
                instructionList.get(4),
                instructionList.get(5),
                instructionList.get(6),
                instructionList.get(7)
        ));
    }

    @Test
    public void testIntegersAndWires() {
        final List<String> instructionStrs = ImmutableList.of(
                "123 -> x",
                "1 AND x -> y");

        final List<Instruction> instructionList = instructionStrs.stream()
                .map(Instruction::parse)
                .collect(Collectors.toList());

        circuit = new Circuit(instructionList);

        final ImmutableList<Instruction> valueInstructions = ImmutableList.of(instructionList.get(0));
        circuit.connect(valueInstructions);

        final List<Instruction> readyInstructions = circuit.getReadyInstructions(valueInstructions);
        assertThat(readyInstructions, containsInAnyOrder(instructionList.get(1)));

        circuit.connect(readyInstructions);

        final Map<String, Wire> wireMap = circuit.getWireMap();
        assertThat(wireMap.get("y").signalAvailable(), is(true));
    }
}
