package com.tdorosz.drawiogen.drawio.element.style;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public enum BinaryState {
    ON("1"),
    OFF("0");

    @Getter
    private final String value;

    @Override
    public String toString() {
        return value;
    }

    public static BinaryState fromValue(String value) {
        for (BinaryState binaryState : BinaryState.values()) {
            if (binaryState.value.equalsIgnoreCase(value)) {
                return binaryState;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }
}
