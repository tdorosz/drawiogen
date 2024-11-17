package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import com.tdorosz.drawiogen.drawio.xmlschema.MxRectangle;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MxCellBasedShape<T extends MxCellBasedShape<T>> implements Stylable<T> {

    protected final MxCell mxCell;

    public MxCell mxCell() {
        return mxCell;
    }

    @SuppressWarnings("unchecked")
    public T parent(String value) {
        mxCell.parent(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T value(String value) {
        mxCell.value(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T style(String style) {
        mxCell.style(style);
        return (T) this;
    }

    @Override
    public String style() {
        return mxCell.style();
    }

    @SuppressWarnings("unchecked")
    public T addAlternateBounds(int width, int height) {
        MxRectangle alternateBounds = new MxRectangle()
                .width(width)
                .height(height)
                .as("alternateBounds");
        mxCell.mxGeometry().mxRectangle(alternateBounds);
        return (T) this;
    }


    public static MxGeometry createDefaultGeometry() {
        return new MxGeometry()
                .x(0)
                .y(0)
                .width(100)
                .height(100)
                .as("geometry");
    }

}
