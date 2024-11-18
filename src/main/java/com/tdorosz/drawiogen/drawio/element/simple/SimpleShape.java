package com.tdorosz.drawiogen.drawio.element.simple;

import com.tdorosz.drawiogen.drawio.element.BaseStyle;
import com.tdorosz.drawiogen.drawio.element.DrawioElementModel;
import com.tdorosz.drawiogen.drawio.element.DrawioElementModelProvider;
import com.tdorosz.drawiogen.drawio.element.Stylable;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import com.tdorosz.drawiogen.drawio.xmlschema.MxRectangle;

import java.util.List;
import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public abstract class SimpleShape<T extends SimpleShape<T, S>, S extends BaseStyle<T, S>>
        implements Stylable<T>, DrawioElementModelProvider {

    private static final String COMPLEX_ELEMENT_TYPE = "complexElementType";
    protected MxObject mxObject;
    protected MxCell mxCell;

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

    @SuppressWarnings("unchecked")
    public T position(Integer x, Integer y) {
        mxCell.mxGeometry().x(x).y(y);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T size(Integer widht, Integer height) {
        mxCell.mxGeometry().width(widht).height(height);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T x(Integer value) {
        mxCell.mxGeometry().x(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T y(Integer value) {
        mxCell.mxGeometry().x(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T width(Integer value) {
        mxCell.mxGeometry().width(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T height(Integer value) {
        mxCell.mxGeometry().height(value);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T tooltip(String tooltip) {
        changeToMxObject();
        this.mxObject.tooltip(tooltip);
        return (T) this;
    }

    public T complexElementType(String value) {
        changeToMxObject();
        this.mxObject.complexElementType(value);
        return (T) this;
    }

    private void changeToMxObject() {
        if (!isMxObject()) {
            mxObject = new MxObject()
                    .id(mxCell.id())
                    .label(mxCell.value())
                    .mxCell(mxCell);
            mxCell.id(null)
                    .value(null);
        }
    }

    @Override
    public DrawioElementModel getDrawioElementModel() {
        if (isMxObject()) {
            return new DrawioElementModel(id(), List.of(), List.of(mxObject));
        }
        return new DrawioElementModel(id(), List.of(mxCell), List.of());
    }
}
