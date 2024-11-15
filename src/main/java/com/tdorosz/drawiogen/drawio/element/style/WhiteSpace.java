package com.tdorosz.drawiogen.drawio.element.style;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WhiteSpace {
    WRAP("wrap");

    private final String value;

    public static WhiteSpace fromValue(String value) {
        for (WhiteSpace ws : WhiteSpace.values()) {
            if (ws.value.equalsIgnoreCase(value)) {
                return ws;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
