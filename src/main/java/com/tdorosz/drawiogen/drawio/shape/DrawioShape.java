package com.tdorosz.drawiogen.drawio.shape;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;

public interface DrawioShape<T> {
    MxCell toMxCell();

    String id();

    T parent(String subCellId);
    String parent();

    default MxObject toObjectWrapper() {
        return null;
    }

    default boolean shouldMapToObjectWrapper() {
        return false;
    }

    default boolean shouldAdd() {
        return true;
    }
}
