package com.jacoblucas.adventofcode2015.day13;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class TableTest {
    private final Person alice = ModifiablePerson.create().setName("Alice");
    private final Person bob = ModifiablePerson.create().setName("Bob");
    private final Person carol = ModifiablePerson.create().setName("Carol");
    private final Person david = ModifiablePerson.create().setName("David");
    private final Table table = new Table(ImmutableList.of(
            david,
            alice,
            bob,
            carol
    ));

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
    public void testNextTo() {
        assertThat(table.nextTo(alice.getName()), containsInAnyOrder(david, bob));
        assertThat(table.nextTo(bob.getName()), containsInAnyOrder(alice, carol));
        assertThat(table.nextTo(carol.getName()), containsInAnyOrder(david, bob));
        assertThat(table.nextTo(david.getName()), containsInAnyOrder(alice, carol));
    }

    @Test
    public void testHappyLevel() {
        assertThat(table.happyLevel(), is(330));
    }
}
