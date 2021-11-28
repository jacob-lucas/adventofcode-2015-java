package com.jacoblucas.adventofcode2015.day10;

public class Day10 {
    public static void main(String[] args) {
        String input = "1113222113";
        for (int i = 0; i < 40; i++) {
            input = LookAndSay.generate(input);
        }

        System.out.println(input.length());

        input = "1113222113";
        for (int i = 0; i < 50; i++) {
            input = LookAndSay.generate(input);
        }

        System.out.println(input.length());
    }
}
