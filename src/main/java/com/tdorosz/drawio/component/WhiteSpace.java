package com.tdorosz.drawio.component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WhiteSpace {
    WRAP("wrap");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
