package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;

import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public class RectangleCell extends MxCellBasedShape<RectangleCell> {

    private static final String DEFAULT_STYLE = new RectangleStyle<>()
            .html(BinaryState.ON)
            .rounded(BinaryState.OFF)
            .whiteSpace(WhiteSpace.WRAP)
            .toStyleString();

    public static RectangleCell createNew() {
        MxCell mxCell = new MxCell()
                .id(UUID.randomUUID().toString())
                .vertex("1")
                .style(DEFAULT_STYLE)
                .mxGeometry(createDefaultGeometry());

        return new RectangleCell(mxCell);
    }

    public static RectangleCell from(MxCell mxCell) {
        return new RectangleCell(mxCell);
    }

    private RectangleCell(MxCell mxCell) {
        super(mxCell);
    }

    public RectangleStyle<RectangleCell> styleEditBegin() {
        return new RectangleStyle<>(this, mxCell.style());
    }

    public RectangleCell addStyle(String styleString) {
        RectangleStyle<RectangleObject> style = new RectangleStyle<>(mxCell().style());
        mapStyleToObject(styleString, style);
        mxCell().style(style.toStyleString());
        return this;
    }

}