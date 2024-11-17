package com.tdorosz.drawiogen.drawio.element.simple;

import com.tdorosz.drawiogen.drawio.element.BaseStyle;
import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public class Rectangle extends SimpleShape<Rectangle, Rectangle.Style> {

    private static final String DEFAULT_STYLE = new Style()
            .html(BinaryState.ON)
            .rounded(BinaryState.OFF)
            .whiteSpace(WhiteSpace.WRAP)
            .toStyleString();

    public Rectangle(MxObject mxObject) {
        super(mxObject);
    }

    public Rectangle(MxCell mxCell) {
        super(mxCell);
    }

    public Rectangle() {
        super();
        this.mxCell
                .style(DEFAULT_STYLE);
    }

    @Override
    public Style styleEditBegin() {
        return new Style(this, mxCell.style());
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(fluent = true, chain = true)
    public static class Style extends BaseStyle<Rectangle, Style> {

        private BinaryState connectable;
        private BinaryState rounded;
        private BinaryState glass;
        private BinaryState collapsible;
        private WhiteSpace whiteSpace;

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

    }
}
