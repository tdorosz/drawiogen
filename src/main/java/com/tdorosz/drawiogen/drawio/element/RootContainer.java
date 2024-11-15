package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RootContainer {
    private MxCell mxCell;

    public RootContainer() {
        mxCell = new MxCell().id(UUID.randomUUID().toString());
    }

    public String id() {
        return mxCell.id();
    }
}