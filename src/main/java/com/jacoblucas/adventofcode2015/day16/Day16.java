package com.jacoblucas.adventofcode2015.day16;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16 {
    public static Map<String, Integer> COMPOUNDS;

    static {
        COMPOUNDS = new HashMap<>();
        COMPOUNDS.put("children", 3);
        COMPOUNDS.put("cats", 7);
        COMPOUNDS.put("samoyeds", 2);
        COMPOUNDS.put("pomeranians", 3);
        COMPOUNDS.put("akitas", 0);
        COMPOUNDS.put("vizslas", 0);
        COMPOUNDS.put("goldfish", 5);
        COMPOUNDS.put("trees", 3);
        COMPOUNDS.put("cars", 2);
        COMPOUNDS.put("perfumes", 1);
    }

    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day16-input.txt");
        final List<Sue> sues = input.stream()
                .map(Sue::parse)
                .collect(Collectors.toList());

        for (final Sue sue : sues) {
            final List<Integer> compoundSums = sue.getCompounds().entrySet()
                    .stream()
                    .map(e -> e.getValue() - COMPOUNDS.get(e.getKey()))
                    .collect(Collectors.toList());

            if (compoundSums.equals(ImmutableList.of(0,0,0))) {
                System.out.println(sue);
            }
        }

        for (final Sue sue : sues) {
            final List<Boolean> compounds = sue.getCompounds().entrySet()
                    .stream()
                    .map(e -> {
                        final String key = e.getKey();
                        final int value = e.getValue() - COMPOUNDS.get(e.getKey());
                        if (ImmutableList.of("cats", "trees").contains(key)) {
                            return value > 0;
                        } else if (ImmutableList.of("pomeranians", "goldfish").contains(key)) {
                            return value < 0;
                        } else {
                            return value == 0;
                        }
                    })
                    .collect(Collectors.toList());

            if (compounds.stream().allMatch(b -> b)) {
                System.out.println(sue);
            }
        }
    }
}
