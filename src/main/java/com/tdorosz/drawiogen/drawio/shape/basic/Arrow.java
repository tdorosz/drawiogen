package com.tdorosz.drawiogen.drawio.shape.basic;

import com.tdorosz.drawiogen.drawio.shape.DrawioShape;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import com.tdorosz.drawiogen.drawio.xmlschema.MxGeometry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

@Data
@Accessors(fluent = true, chain = true)
public class Arrow implements DrawioShape<Arrow> {

    private final String id = UUID.randomUUID().toString();
    private String parent;
    private String relative = "1";
    private String edge = "1";
    private String sourceId;
    private String targetId;
    private Style style;

    public static Arrow newArrow(String sourceId, String targetId) {
        return new Arrow()
                .sourceId(sourceId)
                .targetId(targetId)
                .style(new Style());
    }

    public Arrow style(Style style) {
        this.style = style.parent(this);
        return this;
    }

    @Override
    public MxCell toMxCell() {
        return MxCell.builder()
                .id(id)
                .edge(edge)
                .parent(parent)
                .style(style.toStyleString())
                .source(sourceId)
                .target(targetId)
                .mxGeometry(MxGeometry.builder()
                        .relative(relative)
                        .as("geometry")
                        .build())
                .build();
    }

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Style {
        @ToStringExclude
        private Arrow parent;

        private EdgeStyle edgeStyle = EdgeStyle.ORTHOGONAL_EDGE_STYLE;
        private Shape shape;
        private String rounded = "0";
        private String orthogonalLoop = "1";
        private String jettySize = "auto";
        private String html = "1";
        private String exitX;
        private String exitY;
        private String exitDx;
        private String exitDy;
        private String entryX;
        private String entryY;
        private String entryDx;
        private String entryDy;

        public String toStyleString() {
            return ReflectionToStringBuilder
                    .toString(this, DrawioStyleToStringStyle.getInstance());
        }

        public Arrow styleEnd() {
            return parent;
        }

        public Style shape(Shape shape) {
            if (shape == Shape.DEFAULT) {
                this.shape = null;
            } else {
                this.shape = shape;
            }
            return this;
        }
    }

    @AllArgsConstructor
    public enum EdgeStyle {
        ORTHOGONAL_EDGE_STYLE("orthogonalEdgeStyle");

        private final String value;

        @Override
        public String toString() {
            return value;
        }
    }

    @AllArgsConstructor
    public enum Shape {
        DEFAULT(null),
        FLEX_ARROW("flexArrow");

        private final String value;

        @Override
        public String toString() {
            return value;
        }
    }
}
