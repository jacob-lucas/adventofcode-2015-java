package com.jacoblucas.adventofcode2015.day7;

public class Wire {
    private final String id;
    private Integer value;

    public Wire(final String id) {
        this(id, null);
    }

    protected Wire(final String id, final Integer value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        try {
            return String.valueOf(getValue());
        } catch (final Exception e) {
            return "-";
        }
    }

    public String getId() {
        return id;
    }

    public boolean signalAvailable() {
        return value != null;
    }

    public int getValue() throws IllegalStateException {
        if (!signalAvailable()) {
            throw new IllegalStateException("No signal available");
        }

        return Short.toUnsignedInt((short)value.intValue());
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public void reset() {
        value = null;
    }
}
