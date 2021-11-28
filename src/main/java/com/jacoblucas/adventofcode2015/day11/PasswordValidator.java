package com.jacoblucas.adventofcode2015.day11;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordValidator {
    public static final Validator LOWERCASE_LETTERS = (password) -> password.toLowerCase().equals(password);

    public static final Validator LENGTH = (password) -> password.length() == 8;

    public static final Validator INCREASING_STRAIGHT = (password) -> {
        final char[] arr = password.toCharArray();
        for (int i = 0; i < arr.length - 3; i++) {
            int a = arr[i];
            int b = arr[i + 1];
            int c = arr[i + 2];
            if (b - a == 1 && c - b == 1) {
                return true;
            }
        }
        return false;
    };

    public static final Validator ONLY_VALID_LETTERS = (password) ->
            ImmutableList.of("I", "o", "l").stream().noneMatch(password::contains);

    public static final Validator DIFFERENT_PAIRS = (password) -> {
        final Set<Character> discovered = new HashSet<>();
        final char[] arr = password.toCharArray();
        for (int i=0; i<arr.length-1; i++) {
            char a = arr[i];
            char b = arr[i+1];
            if (a == b) {
                discovered.add(a);
            }
        }
        return discovered.size() >= 2;
    };

    private final List<Validator> validators;

    public PasswordValidator(final Validator... validators) {
        this.validators = Arrays.asList(validators);
    }

    public boolean isValid(final String password) {
        return validators.stream()
                .allMatch(v -> v.validate(password));
    }

    public String increment(final String password) {
        if (password.equals("zzzzzzzz")) {
            return password;
        }

        final int a = 'a';
        final int z = 'z';
        final char[] passwordArr = password.toCharArray();
        for (int i = 7; i >= 0; i--) {
            int ch = passwordArr[i];
            if (ch == z) {
                passwordArr[i] = (char) a;
            } else {
                passwordArr[i] = (char) (ch + 1);
                break;
            }
        }
        return String.valueOf(passwordArr);
    }

    public String next(final String password) {
        String candidate = password;
        boolean valid = false;
        while (!valid) {
            candidate = increment(candidate);
            valid = isValid(candidate);
        }
        return candidate;
    }
}
