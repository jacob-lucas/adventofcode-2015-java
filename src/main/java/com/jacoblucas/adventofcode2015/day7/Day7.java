package com.jacoblucas.adventofcode2015.day7;

import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day7-input.txt");
        final List<Instruction> instructionList = input.stream()
                .map(Instruction::parse)
                .collect(Collectors.toList());

        final Circuit circuit = new Circuit(instructionList);

        circuit.connect();

        final int a1 = circuit.getValueOnWire("a");
        System.out.println(a1);

        final Map<String, Wire> wireMap = circuit.getWireMap();
        wireMap.values().forEach(Wire::reset);
        wireMap.get("b").setValue(a1);

        circuit.connect();

        final int a2 = circuit.getValueOnWire("a");
        System.out.println(a2);
    }
}
