package com.tdorosz.drawio.component.basic;

import com.tdorosz.drawio.component.BinaryState;
import com.tdorosz.drawio.component.DrawioColor;
import com.tdorosz.drawio.component.DrawioShape;
import com.tdorosz.drawio.component.WhiteSpace;
import com.tdorosz.drawio.model.MxCell;
import com.tdorosz.drawio.model.MxGeometry;
import com.tdorosz.drawio.model.ObjectWrapper;
import com.tdorosz.drawio.util.DrawioStyleToStringStyle;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Accessors(fluent = true, chain = true)
public class Rectangle implements DrawioShape<Rectangle> {

    private final String id = UUID.randomUUID().toString();
    private Map<String, String> customValues = new LinkedHashMap<>();
    private Boolean placeholders;
    private String value;
    private String parent;
    private String vertex = "1";
    private Integer x = 0;
    private Integer y = 0;
    private Integer width = 100;
    private Integer height = 100;
    private Style style;

    public static Rectangle newRectangle(Integer x, Integer y) {
        return new Rectangle()
                .x(x)
                .y(y)
                .style(new Style());
    }

    public Rectangle style(Style style) {
        this.style = style.parent(this);
        return this;
    }

    @Override
    public boolean shouldMapToObjectWrapper() {
        return !customValues.isEmpty() || placeholders != null;
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

    @Override
    public ObjectWrapper toObjectWrapper() {
        MxCell mxCell = toMxCell();
        mxCell.setId(null);
        mxCell.setValue(null);

        return ObjectWrapper.builder()
                .id(id)
                .label(value)
                .placeholders(BooleanUtils.isTrue(placeholders) ? "1" : "0")
                .customParams(customValues)
                .mxCell(mxCell)
                .build();
    }

    public Rectangle addCustomValue(String key, String value) {
        customValues.put(key, value);
        return this;
    }

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Style {
        @ToStringExclude
        private Rectangle parent;

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

        public Rectangle styleEnd() {
            return parent;
        }
    }
}
