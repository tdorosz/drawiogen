package com.tdorosz.drawio.component.basic;

import com.tdorosz.drawio.component.DrawioShape;
import com.tdorosz.drawio.model.MxCell;
import com.tdorosz.drawio.model.MxGeometry;
import com.tdorosz.drawio.util.DrawioStyleToStringStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

@Data
@Accessors(fluent = true, chain = true)
public class Arrow implements DrawioShape {

    private String id = UUID.randomUUID().toString();
    private String parent = "1";
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
                .style(style != null ? style.toStyleString() : null)
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
        private String exitX = "0.5";
        private String exitY = "0.5";
        private String exitDx = "0";
        private String exitDy = "0.5";
        private String entryX = "0.5";
        private String entryY = "0.5";
        private String entryDx = "0";
        private String entryDy = "0";

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
