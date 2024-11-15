package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Group {
    private MxCell mxCell;

    public Group() {
        mxCell = new MxCell()
                .id(UUID.randomUUID().toString());
    }

    public Group parent(String value) {
        mxCell.parent(value);
        return this;
    }

    public String id() {
        return mxCell.id();
    }
}
