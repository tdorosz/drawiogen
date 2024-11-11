package com.tdorosz.drawiogen.drawio.shape;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.ObjectWrapper;

public interface DrawioShape<T> {
    MxCell toMxCell();

    String id();

    T parent(String subCellId);
    String parent();

    default ObjectWrapper toObjectWrapper() {
        return null;
    }

    default boolean shouldMapToObjectWrapper() {
        return false;
    }

}
