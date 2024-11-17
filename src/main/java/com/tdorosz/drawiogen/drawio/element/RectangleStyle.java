package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

@Data
@Accessors(fluent = true, chain = true)
public class RectangleStyle<T extends Stylable<T>> {
    @ToStringExclude
    private final T parent;

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

    public RectangleStyle() {
        this("");
    }

    public RectangleStyle(String style) {
        this(null, style);
    }

    public RectangleStyle(T parent, String style) {
        this.parent = parent;
        mapStyleToObject(style, this);
    }

    public String toStyleString() {
        return ReflectionToStringBuilder
                .toString(this, DrawioStyleToStringStyle.getInstance());
    }

    public T styleEditCommit() {
        return parent.style(toStyleString());
    }
}
