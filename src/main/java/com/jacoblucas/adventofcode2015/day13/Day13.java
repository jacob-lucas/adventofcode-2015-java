package com.jacoblucas.adventofcode2015.day13;

import com.google.common.collect.Collections2;
import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) throws IOException {
        final List<String> input = InputReader.read("day13-input.txt");
        final List<Person> people = parsePeople(input);

        int maxHappyLevel = getMaxHappyLevel(people);
        System.out.println(maxHappyLevel);

        final Person myself = ModifiablePerson.create().setName("Jacob");
        final List<Person> peopleIncludingMyself = new ArrayList<>(people);
        peopleIncludingMyself.add(myself);
        maxHappyLevel = getMaxHappyLevel(peopleIncludingMyself);
        System.out.println(maxHappyLevel);
    }

    public static int getMaxHappyLevel(final List<Person> people) {
        int maxHappyLevel = Integer.MIN_VALUE;
        final Collection<List<Person>> permutations = Collections2.permutations(people);
        for (final List<Person> seatingChart : permutations) {
            final Table table = new Table(seatingChart);
            final int happyLevel = table.happyLevel();
            if (happyLevel > maxHappyLevel) {
                maxHappyLevel = happyLevel;
            }
        }
        return maxHappyLevel;
    }

    public static List<Person> parsePeople(List<String> input) {
        final List<Person> people = new ArrayList<>();
        input.stream()
                .collect(Collectors.groupingBy(str -> str.split(" ")[0]))
                .forEach((key, value) -> {
                    final Person person = ModifiablePerson.create().setName(key);
                    for (final String str : value) {
                        final String[] parts = str.substring(0, str.length() - 1).split(" "); // remove trailing '.' char
                        int happyLevel = Integer.parseInt(parts[3]);
                        if (parts[2].equals("lose")) {
                            happyLevel *= -1;
                        }
                        person.addHappyLevel(parts[parts.length - 1], happyLevel);
                    }
                    people.add(person);
                });

        return people;
    }
}
