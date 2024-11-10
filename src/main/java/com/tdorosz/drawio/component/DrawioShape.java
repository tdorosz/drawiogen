package com.tdorosz.drawio.component;

import com.tdorosz.drawio.model.MxCell;
import com.tdorosz.drawio.model.ObjectWrapper;

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
