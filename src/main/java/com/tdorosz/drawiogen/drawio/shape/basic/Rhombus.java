package com.tdorosz.drawiogen.drawio.shape.basic;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.shape.DrawioShape;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

@Data
@Accessors(fluent = true, chain = true)
public class Rhombus implements DrawioShape<Rhombus> {

    private final String id = UUID.randomUUID().toString();
    private String value;
    private String parent = "1";
    private String vertex = "1";
    private Integer x = 0;
    private Integer y = 0;
    private Integer width = 100;
    private Integer height = 100;
    private Style style;

    public static Rhombus newRhombus(Integer x, Integer y) {
        return new Rhombus()
                .x(x)
                .y(y)
                .style(new Style());
    }

    public Rhombus style(Style style) {
        this.style = style.parent(this);
        return this;
    }

    @Override
    public MxCell toMxCell() {
        return new MxCell()
                .id(id)
                .value(value)
                .parent(parent)
                .vertex(vertex)
                .style(style != null ? style.toStyleString() : null)
                .mxGeometry(new MxGeometry()
                        .x(x)
                        .y(y)
                        .width(width)
                        .height(height)
                        .as("geometry"));
    }

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Style {
        @ToStringExclude
        private Rhombus parent;

        private String rhombus = "";
        private BinaryState sketch = BinaryState.OFF;
        private BinaryState rounded = BinaryState.ON;
        private BinaryState glass;
        private WhiteSpace whiteSpace = WhiteSpace.WRAP;
        private BinaryState html = BinaryState.ON;
        private DrawioColor fillColor;

        public String toStyleString() {
            return ReflectionToStringBuilder
                    .toString(this, DrawioStyleToStringStyle.getInstance());
        }

        public Rhombus styleEnd() {
            return parent;
        }
    }
}
