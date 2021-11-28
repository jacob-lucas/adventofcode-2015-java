package com.jacoblucas.adventofcode2015.day7;

import org.immutables.value.Value;

import static com.jacoblucas.adventofcode2015.day7.InstructionType.GATE;
import static com.jacoblucas.adventofcode2015.day7.InstructionType.VALUE;
import static com.jacoblucas.adventofcode2015.day7.InstructionType.WIRE;

@Value.Immutable
public abstract class Instruction {
    public abstract String getDestinationWireId();

    public abstract String getInstructionText();

    @Value.Derived
    public InstructionType getInstructionType() {
        final String instructionText = getInstructionText();
        final String[] parts = instructionText.split(" ");
        if (instructionText.contains("AND") || instructionText.contains("OR") || instructionText.contains("NOT")) {
            return GATE;
        } else if (instructionText.contains("LSHIFT") || instructionText.contains("RSHIFT") || (parts.length == 1 && Circuit.tryParse(parts[0]) == null)) {
            return WIRE;
        } else {
            return VALUE;
        }
    }

    public static Instruction parse(final String str) {
        final String[] parts = str.split("->");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid instruction: " + str);
        }
        return ImmutableInstruction.builder()
                .destinationWireId(parts[1].trim())
                .instructionText(parts[0].trim())
                .build();
    }
}
