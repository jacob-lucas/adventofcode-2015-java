package com.jacoblucas.adventofcode2015.day9;

import com.google.common.collect.Collections2;
import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day9 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day9-input.txt");

        final Set<String> locations = new HashSet<>();
        final Map<String, Map<String, Integer>> graph = new HashMap<>();

        input.forEach(dist -> {
            final String[] parts = dist.split(" ");
            final String loc1 = parts[0];
            final String loc2 = parts[2];
            final int distance = Integer.parseInt(parts[4]);

            locations.add(loc1);
            locations.add(loc2);

            final Map<String, Integer> loc1Map = graph.getOrDefault(loc1, new HashMap<>());
            loc1Map.put(loc2, distance);
            graph.put(loc1, loc1Map);

            final Map<String, Integer> loc2Map = graph.getOrDefault(loc2, new HashMap<>());
            loc2Map.put(loc1, distance);
            graph.put(loc2, loc2Map);
        });

        List<Integer> distances = new ArrayList<>();
        final Collection<List<String>> permutations = Collections2.permutations(locations);
        for (final List<String> order : permutations) {
            int d = 0;
            for (int i=1; i<order.size(); i++) {
                d += graph.get(order.get(i-1)).get(order.get(i));
            }
            distances.add(d);
        }
        System.out.println(distances.stream().min(Comparator.naturalOrder()));
        System.out.println(distances.stream().max(Comparator.naturalOrder()));
    }
}
