package com.tdorosz.drawio.component.basic;

import com.tdorosz.drawio.component.BinaryState;
import com.tdorosz.drawio.component.DrawioColor;
import com.tdorosz.drawio.component.DrawioShape;
import com.tdorosz.drawio.component.WhiteSpace;
import com.tdorosz.drawio.model.MxCell;
import com.tdorosz.drawio.model.MxGeometry;
import com.tdorosz.drawio.util.DrawioStyleToStringStyle;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

@Data
@Accessors(fluent = true, chain = true)
public class Process implements DrawioShape {

    private String id = UUID.randomUUID().toString();
    private String value;
    private String parent = "1";
    private String vertex = "1";
    private Integer x = 0;
    private Integer y = 0;
    private Integer width = 100;
    private Integer height = 100;
    private Style style;

    public static Process newRectangle(Integer x, Integer y) {
        return new Process()
                .x(x)
                .y(y)
                .style(new Style());
    }

    public Process style(Style style) {
        this.style = style.parent(this);
        return this;
    }

    @Override
    public MxCell toMxCell() {
        return MxCell.builder()
                .id(id)
                .value(value)
                .parent(parent)
                .vertex(vertex)
                .style(style != null ? style.toStyleString() : null)
                .mxGeometry(MxGeometry.builder()
                        .x(x)
                        .y(y)
                        .width(width)
                        .height(height)
                        .as("geometry")
                        .build())
                .build();
    }

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Style {
        @ToStringExclude
        private Process parent;

        private String shape = "process";
        private BinaryState rounded = BinaryState.ON;
        private BinaryState glass;
        private WhiteSpace whiteSpace = WhiteSpace.WRAP;
        private BinaryState html = BinaryState.ON;
        private DrawioColor fillColor;

        public String toStyleString() {
            return ReflectionToStringBuilder
                    .toString(this, DrawioStyleToStringStyle.getInstance());
        }

        public Process styleEnd() {
            return parent;
        }
    }

}
