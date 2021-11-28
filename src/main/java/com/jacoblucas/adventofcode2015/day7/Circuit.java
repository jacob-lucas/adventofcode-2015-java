package com.jacoblucas.adventofcode2015.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jacoblucas.adventofcode2015.day7.InstructionType.GATE;
import static com.jacoblucas.adventofcode2015.day7.InstructionType.VALUE;

public class Circuit {
    private final Map<String, Wire> wireMap;
    private final List<Instruction> instructionList;

    public Circuit(final List<Instruction> instructionList) {
        this.instructionList = instructionList;
        wireMap = new HashMap<>();
        instructionList.stream()
                .map(Instruction::getDestinationWireId)
                .distinct()
                .forEach(id -> wireMap.put(id, new Wire(id)));
    }

    public Map<String, Wire> getWireMap() {
        return wireMap;
    }

    public int getValueOnWire(final String id) {
        if (!wireMap.containsKey(id)) {
            throw new RuntimeException("Unknown wire ID: " + id);
        }
        return wireMap.get(id).getValue();
    }

    public void connect() {
        List<Instruction> executed = new ArrayList<>();
        List<Instruction> readyToConnect = getReadyInstructions(executed);
        while (!readyToConnect.isEmpty()) {
            // connect newly ready to connect instructions
            connect(readyToConnect);

            // mark these instructions as executed
            executed.addAll(readyToConnect);

            // redetect ready instructions, and repeat
            readyToConnect = getReadyInstructions(executed);
        }
    }

    public void connect(final List<Instruction> readyInstructions) {
        readyInstructions.forEach(i -> {
            final Wire wire = wireMap.get(i.getDestinationWireId());
            final String instructionText = i.getInstructionText();
            final String[] parts = instructionText.split(" ");
            if (i.getInstructionType() == VALUE) {
                wire.setValue(Integer.parseInt(parts[0]));
            } else if (i.getInstructionType() == GATE) {
                if (parts.length == 3) {
                    if (parts[1].equals("AND")) {
                        wire.setValue(GateLogic.and(parts[0], parts[2], wireMap));
                    } else {
                        wire.setValue(GateLogic.or(parts[0], parts[2], wireMap));
                    }
                } else {
                    wire.setValue(GateLogic.not(wireMap.get(parts[1])));
                }
            } else {
                if (parts.length == 1) {
                    wire.setValue(wireMap.get(parts[0]).getValue());
                } else {
                    if (parts[1].equals("LSHIFT")) {
                        wire.setValue(GateLogic.leftShift(wireMap.get(parts[0]), Integer.parseInt(parts[2])));
                    } else {
                        wire.setValue(GateLogic.rightShift(wireMap.get(parts[0]), Integer.parseInt(parts[2])));
                    }
                }
            }
        });
    }

    public List<Instruction> getReadyInstructions(final List<Instruction> executed) {
        // An instruction that is ready is one where all the wires in the instruction text have an available signal,
        // and has not yet been executed

        return instructionList.stream()
                .filter(i -> !executed.contains(i))
                .filter(i -> {
                    if (i.getInstructionType() == VALUE) {
                        return true;
                    } else {
                        final String instructionText = i.getInstructionText();
                        final String[] parts = instructionText.split(" ");
                        if (parts.length == 2) {
                            return wireMap.get(parts[1]).signalAvailable();
                        } else if (parts.length == 3) {
                            final String part1 = parts[0];
                            final Integer p1int = tryParse(part1);
                            final String part2 = parts[2];
                            final Integer p2int = tryParse(part2);

                            if (p1int != null && p2int != null) {
                                return true;
                            } else if (p1int == null && p2int != null) {
                                return wireMap.get(part1).signalAvailable();
                            } else if (p1int != null) {
                                return wireMap.get(part2).signalAvailable();
                            } else {
                                return wireMap.get(part1).signalAvailable() && wireMap.get(part2).signalAvailable();
                            }
                        } else {
                            return wireMap.get(parts[0]).signalAvailable();
                        }
                    }
                })
                .sorted((i1, i2) -> {
                    if (i1.getInstructionText().contains(i2.getDestinationWireId())) {
                        return -1;
                    } else if (i2.getInstructionText().contains(i1.getDestinationWireId())) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .collect(Collectors.toList());
    }

    static Integer tryParse(final String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
