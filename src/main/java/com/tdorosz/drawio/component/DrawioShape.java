package com.tdorosz.drawio.component;

import com.tdorosz.drawio.model.MxCell;

public interface DrawioShape {
    MxCell toMxCell();

    String id();
}
