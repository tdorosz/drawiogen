package com.tdorosz.drawiogen.drawio.shape;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BinaryState {
    ON("1"),
    OFF("0");

    @Getter
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
