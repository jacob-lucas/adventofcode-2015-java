package com.jacoblucas.adventofcode2015.day17;

import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day17 {
    public static void main(String[] args) throws IOException {
        final List<Integer> input = InputReader.read("day17-input.txt")
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        final List<List<Integer>> combinations = sumTo(150, input);
        System.out.println(combinations.size());

        final Integer minCount = combinations.stream()
                .collect(Collectors.groupingBy(List::size))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByKey())
                .get()
                .getKey();

        System.out.println(minCount);
    }

    public static List<List<Integer>> sumTo(int sum, List<Integer> values) {
        List<List<Integer>> result = new ArrayList<>();
        final List<Integer> sorted = values.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < sorted.size(); i++) {
            final Collection<List<Integer>> permutations = getPermutations(sorted, sorted.size() - i);
            permutations.stream()
                    .filter(p -> p.stream().mapToInt(Integer::valueOf).sum() == sum)
                    .forEach(result::add);
        }
        return result;
    }

    private static List<List<Integer>> getPermutations(final List<Integer> values, final int length) {
        int[] A = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            A[i] = values.get(i);
        }

        List<List<Integer>> permutations = new ArrayList<>();
        getPermutations(A, 0, length, permutations, new ArrayList<>());
        return permutations;
    }

    private static void getPermutations(
            final int[] arr,
            final int i,
            final int k,
            final List<List<Integer>> permutations,
            final List<Integer> tmp
    ) {
        if (arr.length == 0 || k > arr.length) {
            return;
        }

        if (k == 0) {
            permutations.add(new ArrayList<>(tmp));
            return;
        }

        for (int j = i; j < arr.length; j++) {
            tmp.add(arr[j]);
            getPermutations(arr, j + 1, k - 1, permutations, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
