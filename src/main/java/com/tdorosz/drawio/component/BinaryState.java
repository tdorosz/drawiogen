package com.tdorosz.drawio.component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BinaryState {
    ON("1"),
    OFF("0");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
