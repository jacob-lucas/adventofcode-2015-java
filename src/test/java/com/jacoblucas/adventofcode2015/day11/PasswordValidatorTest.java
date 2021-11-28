package com.jacoblucas.adventofcode2015.day11;

import org.junit.Before;
import org.junit.Test;

import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.DIFFERENT_PAIRS;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.INCREASING_STRAIGHT;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.LENGTH;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.LOWERCASE_LETTERS;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.ONLY_VALID_LETTERS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PasswordValidatorTest {

    private PasswordValidator validator;

    @Before
    public void setUp() {
        validator = new PasswordValidator(
                LOWERCASE_LETTERS,
                LENGTH,
                INCREASING_STRAIGHT,
                ONLY_VALID_LETTERS,
                DIFFERENT_PAIRS);
    }

    @Test
    public void testLowercaseLettersOnly() {
        assertThat(LOWERCASE_LETTERS.validate("hijklmmn"), is(true));
        assertThat(LOWERCASE_LETTERS.validate("hiQklzmn"), is(false));
    }

    @Test
    public void testLength() {
        assertThat(LENGTH.validate("hijklmmn"), is(true));
        assertThat(LENGTH.validate("hijklmman"), is(false));
    }

    @Test
    public void testIncreasingStraight() {
        assertThat(INCREASING_STRAIGHT.validate("hijklmmn"), is(true));
        assertThat(INCREASING_STRAIGHT.validate("hiqklzmn"), is(false));
    }

    @Test
    public void testOnlyValidLetters() {
        assertThat(ONLY_VALID_LETTERS.validate("abbceffg"), is(true));
        assertThat(ONLY_VALID_LETTERS.validate("hijklmmn"), is(false));
    }

    @Test
    public void testDifferentPairs() {
        assertThat(DIFFERENT_PAIRS.validate("abbceffg"), is(true));
        assertThat(DIFFERENT_PAIRS.validate("hijklmmn"), is(false));
        assertThat(DIFFERENT_PAIRS.validate("abcdffaa"), is(true));
    }

    @Test
    public void testIsValid() {
        assertThat(validator.isValid("hijklmmn"), is(false));
        assertThat(validator.isValid("abbceffg"), is(false));
        assertThat(validator.isValid("abbcegjk"), is(false));
        assertThat(validator.isValid("abcdffaa"), is(true));
    }

    @Test
    public void testIncrement() {
        assertThat(validator.increment("abcdefgh"), is("abcdefgi"));
        assertThat(validator.increment("abcdefgz"), is("abcdefha"));
        assertThat(validator.increment("abcdzzzz"), is("abceaaaa"));
        assertThat(validator.increment("zzzzzzzz"), is("zzzzzzzz"));
    }

    @Test
    public void testNext() {
        assertThat(validator.next("abcdefgh"), is("abcdffaa"));
    }
}
