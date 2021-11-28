package com.jacoblucas.adventofcode2015.day7;

import java.util.Map;

public class GateLogic {
    public static int and(final String a, final String b, final Map<String, Wire> wireMap) {
        final int aVal = getVal(a, wireMap);
        final int bVal = getVal(b, wireMap);
        return aVal & bVal;
    }

    public static int or(final String a, final String b, final Map<String, Wire> wireMap) {
        final int aVal = getVal(a, wireMap);
        final int bVal = getVal(b, wireMap);
        return aVal | bVal;
    }

    public static int leftShift(final Wire a, final int amount) {
        return a.getValue() << amount;
    }

    public static int rightShift(final Wire a, final int amount) {
        return a.getValue() >> amount;
    }

    public static int not(final Wire a) {
        return ~a.getValue();
    }

    private static int getVal(String str, Map<String, Wire> wireMap) {
        return wireMap.containsKey(str) ? wireMap.get(str).getValue() : Integer.parseInt(str);
    }
}
