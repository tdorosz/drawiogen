package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import com.tdorosz.drawiogen.drawio.xmlschema.MxRectangle;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public class Rectangle {

    private final MxObject mxObject;
    private final MxCell mxCell;

    private static final String DEFAULT_STYLE = new Style()
            .html(BinaryState.ON)
            .rounded(BinaryState.OFF)
            .whiteSpace(WhiteSpace.WRAP)
            .toStyleString();

    public Rectangle(MxObject mxObject) {
        this.mxObject = mxObject;
        this.mxCell = mxObject.mxCell();
    }

    public Rectangle(MxCell mxCell) {
        this.mxObject = null;
        this.mxCell = mxCell;
    }

    public Rectangle() {
        this.mxCell = new MxCell()
                .vertex("1")
                .style(DEFAULT_STYLE)
                .mxGeometry(new MxGeometry()
                        .x(0)
                        .y(0)
                        .width(100)
                        .height(100)
                        .as("geometry"));
        this.mxObject = new MxObject()
                .id(UUID.randomUUID().toString())
                .mxCell(mxCell)
                .label("");
    }

    public MxObject mxObject() {
        return mxObject;
    }


    public MxCell mxCell() {
        return mxCell;
    }

    public Style styleEditBegin() {
        return new Style(this, mxCell.style());
    }

    private Rectangle style(String styleString) {
        mxCell.style(styleString);
        return this;
    }

    public boolean isMxObject() {
        return mxObject != null;
    }

    public Rectangle parent(String id) {
        mxCell.parent(id);
        return this;
    }

    public Rectangle value(String val) {
        if (isMxObject()) {
            mxObject.label(val);
        } else {
            mxCell.value(val);
        }
        return this;
    }

    public Rectangle addAlternateBounds(int width, int height) {
        MxRectangle alternateBounds = new MxRectangle()
                .width(width)
                .height(height)
                .as("alternateBounds");
        mxCell.mxGeometry().mxRectangle(alternateBounds);
        return this;
    }

    public Rectangle addStyle(String styleString) {
        Style style = new Style(mxCell.style());
        mapStyleToObject(styleString, style);
        style(style.toStyleString());
        return this;
    }

    public Rectangle addStyle(Style newStyle) {
        Style style = new Style(mxCell.style());
        mapStyleToObject(newStyle.toStyleString(), style);
        style(style.toStyleString());
        return this;
    }

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Style {
        @ToStringExclude
        private final Rectangle parent;

        private BinaryState editable;
        private BinaryState connectable;
        private BinaryState sketch;
        private BinaryState rounded;
        private BinaryState glass;
        private BinaryState shadow;
        private BinaryState collapsible;
        private BinaryState html;
        private WhiteSpace whiteSpace;
        private DrawioColor fillColor;
        private DrawioColor strokeColor;

        public Style() {
            this("");
        }

        public Style(String style) {
            this(null, style);
        }

        public Style(Rectangle parent, String style) {
            this.parent = parent;
            mapStyleToObject(style, this);
        }

        public String toStyleString() {
            return ReflectionToStringBuilder
                    .toString(this, DrawioStyleToStringStyle.getInstance());
        }

        public Rectangle styleEditCommit() {
            return parent.style(toStyleString());
        }
    }
}
