package com.jacoblucas.adventofcode2015.day18;

import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Day18 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day18-input.txt");

        final Grid grid = new Grid(input.size());
        grid.init(input);

        IntStream.range(0, 100).forEach(i -> grid.iterate());
        System.out.println(grid.count(1));

        grid.init(input);
        int[][] gridArray = grid.getGridArray();
        gridArray[0][0] = 1;
        gridArray[input.size()-1][0] = 1;
        gridArray[0][input.size()-1] = 1;
        gridArray[input.size()-1][input.size()-1] = 1;
        grid.setGridArray(gridArray);

        IntStream.range(0, 100).forEach(i -> grid.iterate(true));
        System.out.println(grid.count(1));
    }
}
