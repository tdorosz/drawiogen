package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public class Rectangle {

    private final MxCell mxCell;

    public Rectangle() {
        mxCell = new MxCell()
                .id(UUID.randomUUID().toString())
                .vertex("1")
                .style(new Style()
                        .html(BinaryState.ON)
                        .rounded(BinaryState.OFF)
                        .whiteSpace(WhiteSpace.WRAP)
                        .toStyleString())
                .mxGeometry(new MxGeometry()
                        .x(0)
                        .y(0)
                        .width(100)
                        .height(100)
                        .as("geometry"));
    }

    public Rectangle(MxCell mxCell) {
        this.mxCell = mxCell;
    }

    public MxCell mxCell() {
        return mxCell;
    }

    public Rectangle parent(String value) {
        mxCell.parent(value);
        return this;
    }

    public Rectangle value(String value) {
        mxCell.value(value);
        return this;
    }

    public Rectangle style(String style) {
        mxCell.style(style);
        return this;
    }

    public Rectangle addStyle(String styleString) {
        Style style = new Style(mxCell.style());
        mapStyleToObject(styleString, style);
        mxCell.style(style.toStyleString());
        return this;
    }

    public Style styleEditBegin() {
        return new Style(this, mxCell.style());
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
        private WhiteSpace whiteSpace;
        private BinaryState html;
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
