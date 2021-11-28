package com.jacoblucas.adventofcode2015.day8;

import com.google.common.collect.ImmutableList;
import com.jacoblucas.adventofcode2015.utils.InputReader;

import java.io.IOException;
import java.util.List;

public class Day8 {
    private static final String DOUBLE_QUOTE = "\"";
    private static final String DOUBLE_BACKSLASH = "\\\\";
    private static final String ESCAPED_DOUBLE_QUOTE = "\\\"";
    private static final String ESCAPED_HEX = "\\x";

    private static final List<String> ESCAPE_LIST = ImmutableList.of(
            DOUBLE_QUOTE,
            DOUBLE_BACKSLASH,
            ESCAPED_DOUBLE_QUOTE,
            ESCAPED_HEX
    );

    public static void main(String[] args) throws IOException {
        final List<String> santasList = InputReader.read("day8-input.txt");

        final int codeCount = santasList.stream()
                .mapToInt(Day8::codeLength)
                .sum();

        final int escapedCount = santasList.stream()
                .map(Day8::escape)
                .mapToInt(Day8::codeLength)
                .sum();

        System.out.println(codeCount - escapedCount);

        final int encodedCount = santasList.stream()
                .map(Day8::encode)
                .mapToInt(Day8::codeLength)
                .sum();

        System.out.println(encodedCount - codeCount);
    }

    public static int codeLength(final String listItem) {
        return listItem.length();
    }

    public static String escape(final String str) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            final String s = str.substring(i, i+1);
            if (s.charAt(0) == '\\' || s.equals(DOUBLE_QUOTE)) {
                // handle escape
//                System.out.println("Detected escape at index [" + i + "] - substr = " + s);

                if (!s.equals(DOUBLE_QUOTE)) {
                    // value in s should be a single backslash
                    String seq = s;
                    for (int j=i+1; j<i+4; j++) {
                        seq += str.charAt(j);
                        if (ESCAPE_LIST.contains(seq)) {
                            break;
                        }
                    }

                    int inc = seq.length() - 1;

                    if (seq.equals(DOUBLE_BACKSLASH)) {
                        sb.append("\\");
                    } else if (seq.equals(ESCAPED_DOUBLE_QUOTE)) {
                        sb.append("'");
                    } else if (seq.equals(ESCAPED_HEX)) {
                        final String hex = str.substring(i + 2, i + 4);
                        int ascii = Integer.parseInt(hex, 16);
                        final char charValue = (char) ascii;
                        sb.append(charValue);
                        inc += 2;
                    }

                    i += inc;
                }
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public static String encode(final String str) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            final String s = str.substring(i, i + 1);
            if (s.charAt(0) == '\\' || s.equals(DOUBLE_QUOTE)) {
                // handle encode

                if (s.equals(DOUBLE_QUOTE)) {
                    sb.append("\\\"");
                } else {
                    String seq = s;
                    for (int j=i+1; j<i+4; j++) {
                        seq += str.charAt(j);
                        if (ESCAPE_LIST.contains(seq)) {
                            break;
                        }
                    }

                    if (seq.equals(DOUBLE_BACKSLASH)) {
                        sb.append("\\\\");
                    } else if (seq.equals(ESCAPED_DOUBLE_QUOTE)) {
                        sb.append("\\\\\\\"");
                    } else if (seq.equals(ESCAPED_HEX)) {
                        sb.append("\\\\x");
                    }

                    i += seq.length() - 1;
                }
            } else {
                sb.append(s);
            }
        }

        return "\"" + sb + "\"";
    }
}
