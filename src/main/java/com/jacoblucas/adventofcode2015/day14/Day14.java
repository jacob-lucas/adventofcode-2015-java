package com.jacoblucas.adventofcode2015.day14;

import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day14-input.txt");
        final List<Reindeer> reindeer = input.stream()
                .map(Reindeer::parse)
                .collect(Collectors.toList());

        final int time = 2503;
        int winningDistance = 0;
        Reindeer winner = null;
        for (final Reindeer candidate : reindeer) {
            final int distance = candidate.distanceAfter(time);
//            System.out.println("After " + time + " seconds, " +  candidate.getName() + " has flown " + distance + " kms.");
            if (distance > winningDistance) {
                winner = candidate;
                winningDistance = distance;
            }
        }

        System.out.println("The winner is " + winner.getName() + " with a distance of " + winningDistance + " kms.");

        final Map<Reindeer, Integer> scores = score(reindeer, time);

        winner = getWinner(scores);
        System.out.println("The winner is " + winner.getName() + " with a score of " + scores.get(winner) + " points.");
    }

    public static Map<Reindeer, Integer> score(List<Reindeer> reindeer, int seconds) {
        final Map<Reindeer, Integer> scores = new HashMap<>();
        reindeer.forEach(r -> scores.put(r, 0));

        for (int i = 1; i <= seconds; i++) {
            final Map<Reindeer, Integer> distances = new HashMap<>();
            for (final Reindeer r : reindeer) {
                distances.put(r, r.distanceAfter(i));
            }

            final Reindeer winner = getWinner(distances);

//            System.out.println("After t=" + i + ", " + winner.getName() + " gets 1 point.");

            scores.put(winner, scores.get(winner) + 1);
        }

        return scores;
    }

    private static Reindeer getWinner(Map<Reindeer, Integer> distances) {
        return distances.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .get()
                .getKey();
    }
}
