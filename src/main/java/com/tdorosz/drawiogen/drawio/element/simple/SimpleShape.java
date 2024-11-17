package com.tdorosz.drawiogen.drawio.element.simple;

import com.tdorosz.drawiogen.drawio.element.BaseStyle;
import com.tdorosz.drawiogen.drawio.element.Stylable;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import com.tdorosz.drawiogen.drawio.xmlschema.MxRectangle;

import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public abstract class SimpleShape<T extends SimpleShape<T, S>, S extends BaseStyle<T, S>>
        implements Stylable<T> {
    protected final MxObject mxObject;
    protected final MxCell mxCell;

    public SimpleShape(MxObject mxObject) {
        this.mxObject = mxObject;
        this.mxCell = mxObject.mxCell();
    }

    public SimpleShape(MxCell mxCell) {
        this.mxObject = null;
        this.mxCell = mxCell;
    }

    public SimpleShape() {
        this.mxCell = new MxCell()
                .id(UUID.randomUUID().toString())
                .vertex("1")
                .mxGeometry(new MxGeometry()
                        .x(0)
                        .y(0)
                        .width(100)
                        .height(100)
                        .as("geometry"));
        this.mxObject = null;
    }

    public MxObject mxObject() {
        return mxObject;
    }

    public MxCell mxCell() {
        return mxCell;
    }

    @SuppressWarnings("unchecked")
    public T parent(String id) {
        mxCell.parent(id);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T style(String styleString) {
        mxCell.style(styleString);
        return (T) this;
    }

    public abstract S styleEditBegin();

    @SuppressWarnings("unchecked")
    public T addStyle(S newStyle) {
        Rectangle.Style style = new Rectangle.Style(mxCell.style());
        mapStyleToObject(newStyle.toStyleString(), style);
        style(style.toStyleString());
        return (T) this;
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

    public boolean isMxObject() {
        return mxObject != null;
    }

    @SuppressWarnings("unchecked")
    public T value(String val) {
        if (isMxObject()) {
            mxObject.label(val);
        } else {
            mxCell.value(val);
        }
        return (T) this;
    }
}
