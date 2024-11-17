package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;

import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public class RectangleObject extends MxObjectBasedShape<RectangleObject> {

    private static final String DEFAULT_STYLE = new RectangleStyle<>()
            .html(BinaryState.ON)
            .rounded(BinaryState.OFF)
            .whiteSpace(WhiteSpace.WRAP)
            .toStyleString();

    public static RectangleObject createNew() {
        MxCell mxCell = new MxCell()
                .vertex("1")
                .style(DEFAULT_STYLE)
                .mxGeometry(createDefaultGeometry());
        MxObject mxObject = new MxObject()
                .id(UUID.randomUUID().toString())
                .mxCell(mxCell)
                .label("");

        return new RectangleObject(mxObject);
    }

    public static RectangleObject from(MxObject mxObject) {
        return new RectangleObject(mxObject);
    }

    private RectangleObject(MxObject mxObject) {
        super(mxObject);
    }

    public RectangleStyle<RectangleObject> styleEditBegin() {
        return new RectangleStyle<>(this, mxObject.mxCell().style());
    }

    public RectangleObject addStyle(String styleString) {
        RectangleStyle<RectangleObject> style = new RectangleStyle<>(mxCell().style());
        mapStyleToObject(styleString, style);
        mxCell().style(style.toStyleString());
        return this;
    }
}
