package com.tdorosz.drawiogen.drawio.element;

public interface Stylable<T> {
    String style();

    T style(String style);
}
