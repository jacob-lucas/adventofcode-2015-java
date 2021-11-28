package com.jacoblucas.adventofcode2015.day13;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Table {
    public List<Person> seatingOrder;

    public Table(final List<Person> seatingOrder) {
        this.seatingOrder = seatingOrder;
    }

    public List<Person> nextTo(final String name) {
        final Optional<Person> person = seatingOrder.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        if (!person.isPresent()) {
            return Collections.emptyList();
        }

        final int index = seatingOrder.indexOf(person.get());
        final int n1 = index - 1 < 0 ? seatingOrder.size() - 1 : index - 1;
        final int n2 = index + 1 == seatingOrder.size() ? 0 : index + 1;
        return ImmutableList.of(seatingOrder.get(n1), seatingOrder.get(n2));
    }

    public int happyLevel() {
        return seatingOrder.stream()
                .mapToInt(person -> {
                    final List<Person> neighbours = nextTo(person.getName());
                    return neighbours.stream()
                            .mapToInt(n -> person.happyLevel(n.getName()))
                            .sum();
                })
                .sum();
    }
}
