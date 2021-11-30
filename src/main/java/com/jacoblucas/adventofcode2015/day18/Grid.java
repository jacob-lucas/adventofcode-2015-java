package com.jacoblucas.adventofcode2015.day18;

import java.util.Arrays;
import java.util.List;

public class Grid {
    private final int size;
    private int[][] gridArray;

    public Grid(final int size) {
        this.size = size;
        gridArray = new int[size][size];
    }

    public void init(final List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            final String inputLine = input.get(i);
            for (int j = 0; j < inputLine.length(); j++) {
                gridArray[i][j] = inputLine.charAt(j) == '#' ? 1 : 0;
            }
        }
    }

    public int[][] getGridArray() {
        return gridArray;
    }

    public void setGridArray(final int[][] gridArray) {
        this.gridArray = gridArray;
    }

    public int countNeighboursInState(
            final int i,
            final int j,
            final int state
    ) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                final int x = i + dx;
                final int y = j + dy;

                if (x >= 0 && x < size && y >= 0 && y < size && gridArray[x][y] == state) {
                    count++;
                }
            }
        }
        return count;
    }

    public void iterate() {
        iterate(false);
    }

    public void iterate(final boolean cornersOn) {
        final int[][] updated = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (
                        cornersOn &&
                                ((i == 0 && j == 0) ||
                                (i == 0 && j == size - 1) ||
                                (i == size - 1 && j == 0) ||
                                (i == size - 1 && j == size - 1))
                ) {
                    updated[i][j] = 1;
                } else {
                    final int state = gridArray[i][j];
                    final int neighbours = countNeighboursInState(i, j, 1);
                    if (state == 1) {
                        updated[i][j] = neighbours == 2 || neighbours == 3 ? 1 : 0;
                    } else {
                        updated[i][j] = neighbours == 3 ? 1 : 0;
                    }
                }
            }
        }

        setGridArray(updated);
    }

    public int count(final int state) {
        return Arrays.stream(gridArray)
                .map(Arrays::stream)
                .mapToInt(is -> is.filter(i -> i == state).sum())
                .sum();
    }
}
