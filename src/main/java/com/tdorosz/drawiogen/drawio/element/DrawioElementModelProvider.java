package com.tdorosz.drawiogen.drawio.element;

public interface DrawioElementModelProvider<T> {
    DrawioElementModel getDrawioElementModel();

    T parent(String id);
}
