package com.jacoblucas.adventofcode2015.day11;

import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.DIFFERENT_PAIRS;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.INCREASING_STRAIGHT;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.LENGTH;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.LOWERCASE_LETTERS;
import static com.jacoblucas.adventofcode2015.day11.PasswordValidator.ONLY_VALID_LETTERS;

public class Day11 {
    public static void main(String[] args) {
        final PasswordValidator validator = new PasswordValidator(
                LOWERCASE_LETTERS,
                LENGTH,
                INCREASING_STRAIGHT,
                ONLY_VALID_LETTERS,
                DIFFERENT_PAIRS);

        String next = validator.next("hxbxwxba");
        System.out.println(next);

        next = validator.next(next);
        System.out.println(next);
    }
}
