package com.jacoblucas.adventofcode2015.day12;

import com.fasterxml.jackson.databind.JsonNode;
import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day12 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day12-input.txt");
        final String json = input.get(0);

        final JsonTreeWalker walker = new JsonTreeWalker();
        final JsonNode jsonNode = walker.read(json);
        final List<Integer> integers = walker.collectNumbers(jsonNode, null);
        System.out.println(integers.stream().mapToInt(Integer::valueOf).sum());

        final List<Integer> integersIgnoringRed = walker.collectNumbers(jsonNode, "red");
        System.out.println(integersIgnoringRed.stream().mapToInt(Integer::valueOf).sum());
    }
}
