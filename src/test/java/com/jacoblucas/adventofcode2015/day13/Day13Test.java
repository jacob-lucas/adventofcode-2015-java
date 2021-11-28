package com.jacoblucas.adventofcode2015.day13;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class Day13Test {
    private final Person alice = ModifiablePerson.create().setName("Alice");
    private final Person bob = ModifiablePerson.create().setName("Bob");
    private final Person carol = ModifiablePerson.create().setName("Carol");
    private final Person david = ModifiablePerson.create().setName("David");

    @Before
    public void setUp() {
        alice.addHappyLevel(bob.getName(), 54);
        alice.addHappyLevel(carol.getName(), -79);
        alice.addHappyLevel(david.getName(), -2);

        bob.addHappyLevel(alice.getName(), 83);
        bob.addHappyLevel(carol.getName(), -7);
        bob.addHappyLevel(david.getName(), -63);

        carol.addHappyLevel(alice.getName(), -62);
        carol.addHappyLevel(bob.getName(), 60);
        carol.addHappyLevel(david.getName(), 55);

        david.addHappyLevel(alice.getName(), 46);
        david.addHappyLevel(bob.getName(), -7);
        david.addHappyLevel(carol.getName(), 41);
    }

    @Test
    public void testGetMaxHappyLevel() {
        assertThat(Day13.getMaxHappyLevel(ImmutableList.of(alice, carol, bob, david)), is(330));
    }

    @Test
    public void testParsePeople() {
        final List<String> input = ImmutableList.of(
                "Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.",
                "Alice would lose 2 happiness units by sitting next to David.",
                "Bob would gain 83 happiness units by sitting next to Alice.",
                "Bob would lose 7 happiness units by sitting next to Carol.",
                "Bob would lose 63 happiness units by sitting next to David.",
                "Carol would lose 62 happiness units by sitting next to Alice.",
                "Carol would gain 60 happiness units by sitting next to Bob.",
                "Carol would gain 55 happiness units by sitting next to David.",
                "David would gain 46 happiness units by sitting next to Alice.",
                "David would lose 7 happiness units by sitting next to Bob.",
                "David would gain 41 happiness units by sitting next to Carol.");

        final List<Person> people = Day13.parsePeople(input);
        assertThat(people, containsInAnyOrder(alice, bob, carol, david));
    }
}
