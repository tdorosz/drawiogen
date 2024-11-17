package com.tdorosz.drawiogen.drawio.element.simple;

import com.tdorosz.drawiogen.drawio.element.BaseStyle;
import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.Stylable;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import com.tdorosz.drawiogen.drawio.xmlschema.MxRectangle;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

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

    public String id() {
        if (isMxObject()) {
            return mxObject.id();
        }
        return mxCell.id();
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

    public T position(Integer x, Integer y) {
        mxCell.mxGeometry().x(x).y(y);
        return (T) this;
    }

    public T size(Integer widht, Integer height) {
        mxCell.mxGeometry().width(widht).height(height);
        return (T) this;
    }

    public T x(Integer value) {
        mxCell.mxGeometry().x(value);
        return (T) this;
    }

    public T y(Integer value) {
        mxCell.mxGeometry().x(value);
        return (T) this;
    }

    public T width(Integer value) {
        mxCell.mxGeometry().width(value);
        return (T) this;
    }

    public T height(Integer value) {
        mxCell.mxGeometry().height(value);
        return (T) this;
    }

    public DrawioElementModel toModel() {
        return new DrawioElementModel(
                id(),
                Stream.of(mxCell).filter(Objects::nonNull).toList(),
                Stream.of(mxObject).filter(Objects::nonNull).toList()
        );
    }
}
